/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
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

public class Client {
    private static final String Server_IP="localhost";
    private static final int Server_port=9090;
    private  BufferedReader in;
    private PrintWriter out;
    private Socket socket;
            
    public Client(){
    
    try {
            socket = new Socket(Server_IP, Server_port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (IOException e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    
 
    }
    
  public void register(String username, String password) {
        if (out != null) {
            //out.println("Regestration info " + username + " " + password);
            out.println( username );
            out.println( password );       
            System.out.println("Sent to server: Regestration info " + username + " " + password);
        }
    }
        /**public static void main(String[] args) throws IOException{
          try(Socket socket = new Socket (Server_IP,Server_port)) {
              ServerConnection servcon=new ServerConnection(socket);
              BufferedReader keyboard=
                      new BufferedReader (new InputStreamReader(System.in));
              PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
              new Thread (servcon).start(); 
              try{
                  while(true){
                      MainMenu main= new MainMenu();
                      main.setVisible(true);
                      
                      
                      String command=keyboard.readLine();
                      out.println(command);
                      
                  } // end of while loop
              } catch (Exception e){
                  e.printStackTrace();
              }
          }
              System.exit(0); }**/
        




}
