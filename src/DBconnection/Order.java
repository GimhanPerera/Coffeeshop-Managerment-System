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
                lID=rs.getString("ORDER_NUMBER");
            }
            lID=lID.substring(2);
            String zeros="";
            for(int i=(Integer.toString((Integer.parseInt(lID))+1)).length();i<5;i++){
                zeros=zeros+"0";
            }
            lID="OR"+zeros+Integer.toString((Integer.parseInt(lID))+1);
        }
        finally{
            c.close(); 
        }
        return lID;    
    }
    
    public int placeOrder(String o_type,String cID,String[] fID,int[] qty,String pay_method,int amount ,int discount,String paymentstatus,String tables[]) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            String newOID=newOrderID();
            System.out.println("==============================");
            System.out.println("Custmer ID : "+cID);
            System.out.println("Order Number : "+newOID);
            Statement stmt = c.createStatement();//Prepare statement
            //Update order table
            getDate obj=new getDate();
            String sql;
            if(o_type.equals("Takeaway")){
                sql="INSERT INTO ORDER_T (ORDER_NUMBER, ORDER_TYPE, ORDER_DATETIME, END_TIME, CUSTOMER_ID,STATUS) " +
                    "VALUES ('"+newOID+"', '"+o_type+"', '"+obj.dateAndTime()+"','"+obj.dateAndTime()+"','"+cID+"','Pending')";
                stmt.executeUpdate(sql);
            }else{
                sql="INSERT INTO ORDER_T (ORDER_NUMBER, ORDER_TYPE, ORDER_DATETIME, CUSTOMER_ID,STATUS) " +
                    "VALUES ('"+newOID+"', '"+o_type+"', '"+obj.dateAndTime()+"','"+cID+"','Pending')";
                stmt.executeUpdate(sql);
                for(String q:tables){
                    sql="INSERT INTO ORDER_TABLE (ORDER_NUMBER, TABLE_NO) " +
                    "VALUES ('"+newOID+"', '"+q+"')";
                    stmt.executeUpdate(sql);
                    System.out.println("Table reserved : "+q);
                }
            }
            
            System.out.println("+ Order table updated");
            //Update food_order table
            for(int i=0;i<fID.length;i++)
            {
               sql="INSERT INTO ORDER_FOOD(ORDER_NUMBER,FOOD_ID,QUANTITY) VALUES('"+newOID+"','"+fID[i]+"','"+qty[i]+"')";
               stmt.executeUpdate(sql);
            }System.out.println("+ food_order table updated");
            //Generate invoice table
            //invoice ID autogenerate bt sql
            sql="INSERT INTO INVOICE(PAYMENT_METHOD,AMOUNT,DISCOUNT,STATUS,ORDERID) " +
                    "VALUES('"+pay_method+"','"+amount+"','"+discount+"','"+paymentstatus+"','"+newOID+"')";
               stmt.executeUpdate(sql);
            System.out.println("+ Invoice table updated");
            System.out.println("==============================");
        }
        finally{
            c.close(); 
        }
        return 1;    
    }
    
    public void cancelOrder(String OID) throws Exception{
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="DELETE FROM ORDER_FOOD WHERE ORDER_NUMBER='"+OID+"';";
                stmt.executeUpdate(sql);
                sql="DELETE FROM INVOICE WHERE ORDERID='"+OID+"';";
                stmt.executeUpdate(sql);
                sql="DELETE FROM ORDER_T WHERE ORDER_NUMBER='"+OID+"';";
                stmt.executeUpdate(sql);
                sql="DELETE FROM ORDER_TABLE WHERE ORDER_NUMBER='"+OID+"';";
                stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    public void completeOdr(String OID,boolean cashPayemt) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            getDate obj=new getDate();
            Statement stmt = c.createStatement();//Prepare statement
            String sql="update ORDER_T set STATUS='Completed',END_TIME='"+obj.timeOnly()+"' where ORDER_NUMBER='"+OID+"'"; //SQL stetment
            stmt.executeUpdate(sql);
            if(cashPayemt)
                sql="update INVOICE set STATUS='Paid',PAYMENT_METHOD='CASH' where ORDERID='"+OID+"'"; //SQL stetment
            else
                sql="update INVOICE set STATUS='Paid',PAYMENT_METHOD='CARD' where ORDERID='"+OID+"'";
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    //make true means change to making
    //make true means change to finish
    public void setMakingFinishStatus(String oID,boolean make) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            String sql="update ORDER_T set STATUS='Finish' where ORDER_NUMBER='"+oID+"'"; //SQL stetment
            if(make)
                sql="update ORDER_T set STATUS='Making' where ORDER_NUMBER='"+oID+"'";
            stmt.executeUpdate(sql);
        }
        finally{
            c.close(); 
        }
    }
    
    public void updateOrder(String OID,String fid[],int qty[],int total) throws Exception{//NOT COMPLETE
        //Need to check order table and order_food table
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="DELETE FROM ORDER_FOOD WHERE ORDER_NUMBER='"+OID+"';";
                stmt.executeUpdate(sql);
                sql="update INVOICE set AMOUNT='"+total+"' where ORDERID='"+OID+"'";
                stmt.executeUpdate(sql);
                for(int i=0;i<fid.length;i++){
                    sql="INSERT INTO ORDER_FOOD(ORDER_NUMBER,FOOD_ID,QUANTITY) VALUES('"+OID+"','"+fid[i]+"','"+qty[i]+"')";
                    stmt.executeUpdate(sql);
                }     
        }
        finally{
            c.close(); 
        }
    }
    
    public String getTablelist(String ordID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String tables="";
        try{ 
            boolean first=true;
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select TABLE_NO from ORDER_TABLE WHERE ORDER_NUMBER='"+ordID+"'"); //SQL stetment
            while(rs.next()){
                if(first){
                    tables=rs.getString("TABLE_NO");
                    first=false;
                }
                else{
                    tables=tables+", "+rs.getString("TABLE_NO");
                }   
            }
        }
        finally{
            c.close(); 
        }
        return tables;    
    }
    
}
