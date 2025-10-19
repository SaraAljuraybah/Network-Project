/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phase1;

/**
 *
 * @author haya9
 */
public class Resturant {
 
   //String[]Resturants={"R1","R2","R3"};
   table[] t ;
   String name;
    
  //  public Resturant(String resterantName, table [] t)
  public Resturant(String resterantName) {
    name= resterantName ;
   // this.t =t;
   
    if( name.equals("R1") ){
    t=new table[]{
         new table(true,"1")  ,
         new table(true,"2")  ,
         new table(true,"3")  ,
         new table(true,"4")  ,
         new table(true,"5")  ,
        };
    }//first if
    else if (name.equals("R2")){
     t=new table[]{
           new table(true,"11"),
           new table(true,"22"),
           new table(true,"33"),
           new table(true,"44"),
           new table(true,"55"),
        };
        
    }//2nd if
    else if(name.equals("R3")){
     t=new table[]{
            new table (true, "111"),
            new table (true, "222"),
            new table (true, "333"),
            new table (true, "444"),
            new table (true, "555"),
        };
    }//3rd if
    
    }//method
  public boolean reserveTable(String tableNumber){
      for(int i =0 ; i<t.length; i++){
        if ( t[i].tableNum.equals(tableNumber) )  { // if the table picked by the user is avilable or not? , if ture enter the loop
            if (t[i].available){
                t[i].available=false; //set to false , because now it is reserved by the user
                System.out.println("your table :" + tableNumber +" in Resturant " + name +" is successfully reserved!");
                return true;
            }//2nd if
            else{
                System.out.println(" this table is not available");
                return false;
            }
        }// if
          
      }//for
      return false;// in case the user enterd an invalid input....
  }//reserveTable
    
}//class
