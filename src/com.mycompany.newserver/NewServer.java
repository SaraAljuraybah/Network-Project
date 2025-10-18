
package com.mycompany.lab3;

/**
 *
 * @author Sss43
 */
import java.io.*; 
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class NewServer
{
    
     // array for reservation 
    private static ArrayList <Reservation> Reservations = new ArrayList<>();////
    private static ArrayList<NewClient> clients=new ArrayList<>();
    
    
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(9090);

        while (true){
         System.out.println("Waiting for client connection");
         Socket client=serverSocket.accept();
         System.out.println("Connected to client");
         NewClient clientThread=new NewClient(client,clients, Reservations);//// // new thread 
         clients.add(clientThread);
         new Thread (clientThread).start();
         
        }
    }
}

