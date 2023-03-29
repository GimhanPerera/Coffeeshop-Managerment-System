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
public class Customer extends Connect{
    public String getCID(String tp) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String cid="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CUSTOMER_ID from CUSTOMER where MOBILE_NUMBER='"+tp+"'"); //SQL stetment
            while(rs.next()){
                cid=rs.getString("CUSTOMER_ID");//get the value to variable "fname"
            }
            if("".equals(cid)){
                cid="0";
            }                
        }
        finally{
            c.close(); 
        }
        return cid;  
    }
    
    public String getFname(String c_ID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String fname="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select F_name from CUSTOMER where customer_ID='"+c_ID+"'"); //SQL stetment
            while(rs.next()){
                fname=rs.getString("F_name");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return fname;//Return First name    
    }
    
    public String getLname(String c_ID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String lname="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select L_name from CUSTOMER where customer_ID='"+c_ID+"'"); //SQL stetment
            while(rs.next()){
                lname=rs.getString("L_name");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return lname;  
    }
    public String getEmail(String c_ID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String email="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select EMAIL from CUSTOMER where customer_ID='"+c_ID+"'"); //SQL stetment
            while(rs.next()){
                email=rs.getString("EMAIL");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return email;  
    }
    
    public String getTp(String c_ID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String tp="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select MOBILE_NUMBER from CUSTOMER where customer_ID='"+c_ID+"'"); //SQL stetment
            while(rs.next()){
                tp=rs.getString("MOBILE_NUMBER");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return tp;   
    }
    
    
    public String getCardID(String c_ID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String id="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CARD_ID from LOYALTY_CARD where customer_ID='"+c_ID+"'"); //SQL stetment
            while(rs.next()){
                id=rs.getString("CARD_ID");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return id; 
    }
    public int getMaxDiscount(String c_ID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int points=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select POINTS from CUSTOMER where customer_ID='"+c_ID+"'"); //SQL stetment
            while(rs.next()){
                points=rs.getInt("POINTS");//get the value to variable "fname"
            } 
        }
        finally{
            c.close(); 
        }
        return points-100;  
    }
    
    public String saveCustomerDetails(String cid,String fname,String lname,String email,String tp,int points) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            if("".equals(email))
                email="NULL";
            //Update customer table
            if("0".equals(cid)){
                cid=newCID();
                String sql="INSERT INTO CUSTOMER (CUSTOMER_ID, F_NAME, L_NAME, EMAIL, MOBILE_NUMBER,POINTS,REQUEST) " +
                    "VALUES ('"+cid+"', '"+fname+"', '"+lname+"','"+email+"','"+tp+"','"+points+"','0')";
                stmt.executeUpdate(sql);
            }else{
                String sql="UPDATE CUSTOMER SET F_NAME='"+fname+"', L_NAME='"+lname+"', EMAIL='"+email+"', MOBILE_NUMBER='"+tp+"',POINTS='"+points+"'" +
                    "where CUSTOMER_ID='"+cid+"'";
                stmt.executeUpdate(sql);
            }
        }
        finally{
            c.close(); 
        }
        return cid;    
    }
    
    public String newCID() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String cID="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CUSTOMER_ID from CUSTOMER ORDER BY CUSTOMER_ID DESC LIMIT 1"); //SQL stetment
            while(rs.next()){
                cID=rs.getString("CUSTOMER_ID");
            }
            cID=cID.substring(2);
            String zeros="";
            for(int i=(Integer.toString((Integer.parseInt(cID))+1)).length();i<3;i++){
                zeros=zeros+"0";
            }                    
            cID="CS"+zeros+Integer.toString((Integer.parseInt(cID))+1);
        }
        finally{
            c.close(); 
        }
        return cID;    
    }
    
    public boolean removeCastomer(String cid) throws Exception{ //Remove a customer from a database  
        Connection c= getConnection();//get the connection using inheritance
        int count=0;
        try{ 
            Statement stmt = c.createStatement();
            //First check active orders of the castomer
            ResultSet rs = stmt.executeQuery("select COUNT(*) AS num from ORDER_T WHERE CUSTOMER_ID='"+cid+"' AND STATUS!='Completed'"); //SQL stetment
            while(rs.next()){
                count=rs.getInt("num");
            }
            if(count!=0)//if there are active orders, We can't delete the customer
            {
                c.close();
                return false;
            }
            //set cus_id of his orders to CS000
            String sql="UPDATE ORDER_T SET CUSTOMER_ID='CS000' where CUSTOMER_ID='"+cid+"'";
            stmt.executeUpdate(sql);
            sql="DELETE FROM LOYALTY_CARD WHERE CUSTOMER_ID='"+cid+"';";
            stmt.executeUpdate(sql);
            sql="DELETE FROM CUSTOMER WHERE CUSTOMER_ID='"+cid+"';";
            stmt.executeUpdate(sql);
            
        }
        finally{
            c.close(); 
        }
        return true;    
    }
}