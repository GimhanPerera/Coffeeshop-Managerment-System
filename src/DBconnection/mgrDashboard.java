/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gimhan
 */
public class mgrDashboard  extends Connect{
    public int orderCount() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int count=0;
        getDate obj =new getDate();
        String date=obj.dateOnly();
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CUSTOMER_ID from ORDER_T where ORDER_DATETIME BETWEEN '"+date+" 00:00' AND '"+date+" 23:59' AND STATUS='Completed'"); //SQL stetment
            while(rs.next()){
                count++;
            }                
        }
        finally{
            c.close(); 
        }
        return count;//Return First name    
    }
    public int todayIncome() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int total=0;
        getDate obj =new getDate();
        String date=obj.dateOnly();
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select (sum(AMOUNT)-sum(DISCOUNT))as tot from INVOICE where ORDERID IN (select ORDER_NUMBER from ORDER_T Inner Join INVOICE ON INVOICE.ORDERID=ORDER_T.ORDER_NUMBER where (ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND INVOICE.STATUS='Paid')"); //SQL stetment
            while(rs.next()){
                total=rs.getInt("tot");
            }                
        }
        finally{
            c.close(); 
        }
        return total;//Return First name    
    }
    
    public int monthlyIncome() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int total=0;
        getDate obj =new getDate();
        String yearMonth=obj.yearMonthOnly();
        try{ 
            //System.out.println(obj.lastdayofmonth());System.out.println(yearMonth);
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select (sum(AMOUNT)-sum(DISCOUNT)) as tot from INVOICE where ORDERID IN (select ORDER_NUMBER from ORDER_T where (ORDER_DATETIME > '"+yearMonth+"-01 00:00:00' AND ORDER_DATETIME <'"+obj.lastdayofmonth().toString()+" 23:59:59')AND INVOICE.STATUS='Paid')"); //SQL stetment
            while(rs.next()){
                total=rs.getInt("tot");
            }                
        }
        finally{
            c.close(); 
        }
        return total;//Return First name    
    }
    public int getActiveLoyaltycardCount() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int count=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select count(CARD_ID) as Count from LOYALTY_CARD where STATUS='ACTIVE'"); //SQL stetment
            while(rs.next()){
                count=rs.getInt("Count");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return count;  
    }
    
    public String bestBevLastMonth() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String food="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("SELECT FOOD_NAME FROM FOOD WHERE FOOD_ID = (SELECT FOOD_ID AS SUM FROM ORDER_FOOD WHERE FOOD_ID LIKE 'CF%' AND ORDER_NUMBER IN (SELECT ORDER_NUMBER FROM ORDER_T WHERE DATE(ORDER_DATETIME)>DATE(NOW() - INTERVAL 30 DAY)) GROUP BY FOOD_ID order by SUM DESC LIMIT 1);"); //SQL stetment
            while(rs.next()){
            food=rs.getString("FOOD_NAME");    
            }
        }
        finally{
            c.close(); 
        }
        return food;//Return First name    
    }
    
    public String bestNonBevLastMonth() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String food="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("SELECT FOOD_NAME FROM FOOD WHERE FOOD_ID = (SELECT FOOD_ID AS SUM FROM ORDER_FOOD WHERE FOOD_ID NOT LIKE 'CF%' AND ORDER_NUMBER IN (SELECT ORDER_NUMBER FROM ORDER_T WHERE DATE(ORDER_DATETIME)>DATE(NOW() - INTERVAL 30 DAY)) GROUP BY FOOD_ID order by SUM DESC LIMIT 1);"); //SQL stetment
            while(rs.next()){
            food=rs.getString("FOOD_NAME");    
            }              
        }
        finally{
            c.close(); 
        }
        return food;//Return First name    
    }
}
