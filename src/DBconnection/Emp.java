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
    private String eid="";
    private String fname="";
    private String lname="";
    private int tp=0;
    private String etpye="";
    private String email="";
    private String nic="";
    private String pwd="";
    
    public Emp(){       
    }
    
    public Emp(String eid) throws Exception{
        this.eid=eid;//System.out.println("EEAAAA1 ");
        getDetails(eid);
    }
    
    private void getDetails(String eid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from EMPLOYEE WHERE EMP_ID='"+eid+"'");
            while(rs.next()){
                this.fname=rs.getString("F_NAME");
                this.lname=rs.getString("L_NAME");
                this.tp=rs.getInt("MOBILE_NUMBER");
                this.etpye=rs.getString("EMP_TYPE");
                this.nic=rs.getString("NIC");
                this.email=rs.getString("EMAIL");
                this.pwd=rs.getString("PWD");
            }
        }
        finally{
            c.close(); 
        }   
    }
    
    public String getEid(){
        return this.eid;
    }
    public String getFname(){
        return this.fname;
    }
    public String getLname(){
        return this.lname;
    }
    public int getTp(){
        return this.tp;
    }
    public String getEtype(){
        return this.etpye;
    }
    public String getNIC(){
        return this.nic;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPPwd(){
        return this.pwd;
    }
    
    public String newCID() throws Exception{//create new emp id
        Connection c= getConnection();//get the connection using inheritance
        String eID="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select EMP_ID from EMPLOYEE ORDER BY EMP_ID DESC LIMIT 1"); //SQL stetment
            while(rs.next()){
                eID=rs.getString("EMP_ID");
            }
            eID=eID.substring(2);
            String zeros="";System.out.println("AAAAA "+Integer.parseInt(eID));
            for(int i=(Integer.toString((Integer.parseInt(eID))+1)).length();i<3;i++){
                zeros=zeros+"0";
            }                    
            eID="EM"+zeros+Integer.toString((Integer.parseInt(eID))+1);
        }
        finally{
            c.close(); 
        }
        return eID;    
    }
    
    public boolean checkOldPwd(String empID,String opwd) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String real_pwd="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select PWD from EMPLOYEE where EMP_ID='"+empID+"'"); //SQL stetment
            while(rs.next()){
                real_pwd=rs.getString("PWD");
            } 
        }
        finally{
            c.close(); 
        }
        if(real_pwd.equals(opwd))
            return true;
        else
            return false;
    }
    
    public void setPwd(String empID,String newpwd) throws Exception{   //set new pwd to a existing employee
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();
                String sql="UPDATE EMPLOYEE " +
                            "SET PWD = '"+newpwd+"' " +
                            " where EMP_ID='"+empID+"'";
                stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    //add new stuff
    public void addStaff(String empID,String fname,String lname,String tp,String email, String nic, String emp_type, String pwd) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();
                String sql="INSERT INTO EMPLOYEE Values " +
                            "('"+empID+"','"+fname+"','"+lname+"','"+tp+"','"+email+"','"+nic+"','"+emp_type+"','"+pwd+"')";
                stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
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
    
    public void setEmpDetails(String eid,String fname,String lname,String email,String tp,String nic,String emp_type) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="UPDATE EMPLOYEE " +
                            "SET F_NAME = '"+fname+"', L_NAME= '"+lname+"', MOBILE_NUMBER = '"+tp+"', EMAIL = '"+email+"', NIC = '"+nic+"',EMP_TYPE='"+emp_type+"' " +
                            " where EMP_ID='"+eid+"'";
                stmt.executeUpdate(sql);
                this.fname=fname;
                this.lname=lname;
                this.email=email;
                this.tp=Integer.parseInt(tp);
                this.nic=nic;
        }
        finally{
            c.close(); 
        }
    }
    
    public void removeEmp(String empid) throws Exception{  //Reomve employee from a databasse 
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();
            String sql="update LOYALTY_CARD set EMP_ID=NULL where EMP_ID='"+empid+"'";
            stmt.executeUpdate(sql);
            sql="DELETE FROM EMPLOYEE WHERE EMP_ID='"+empid+"';";
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    public boolean isEmailAlreadyExist(String email,String eid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        byte count=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select COUNT(*) AS c from EMPLOYEE where EMAIL='"+email+"' AND EMP_ID!='"+eid+"'"); //SQL stetment
            while(rs.next()){
                count=rs.getByte("c");
            } 
        }
        finally{
            c.close(); 
        }
        if(count==0)
            return false; 
        else
            return true;
    }
}
