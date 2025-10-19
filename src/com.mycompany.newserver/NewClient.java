/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phase1;

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

class NewClient implements Runnable
{
private Socket client;
private BufferedReader in;
private PrintWriter out;
private ArrayList<NewClient> clients;
private ArrayList<Reservation> reservations; ///////
  public NewClient (Socket c,ArrayList<NewClient> clients, ArrayList<Reservation> reservations) throws IOException/////
  {
    this.client = c;
    this.clients=clients;
    this.reservations=reservations;/////
    in= new BufferedReader (new InputStreamReader(client.getInputStream())); 
    out=new PrintWriter(client.getOutputStream(),true); 
  }

@Override
  public void run ()
  {
   try{
    while (true){
        String name=in.readLine();
        //System.out.println("name "+name);
        String pass=in.readLine();
        //System.out.println("pass "+ pass);
        //String resturant=in.readLine();
        
       Reservation R = new Reservation(name, pass,"monday", "kapsa","2");
        R.Print();
        reservations.add(R);
         //outToAll(request);
   
    }
}
   catch (IOException e){
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
}
