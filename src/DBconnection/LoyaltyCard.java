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
                Points=rs.getInt("POINTS");
            } 
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
            String sql="update CUSTOMER set REQUEST='1' where CUSTOMER_ID='"+CID+"'"; //SQL stetment
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    public void blockUnblockCard(String CID,String status) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql="update LOYALTY_CARD set STATUS='"+status+"' where CARD_ID='"+CID+"'"; //SQL stetment
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    public void issueCard(String cardID,String customerTp,String empID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String cusID="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CUSTOMER_ID from CUSTOMER where MOBILE_NUMBER='"+customerTp+"'"); //SQL stetment
            while(rs.next()){
                cusID=rs.getString("CUSTOMER_ID");
            }
            getDate obj =new getDate();
            String sql="update LOYALTY_CARD set STATUS='Active', CUSTOMER_ID='"+cusID+"', ISSUE_DATE='"+obj.dateOnly()+"', "
            + " EMP_ID='"+empID+"'  where CARD_ID='"+cardID+"';"; //SQL stetment
            stmt.executeUpdate(sql);
            sql="update CUSTOMER set REQUEST='2' where CUSTOMER_ID='"+cusID+"';"; //SQL stetment
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    //check customer loyalty card status. return 0->not request ,1->requested ,2->has a card
    public int checkRequseted(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int req=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select REQUEST from CUSTOMER where CUSTOMER_ID='"+CID+"'"); //SQL stetment
            while(rs.next()){
                req=rs.getInt("REQUEST");
            } 
        }
        finally{
            c.close(); 
        }
        return req;  
    }
    
    public boolean checkExistence(String cardid) throws Exception{ //check the card is existing(return 1) or not(return 0)  
        Connection c= getConnection();//get the connection using inheritance
        boolean exist=true;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select STATUS from LOYALTY_CARD where CARD_ID='"+cardid+"'"); //SQL stetment
            while(rs.next()){
                String temp=rs.getString("STATUS");
                if(("".equals(temp))){
                    exist=false;
                }
            } 
        }
        finally{
            c.close(); 
        }
        return exist;  
    }
    
    public int checkIssued(String CID) throws Exception{ //check it is a free card.if free(return 1) or not free(return 0)  
        Connection c= getConnection();//get the connection using inheritance
        int status=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select STATUS from LOYALTY_CARD where CARD_ID='"+CID+"'"); //SQL stetment
            while(rs.next()){
                String temp=rs.getString("STATUS");//System.out.println(temp);
                if("FREE".equals(temp)){
                    status=1;
                }
            } 
        }
        finally{
            c.close(); 
        }
        return status;  
    }
    
    public int addNewCard(String cardid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int i=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement

            String sql;
            sql="INSERT INTO LOYALTY_CARD (CARD_ID, STATUS) " +
                    "VALUES ('"+cardid+"','FREE')";
            i=stmt.executeUpdate(sql);      
        }
        finally{
            c.close(); 
        }
        return i;
    }
    
    public int removeCard(String cardid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int i=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql;
            sql="UPDATE CUSTOMER SET request = '0' WHERE CUSTOMER_ID =( SELECT CUSTOMER_ID FROM LOYALTY_CARD WHERE card_id = '"+cardid+"');";
            i=stmt.executeUpdate(sql);
            sql="delete from LOYALTY_CARD WHERE CARD_ID= '"+cardid+"';";
            i=stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
        return i;
    }
    
    public void rejectRequest(String tp) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql;
            sql="UPDATE CUSTOMER SET request = '0' WHERE MOBILE_NUMBER='"+tp+"'";
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    public String getLoyaltycardNumber(String CID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String id="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select CARD_ID from LOYALTY_CARD where CUSTOMER_ID='"+CID+"'"); //SQL stetment
            while(rs.next()){
                id=rs.getString("CARD_ID");
            } 
        }
        finally{
            c.close(); 
        }
        return id;  
    }
}
