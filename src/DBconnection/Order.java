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
public class Order extends Connect{
    
    public String newOrderID() throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String lID="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select ORDER_NUMBER from ORDER_T ORDER BY ORDER_NUMBER DESC LIMIT 1"); //SQL stetment
            while(rs.next()){
                lID=rs.getString("ORDER_NUMBER");//get the value to variable "fname"
            }
            lID=lID.substring(2);
            String zeros="";
            for(int i=(Integer.toString((Integer.parseInt(lID))+1)).length();i<5;i++){
                zeros=zeros+"0";
            }
            lID="OR"+zeros+Integer.toString((Integer.parseInt(lID))+1);
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return lID;    
    }
    
    public int placeOrder(String o_type,String cID,String[] fID,int[] qty,String pay_method,int amount ,int discount,String paymentstatus) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            String newOID=newOrderID();
            Statement stmt = c.createStatement();//Prepare statement
            //Update order table
            getDate obj=new getDate();
            String sql;
            if(o_type.equals("Takeaway")){
                sql="INSERT INTO ORDER_T (ORDER_NUMBER, ORDER_TYPE, ORDER_DATETIME, END_TIME, CUSTOMER_ID,STATUS) " +
                    "VALUES ('"+newOID+"', '"+o_type+"', '"+obj.dateAndTime()+"','"+obj.dateAndTime()+"','"+cID+"','Pending')";
            }else{
                sql="INSERT INTO ORDER_T (ORDER_NUMBER, ORDER_TYPE, ORDER_DATETIME, CUSTOMER_ID,STATUS) " +
                    "VALUES ('"+newOID+"', '"+o_type+"', '"+obj.dateAndTime()+"','"+cID+"','Pending')";
            }
            
            stmt.executeUpdate(sql);
            System.out.println("Order table updated");
            //Update food_order table
            for(int i=0;i<fID.length;i++)
            {
               sql="INSERT INTO ORDER_FOOD(ORDER_NUMBER,FOOD_ID,QUANTITY) VALUES('"+newOID+"','"+fID[i]+"','"+qty[i]+"')";
               stmt.executeUpdate(sql);
            }System.out.println("food_order table updated");
            //Generate invoice table
            sql="INSERT INTO INVOICE(PAYMENT_METHOD,AMOUNT,DISCOUNT,STATUS,ORDERID) " +
                    "VALUES('"+pay_method+"','"+amount+"','"+discount+"','"+paymentstatus+"','"+newOID+"')";
               stmt.executeUpdate(sql);
            System.out.println("Invoice table updated");
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return 1;    
    }
    
}
