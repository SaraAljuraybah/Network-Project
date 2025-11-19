/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phase1;
import java.util.ArrayList;
/**
 *
 * @author haya9
 */
public class table {

   String tableNum; 
    String day;     
    String time;     
    boolean reserved; 

    public table(String tableNum, String day, String time) {
        this.tableNum = tableNum;
        this.day = day;
        this.time = time;
        this.reserved = false;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        reserved = true;
    }

    public void cancel() {
        reserved = false;
    }




}
