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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
class NewClient implements Runnable
{
private Socket client;
private BufferedReader in;
private PrintWriter out;
private ArrayList<NewClient> clients;
private ArrayList<Reservation> reservations; 
private Resturant[] allResturants;
private ArrayList<User>registeredUsers; //$$$$$$$$$$
private String currentUsername;

  public NewClient (Socket c,ArrayList<NewClient> clients, ArrayList<Reservation> reservations,Resturant[] allResturants,ArrayList<User> registeredUsers ) throws IOException/////
  {
    this.client = c;
    this.clients=clients;
    this.reservations=reservations;/////
    this.allResturants = allResturants;
    this.registeredUsers = registeredUsers;//$$$$$$$$$$$
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
    if (data.length < 6) {  // RESERVE 
        out.println("Invalid reservation command. Usage: RESERVE <username> <restaurant> <table> <day> <time>");
        continue;
    }

    String username = data[1];
    String restaurantName = data[2];
    String tableNum = data[3];
    String day = data[4];
    String time = data[5];
    
    handleReserve(username, restaurantName, tableNum, day, time);

} else if (command.equalsIgnoreCase("SHOW")) {
    if (data.length < 4) {  // SHOW 
        out.println("Invalid show command. Usage: SHOW <restaurant> <table> <day>");
        out.println("END");
        continue;
    }

    String restaurantName = data[1];
    String tableNum = data[2];
    String day = data[3];
    handleShow(restaurantName, tableNum, day);

} 


 else if (command.equalsIgnoreCase("SHOW_TABLES")) {   // 
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

 //$$$$$$$$

         else if (command.equalsIgnoreCase("LOGIN")) {
    if (data.length < 3) {
        out.println("Invalid login command. Usage: LOGIN <username> <password>");
        continue;
    }
    String username = data[1];
    String password = data[2];
    handleLogin(username, password);
} else if (command.equalsIgnoreCase("REGISTER")) {
    // Read username and password from next lines
    try {
        String username = in.readLine();
        String password = in.readLine();
        if (username != null && password != null) {
            handleRegister(username, password);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
else if (command.equalsIgnoreCase("GET_MY_RESERVATIONS"))     {
    if(data.length<2){
        out.println("invaild command. Usage:GET_MY_RESERVATIONS<username>");
        out.println("END");
        continue;
    }
    String username=data[1];
    handelGetMyResrvation(username);
}
            
          
else {
    out.println("Unknown command: " + command);
}

    }
} catch (IOException e) {
    System.err.println("IO exception in new client class");
    e.printStackTrace();
}
  }//RUN
  
    
    
private void handleReserve(String username, String restaurantName, String tableNum, String day, String time) {
    // Remove the currentUsername logic, use the passed username directly
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
                        Reservation newReservation = new Reservation(username, "", day, restaurantName, time, tableNum);
                        reservations.add(newReservation);
                        System.out.println("Reservation stored for: " + username);
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

//$$$
private void handleLogin(String username, String password) {
    // ✅ CHECK IF USER EXISTS AND PASSWORD MATCHES
    for (User user : registeredUsers) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            this.currentUsername=username;
            out.println("LOGIN_SUCCESS");
            return;
        }
    }
    out.println("LOGIN_FAILED");
}

private void handleRegister(String username, String password) {
    // ✅ CHECK IF USER ALREADY EXISTS
    for (User user : registeredUsers) {
        if (user.getUsername().equals(username)) {
            out.println("REGISTER_FAILED: User already exists");
            return;
        }
    }
    // ✅ REGISTER NEW USER
    User newUser = new User(username, password);
    registeredUsers.add(newUser);
    out.println("REGISTER_SUCCESS");
    System.out.println("New user registered: " + username);
}

    private void handelGetMyResrvation (String username) {
        boolean found =false;
        
        for(Reservation res : reservations){
            if (res.Username.equals(username)){
                out.println("Resturant:"+ res.ResturnatName+
                ",Day: "+res.day+
                 ",Time: "+ res.time+
                   ", Table :"  +res.tableNo   );
                found= true;
            }
        }
       if(!found){
            out.println("no reservations found");
        
        }
        out.println("END");   
    }

}  
    
    
    
    
   