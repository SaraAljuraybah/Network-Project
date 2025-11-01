/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.newserver;

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
      


      
      
try {
    while (true) {
        String msg = in.readLine();
        if (msg == null) break;

msg = msg.replaceAll("[\\r\\n]+", "").trim();
String[] data = msg.split(" ");
System.out.println("Received command: [" + msg + "] Parts: " + data.length);
        String command = data[0];

if (command.equalsIgnoreCase("RESERVE")) {
    if (data.length < 5) {  // RESERVE تحتاج 5 عناصر
        out.println("Invalid reservation command. Usage: RESERVE <restaurant> <table> <day> <time>");
        continue;
    }

    String restaurantName = data[1];
    String tableNum = data[2];
    String day = data[3];
    String time = data[4];
    handleReserve(restaurantName, tableNum, day, time);

} else if (command.equalsIgnoreCase("SHOW")) {
    if (data.length < 4) {  // SHOW تحتاج 4 عناصر فقط
        out.println("Invalid show command. Usage: SHOW <restaurant> <table> <day>");
        out.println("END");
        continue;
    }

    String restaurantName = data[1];
    String tableNum = data[2];
    String day = data[3];
    handleShow(restaurantName, tableNum, day);

} 
/////////////////************************************************
 else if (command.equalsIgnoreCase("SHOW_TABLES")) {   // ← ADD THIS BLOCK
    if (data.length < 4) {
        out.println("Invalid show tables command. Usage: SHOW_TABLES <restaurant> <day> <time>");
        out.println("END");
        continue;
    }
    String restaurantName = data[1];
    String day = data[2];
    String time = data[3];
    handleShowTables(restaurantName, day, time);
}
/////////////////////******************

else {
    out.println("Unknown command: " + command);
}

    }
} catch (IOException e) {
    System.err.println("IO exception in new client class");
    e.printStackTrace();
}
  }//RUN
  
    
    
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

///*******************************************
private void handleShowTables(String restaurantName, String day, String time) {
    // Get all available tables for specific time slot
    for (int i = 0; i < allResturants.length; i++) {
        Resturant r = allResturants[i];
        if (r.name.equalsIgnoreCase(restaurantName)) {
            for (int j = 0; j < r.tables.size(); j++) {
                table t = r.tables.get(j);
                if (t.day.equalsIgnoreCase(day) && t.time.equals(time) && !t.reserved) {
                    out.println(t.tableNum);  // Only send available tables
                }
            }
        }
    }
    out.println("END");
}

////*************************************

}
    
    
    
    
   
