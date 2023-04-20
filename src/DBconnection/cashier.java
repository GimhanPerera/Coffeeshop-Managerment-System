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
public class cashier extends Connect{
    
    public int getPendingOrderCount()throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int count=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select COUNT(ORDER_NUMBER) as count from ORDER_T where STATUS='Pending' OR STATUS='Hold'"); //SQL stetment
            while(rs.next()){
                count=rs.getInt("count");
            } 
        }
        finally{
            c.close(); 
        }
        return count;  
    }
    
    public int competedOdrCount() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int total=0;
        getDate obj =new getDate();
        String date=obj.dateOnly();
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select ORDER_NUMBER from ORDER_T where ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59' AND STATUS='Completed'"); //SQL stetment
            while(rs.next()){
                total=rs.getInt("tot");
            }                
        }
        finally{
            c.close(); 
        }
        return total;//Return First name    
    }
    
    public void setHoldUnhold(String oid,String status) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql="update ORDER_T set STATUS='"+status+"' where ORDER_NUMBER='"+oid+"'"; //SQL stetment
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    public int getCustomerTp(String oid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int tp=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("SELECT MOBILE_NUMBER FROM CUSTOMER WHERE CUSTOMER_ID IN (select CUSTOMER_ID from ORDER_T where ORDER_NUMBER='"+oid+"')"); //SQL stetment
            while(rs.next()){
                tp=rs.getInt("MOBILE_NUMBER");
            } 
        }
        finally{
            c.close(); 
        }
        return tp;  
    }
    public int getorderTotal(String oid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int tot=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("SELECT AMOUNT,DISCOUNT FROM INVOICE WHERE ORDERID ='"+oid+"'"); //SQL stetment
            while(rs.next()){
                tot=rs.getInt("AMOUNT");
                tot=tot-rs.getInt("DISCOUNT");
            } 
        }
        finally{
            c.close(); 
        }
        return tot;  
    }
    
    public String[] getfooditemsOfOrder(String oid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int lines=0;
        //String foodid[];
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("SELECT COUNT(ORDER_NUMBER) as coun FROM ORDER_FOOD WHERE ORDER_NUMBER ='"+oid+"'"); //SQL stetment
            while(rs.next()){
                lines=rs.getInt("coun");
            }           
        }
        finally{
            c.close(); 
        }
        c= getConnection();
        String foodid[]=new String[lines];
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            int qty[]=new int[lines];
            ResultSet rs = stmt.executeQuery("SELECT FOOD_ID FROM ORDER_FOOD WHERE ORDER_NUMBER ='"+oid+"'"); //SQL stetment
            int y=0;
            while(rs.next()){
                foodid[y]=rs.getString("FOOD_ID");
                y++;
            }
            
        }
        finally{
            c.close(); 
        }
        return foodid;  
    }
    
    public int[] getQtysOfOrder(String oid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int lines=0;
        //String foodid[];
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("SELECT COUNT(ORDER_NUMBER) as coun FROM ORDER_FOOD WHERE ORDER_NUMBER ='"+oid+"'"); //SQL stetment
            while(rs.next()){
                lines=rs.getInt("coun");
            }
        }
        finally{
            c.close(); 
        }
        c= getConnection();
        int qty[]=new int[lines];
        try{ 
            Statement stmt = c.createStatement();//Prepare statement            
            ResultSet rs = stmt.executeQuery("SELECT QUANTITY FROM ORDER_FOOD WHERE ORDER_NUMBER ='"+oid+"'"); //SQL stetment
            int y=0;
            while(rs.next()){
                qty[y]=rs.getInt("QUANTITY");
                y++;
            }            
        }
        finally{
            c.close(); 
        }
        return qty;  
    }
}
