/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.newserver;

/**
 *
 * @author HUAWEI
 */
import java.io.*; 
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
public class NewServer {
    
    //array for reservations: 
   private static ArrayList <Reservation> Reservations= new ArrayList<>();
    //can we use an array insted of a list? or we dont know the size in advance?
    
    private static ArrayList<NewClient> clients=new ArrayList<>();
       // $$$$$$$$$$$$
    private static ArrayList<User> registeredUsers = new ArrayList<>();
    //$$$$$$$$$$$$
    
  private static Resturant[] allRresturants = {
    new Resturant("R1"),
    new Resturant("R2"),
    new Resturant("R3")
};// method
  
  
    public static void main(String[] args) throws IOException
    {
       
        ServerSocket serverSocket = new ServerSocket(9090);

        while (true){
         System.out.println("Waiting for client connection");
         Socket client=serverSocket.accept();
         System.out.println("Connected to client");
         //$$$$$$$$$$$$$$$$$$$$$$$ registerdUsers
         NewClient clientThread=new NewClient(client,clients, Reservations,allRresturants,registeredUsers ); // new thread 
         clients.add(clientThread);
         new Thread (clientThread).start();
         
        }
    }
}