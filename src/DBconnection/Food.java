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
    
    public void updateFood(String foodID,String foodname,String category,String price,String dailyQty,String qtyType) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
                String sql="UPDATE FOOD " +
                            "SET FOOD_NAME = '"+foodname+"', CATEGORY= '"+category+"', QUANTITY_TYPE = '"+qtyType+"', UNIT_PRICE = '"+price+"', DAILY_QUANTITY = '"+dailyQty+"' " +
                            " where FOOD_ID='"+foodID+"'";
                stmt.executeUpdate(sql);
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
                fid=rs.getString("FOOD_ID");
            } 
        }
        finally{
            c.close(); 
        }
        return fid;  
    } 
    
    public String getFoodName(String fid) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String fname="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select FOOD_NAME from FOOD where FOOD_ID='"+fid+"'"); //SQL stetment
            while(rs.next()){
                fname=rs.getString("FOOD_NAME");
            } 
        }
        finally{
            c.close(); 
        }
        return fname;  
    }
    
    public int foodCountOfTheOrder(String food_name,String orderID) throws Exception{ //qty of food in given order  
        Connection c= getConnection();//get the connection using inheritance
        int qty=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("Select QUANTITY FROM ORDER_FOOD t INNER JOIN FOOD f ON t.FOOD_ID=f.FOOD_ID WHERE t.ORDER_NUMBER='"+orderID+"' AND f.FOOD_NAME='"+food_name+"';"); //SQL stetment
            while(rs.next()){
                qty=rs.getInt("QUANTITY");
            } 
        }
        finally{
            c.close(); 
        }
        return qty;  
    }
    
     //availabitily of a food
    public boolean isFoodAvailability(String food_name,int num) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int avalable=0; System.out.println("Available checking ");
        getDate obj =new getDate();
        String date=obj.dateOnly();
        boolean x=false;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            //max qty for a day
            ResultSet rs = stmt.executeQuery("SELECT DAILY_QUANTITY FROM food WHERE FOOD_NAME='"+food_name+"'");
            while(rs.next()){
                avalable=rs.getInt("DAILY_QUANTITY");              
            }
            System.out.println("Daily Available qty: "+avalable);
            //Get sold qty today
            rs = stmt.executeQuery("select  SUM(QUANTITY) AS Availability from FOOD INNER JOIN ORDER_FOOD ON food.FOOD_ID=ORDER_FOOD.FOOD_ID" +
                            " where ORDER_FOOD.ORDER_NUMBER IN (select ORDER_NUMBER from ORDER_T" +
                                " where ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND FOOD.FOOD_NAME='"+food_name+"'" +
                                    " GROUP BY ORDER_FOOD.FOOD_ID;"); //SQL stetment
            while(rs.next()){
                avalable-=rs.getInt("Availability");              
            }
            System.out.println("Today can serve : "+avalable);
            if((avalable-num)>0)
                x=true;
            
        }
        finally{
            c.close(); 
        }
        return x;  
    }
    
    
    public int FoodAvailableCount(String food_name) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        int avalable=0; System.out.println("Available checking ");
        getDate obj =new getDate();
        String date=obj.dateOnly();
        boolean x=false;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            //max qty for a day
            ResultSet rs = stmt.executeQuery("SELECT DAILY_QUANTITY FROM food WHERE FOOD_NAME='"+food_name+"'");
            while(rs.next()){
                avalable=rs.getInt("DAILY_QUANTITY");              
            }
            System.out.println("Daily Available qty: "+avalable);
            //Get sold qty today
            rs = stmt.executeQuery("select  SUM(QUANTITY) AS Availability from FOOD INNER JOIN ORDER_FOOD ON food.FOOD_ID=ORDER_FOOD.FOOD_ID" +
                            " where ORDER_FOOD.ORDER_NUMBER IN (select ORDER_NUMBER from ORDER_T" +
                                " where ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND FOOD.FOOD_NAME='"+food_name+"'" +
                                    " GROUP BY ORDER_FOOD.FOOD_ID;");
            while(rs.next()){
                avalable-=rs.getInt("Availability");              
            }
            System.out.println("Today can serve : "+avalable);
        }
        finally{
            c.close(); 
        }
        return avalable;  
    }
    
    public String newFID(String id) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        String FID="";
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select FOOD_ID from FOOD WHERE FOOD_ID LIKE '"+id+"%' ORDER BY FOOD_ID DESC LIMIT 1"); //SQL stetment
            while(rs.next()){
                FID=rs.getString("FOOD_ID");
            }
            FID=FID.substring(2);
            String temp=Integer.toString((Integer.parseInt(FID))+1);
            System.out.println(temp.length());
            for(int i=3;i>temp.length();i--){
                id=id+"0";
            }
            FID=id+Integer.toString((Integer.parseInt(FID))+1);
        }
        finally{
            c.close(); 
        }
        return FID;    
    }
    
    public boolean deleteFood(String fID) throws Exception{
        Connection c= getConnection();//get the connection using inheritance
        boolean success=false;
        try{ 
            int count=0;
            Statement stmt = c.createStatement();//Prepare statement                        
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM order_food WHERE ORDER_NUMBER IN (" +
                                    "SELECT order_number FROM order_t WHERE status!='Completed') " +
                                            "AND FOOD_ID='"+fID+"';"); //SQL stetment
            while(rs.next()){
                count=rs.getInt("COUNT(*)");
            } 
            if(count==0){
                String sql="DELETE FROM FOOD WHERE FOOD_ID='"+fID+"';";
                stmt.executeUpdate(sql);
                success=true;
            }
                
        }
        finally{
            c.close(); 
            return success;
        }
    }
    
    public boolean isFoodAlreadyExist(String food_name) throws Exception{   
        Connection c= getConnection();//get the connection using inheritance
        byte count=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select COUNT(*) AS c from FOOD where FOOD_NAME='"+food_name+"'"); //SQL stetment
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
