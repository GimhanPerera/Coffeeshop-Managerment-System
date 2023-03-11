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
public class manager extends Connect{
    private String fname="";
    private String lname="";
    private int tp=0;
    private String email="";
    private String nic="";
    
    public manager() throws Exception{
        
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select * from EMPLOYEE where EMP_TYPE='MANAGER'"); //SQL stetment
            while(rs.next()){
                fname=rs.getString("F_NAME");
                lname=rs.getString("L_NAME");
                tp=rs.getInt("MOBILE_NUMBER");
                email=rs.getString("EMAIL");
                nic=rs.getString("NIC");
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
    }
    
    public String getfname(){   
        return fname;  
    }
    
    public String getlname(){   
        return lname;  
    }
    
    public String getTp(){   
        return Integer.toString(tp);  
    }
    
    public String getEmail(){   
        return email;  
    }
    
    public String getNIC(){   
        return nic;  
    }
    
    public boolean checkOldPwd(String opwd) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String real_pwd="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select PWD from EMPLOYEE where EMP_TYPE='MANAGER'"); //SQL stetment
            while(rs.next()){
                real_pwd=rs.getString("PWD");//get the value to variable "fname"
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        if(real_pwd.equals(opwd))
            return true;
        else
            return false;
    }
    
    public void setManagerDetails(String fname,String lname,String email,String tp,String nic) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="UPDATE EMPLOYEE " +
                            "SET F_NAME = '"+fname+"', L_NAME= '"+lname+"', MOBILE_NUMBER = '"+tp+"', EMAIL = '"+email+"', NIC = '"+nic+"' " +
                            " where EMP_TYPE='MANAGER'";
                stmt.executeUpdate(sql);
                this.fname=fname;
                this.lname=lname;
                this.email=email;
                this.tp=Integer.parseInt(tp);
                this.nic=nic;
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
    }
    
    public void setPwd(String newpwd) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="UPDATE EMPLOYEE " +
                            "SET PWD = '"+newpwd+"' " +
                            " where EMP_TYPE='MANAGER'";
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
}
