/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author Sss43
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
class NewClient implements Runnable
{
private Socket client;
private BufferedReader in;
private PrintWriter out;
private ArrayList<NewClient> clients;
private ArrayList<Reservation> reservations; //////
private Resturant[] allResturants;


  public NewClient (Socket c,ArrayList<NewClient> clients, ArrayList<Reservation> reservations,Resturant[] allResturants ) throws IOException/////
  {
    this.client = c;
    this.clients=clients;
    this.reservations=reservations;/////
    this.allResturants = allResturants;
    
    in= new BufferedReader (new InputStreamReader(client.getInputStream())); 
    out=new PrintWriter(client.getOutputStream(),true); 
  }

@Override
  public void run ()
  {
   try{
       
       
               String name=in.readLine();
        //System.out.println("name "+name);
        String pass=in.readLine();
        //System.out.println("pass "+ pass);
        //String resturant=in.readLine();
        
       Reservation R = new Reservation(name, pass,"-", "-","-");
        R.Print();
        reservations.add(R);
         //outToAll(request);
         
    while (true){

   
         
         
       String msg = in.readLine();
                if (msg == null) break;

              
                String[] data = msg.split(" ");
                String command = data[0];

                if (command.equalsIgnoreCase("RESERVE")) {
                    String restaurantName = data[1];
                    String tableNum = data[2];
                    String day = data[3];
                    String time = data[4];
                    handleReserve(restaurantName, tableNum, day, time);
                } 
                else if (command.equalsIgnoreCase("SHOW")) {
                    String restaurantName = data[1];
                    String tableNum = data[2];
                    String day = data[3];
                    handleShow(restaurantName, tableNum, day);
                }
         
    }
}catch (IOException e){
       System.err.println("IO exception in new client class");
       System.err.println(e.getStackTrace());
   }
finally{
    out.close();
       try {
           in.close();
       } catch (IOException ex) {
          ex.printStackTrace();
       }
}
  }
    private void outToAll(String substring) {
for (NewClient aclient:clients){
   aclient.out.println(substring); 
}
    }
    
    
    
    
    
    
    private void handleReserve(String restaurantName, String tableNum, String day, String time) {
        for (int i = 0; i < allResturants.length; i++) {
            Resturant r = allResturants[i];
            if (r.name.equalsIgnoreCase(restaurantName)) {
                for (int j = 0; j < r.tables.size(); j++) {
                    table t = r.tables.get(j);
                    if (t.tableNum.equals(tableNum)
                            && t.day.equalsIgnoreCase(day)
                            && t.time.equals(time)) {

                        if (!t.reserved) {
                            t.reserved = true;
                            out.println("Reservation confirmed for " + day + " at " + time);
                            return;
                        } else {
                            out.println("Table already reserved.");
                            return;
                        }
                    }
                }
            }
        }
        out.println("Reservation failed.");
    }

    private void handleShow(String restaurantName, String tableNum, String day) {
        for (int i = 0; i < allResturants.length; i++) {
            Resturant r = allResturants[i];
            if (r.name.equalsIgnoreCase(restaurantName)) {
                for (int j = 0; j < r.tables.size(); j++) {
                    table t = r.tables.get(j);
                    if (t.tableNum.equals(tableNum)
                            && t.day.equalsIgnoreCase(day)
                            && !t.reserved) {
                        out.println(t.time);
                    }
                }
            }
        }
        out.println("END");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}// end of New client
