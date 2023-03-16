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
public class Food extends Connect{
    private String foodID="";
    private String foodname="";
    private int price=0;
    private int dailyQty=0;
    private String category="";
    private String qtyType="";
    
    public Food(){  
    }
    
    public Food(String fID) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select * from FOOD where FOOD_ID='"+fID+"'"); //SQL stetment
            while(rs.next()){
                foodID=rs.getString("FOOD_ID");
                foodname=rs.getString("FOOD_NAME");
                price=rs.getInt("UNIT_PRICE");
                dailyQty=rs.getInt("DAILY_QUANTITY");
                category=rs.getString("CATEGORY");
                qtyType=rs.getString("QUANTITY_TYPE");
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
    
    public String getfoodID(){
        return this.foodID;
    }
    public String getfoodName(){
        return this.foodname;
    }
    public String getPrice(){
        return Integer.toString(this.price);
    }
    public String getDailyQty(){
        return Integer.toString(this.dailyQty);
    }
    public String getCategory(){
        return this.category;
    }
    public String getQtyType(){
        return this.qtyType;
    }
    
    public void updateFood(String foodID,String foodname,String price,String dailyQty,String qtyType) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="UPDATE FOOD " +
                            "SET FOOD_NAME = '"+foodname+"', CATEGORY= '"+category+"', QUANTITY_TYPE = '"+qtyType+"', UNIT_PRICE = '"+price+"', DAILY_QUANTITY = '"+dailyQty+"' " +
                            " where FOOD_ID='"+foodID+"'";
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
    
    public void addNewFood(String foodID,String foodname,String category, String price,String dailyQty,String qtyType) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="insert into FOOD VALUES " +
                            "('"+foodID+"','"+foodname+"','"+category+"','"+qtyType+"','"+price+"','"+dailyQty+"'); ";
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
    
    public String getFoodID(String food_name) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String fid="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select FOOD_ID from FOOD where FOOD_NAME='"+food_name+"'"); //SQL stetment
            while(rs.next()){
                fid=rs.getString("FOOD_ID");//get the value to variable "fname"
            } 
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return fid;  
    } 
    
    public String newFID(String id) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String FID="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select FOOD_ID from FOOD WHERE FOOD_ID LIKE '"+id+"%' ORDER BY FOOD_ID DESC LIMIT 1"); //SQL stetment
            while(rs.next()){
                FID=rs.getString("FOOD_ID");//get the value to variable "fname"
            }
            FID=FID.substring(2);
            String temp=Integer.toString((Integer.parseInt(FID))+1);
            System.out.println(temp.length());
            for(int i=3;i>temp.length();i--){
                id=id+"0";
            }
            FID=id+Integer.toString((Integer.parseInt(FID))+1);
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            c.close(); 
        }
        return FID;    
    }
    
    public void deleteFood(String fID) throws Exception{//NOT COMPLETE
        //Need to check order table and order_food table
        Connection c= getConnection();//get the connection using inheritance
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="DELETE FROM FOOD WHERE FOOD_ID='"+fID+"';";
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
