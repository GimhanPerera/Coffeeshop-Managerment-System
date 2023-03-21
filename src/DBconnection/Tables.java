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
public class Tables extends Connect{
    
    public String checkAvailability(int pax) throws Exception{//NOT COMPLETED   
        Connection c= getConnection();//get the connection using inheritance
        String tid="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            //check a one table is engugh
            ResultSet rs = stmt.executeQuery("select TABLE_NO,CHAIRS from TABLE_T where CHAIRS>='"+pax+"' AND STATUS='AVAILABLE' ORDER BY CHAIRS LIMIT 1"); //SQL stetment
            while(rs.next()){
                tid=rs.getString("TABLE_NO");//get the value to variable "fname"
                pax-=rs.getInt("CHAIRS");
            }
            if(pax>6){
                rs = stmt.executeQuery("select TABLE_NO,CHAIRS from TABLE_T where CHAIRS>='"+pax+"' AND STATUS='AVAILABLE' ORDER BY CHAIRS LIMIT 1"); //SQL stetment
            while(rs.next()){
                tid=rs.getString("TABLE_NO");//get the value to variable "fname"
                pax-=rs.getInt("CHAIRS");
            }
            }
        }
        finally{
            c.close(); 
        }
        return tid;  
    }    
}
