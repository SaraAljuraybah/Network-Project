/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
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
  
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final String Server_IP = "10.6.197.159";
    private static final int Server_port = 9090;

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public Client() {
        try {
            socket = new Socket(Server_IP, Server_port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (IOException e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    }

    // اختيارية للحساب
    public void register(String username, String password) {
        if (out != null) {
            out.println(username);
            out.println(password);
            
            System.out.println("Sent to server: Regestration info " + username + " " + password);
        }
    }

    // ✅ الدوال اللازمة للجيو
    public void sendMessage(String msg) {
        if (out != null) out.println(msg);
    }

    public String readMessage() {
        try {
            return (in != null) ? in.readLine() : null;
        } catch (IOException e) {
            return null;
        }
    }

    public List<String> readUntilEnd() {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if ("END".equals(line)) break;
                lines.add(line);
            }
        } catch (IOException e) {
            // تجاهل أو اطبعي رسالة
        }
        return lines;
    }

    public void close() {
        try {
            if (in != null)  in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException ignore) {}
    }
}

