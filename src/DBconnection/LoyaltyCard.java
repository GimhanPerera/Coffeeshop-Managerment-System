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
public class LoyaltyCard extends Connect{
    
    public int getLoyaltyPoints(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int Points=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select POINTS from CUSTOMER where CUSTOMER_ID='"+CID+"'"); //SQL stetment
            while(rs.next()){
                Points=rs.getInt("POINTS");//get the value to variable "fname"
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return Points;  
    } 
    public void sendRequest(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql="update CUSTOMER set REQUEST='1' where sid='"+CID+"'"; //SQL stetment
            stmt.executeUpdate(sql);
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
    }
    public int checkRequseted(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int req=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select REQUEST from CUSTOMER where CUSTOMER_ID='"+CID+"'"); //SQL stetment
            while(rs.next()){
                req=rs.getInt("REQUEST");//get the value to variable "fname"
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return req;  
    }
    public String getLoyaltycardNumber(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String id="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CARD_ID from LOYALTY_CARD where CUSTOMER_ID='"+CID+"'"); //SQL stetment
            while(rs.next()){
                id=rs.getString("CARD_ID");//get the value to variable "fname"
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return id;  
    }
}
