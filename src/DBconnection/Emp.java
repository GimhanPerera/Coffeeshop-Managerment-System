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
public class Emp extends Connect{
    
    public String checkStuff(String email,String pwd) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String eid="";
        String cemail="";
        String cpwd="";
        String e_type="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select EMP_ID,EMAIL,PWD,EMP_TYPE from EMPLOYEE where EMAIL='"+email+"'"); //SQL stetment
            while(rs.next()){
                eid=rs.getString("EMP_ID");
                cemail=rs.getString("EMAIL");
                cpwd=rs.getString("PWD");
                e_type=rs.getString("EMP_TYPE");
                if(e_type.equals("CASHIER")){
                    e_type=eid;
                }
            }
            if(!cemail.equals(email)){
                e_type="1";
            }
            else{
                if(!cpwd.equals(pwd)){
                    e_type="2";
                }
            }
            
        }
        finally{
            c.close(); 
        }
        return e_type;  
    } 
    public void sendRequest(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql="update CUSTOMER set REQUEST='1' where sid='"+CID+"'"; //SQL stetment
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
}
