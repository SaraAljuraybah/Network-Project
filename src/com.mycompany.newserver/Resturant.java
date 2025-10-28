/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author haya9
 */
import java.util.ArrayList;
public class Resturant {
 

    ArrayList<table> tables;
   String name;
   String[] times = {"4-6", "6-8", "8-10"};
    String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
  public Resturant(String resterantName) {
    name= resterantName ;
  tables = new ArrayList<>();
  
   // إنشاء كل الحالات الممكنة لكل طاولة
     for (int i = 1; i <= 5; i++) {           // 5 طاولات
            for (int d = 0; d < days.length; d++) {   // 7 أيام
                for (int t = 0; t < times.length; t++) {  // 3 فترات
                    String tableNum = "" + i;  // نحول الرقم إلى String بطريقة بسيطة
                    tables.add(new table(tableNum, days[d], times[t]));
                }
            }
        }
    }

   
  



 


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}//class

