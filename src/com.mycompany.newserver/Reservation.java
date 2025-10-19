/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phase1;

/**
 *
 * @author haya9
 */
public class Reservation {
  String Username;
  String Password;
  String day;
  String ResturnatName;
 // String tableNo;
  String time;
    
    
    //counstructor
    public Reservation(String Username , String pass , String Day ,  String rest,String time){
    this.Username = Username;
    Password = pass;
   // ResturnatName =rest;
    day = Day;
   this.time= time;
  //  this.tableNo=tableNo;
    
    }
    
    
 public void Print  (){
     System.out.println("Regestration info"+ Username + " " +Password + " " + day);
 } 
    
    
    
    
    
    
    
    

    
}// end of reservation class
