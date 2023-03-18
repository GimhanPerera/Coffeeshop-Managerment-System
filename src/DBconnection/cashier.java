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
    public String checkCardStatus(String cardID)throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String status="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select POINTS from STATUS where CARD_ID='"+cardID+"'"); //SQL stetment
            while(rs.next()){
                status=rs.getString("STATUS");//get the value to variable "fname"
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return status;  
    }
}
