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
    
    public String[] checkAvailability(int pax) throws Exception{//NOT COMPLETED   
        Connection c= getConnection();//get the connection using inheritance
        boolean can=true;
        String notcan[]={"cant"};
        String res_tables[];
        String tid="";
        try{ 
            getDate obj =new getDate();
            String date=obj.dateOnly();
            Statement stmt = c.createStatement();//Prepare statement
            int ava_count=0;int x=0;
            //check available table count
            ResultSet rs = stmt.executeQuery("select COUNT(*) AS coun from  ORDER_TABLE o WHERE o.ORDER_NUMBER IN (select ORDER_NUMBER from ORDER_T" +
                                                    " where ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59' AND status != 'Completed')"); //SQL stetment
            while(rs.next()){
                ava_count-=rs.getInt("coun");
            }
            rs = stmt.executeQuery("select COUNT(*) AS coun from TABLE_T"); //SQL stetment
            while(rs.next()){
                ava_count=rs.getInt("coun")-ava_count;
            }   
            System.out.println("TESTING1"+ava_count);
            if(ava_count==0){
                c.close(); 
                return notcan;
            }
            String table[]=new String[ava_count];
            byte chairs[]=new byte[ava_count];
            boolean reserved[]=new boolean[ava_count];
            for (int i = 0; i < reserved.length; i++) {
                reserved[i]=false;
            }
            
            //Available tables and thier chair count
            rs = stmt.executeQuery("select table_no,chairs from TABLE_T WHERE table_no NOT IN (select DISTINCT t.table_no from  ORDER_T o INNER JOIN ORDER_TABLE ot ON o.ORDER_NUMBER=ot.ORDER_NUMBER INNER JOIN TABLE_T t ON ot.TABLE_NO=t.TABLE_NO " +
                            "where o.ORDER_NUMBER NOT IN (select ORDER_NUMBER from ORDER_T " +
                                        "where ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59' AND o.status != 'Completed'))"); //SQL stetment
            while(rs.next()){
                    table[x]=rs.getString("TABLE_NO");
                    chairs[x]=rs.getByte("chairs");
                    x++;
            }
            while(pax>0){//7
                int check=pax;
                if(pax<=2){
                    for (int i = 0; i < table.length; i++) {
                        if(chairs[i]==2 && reserved[i]==false){
                            reserved[i]=true;
                            pax-=2;
                            break;
                        }
                    }
                }
                else{
                    for (int i = 0; i < table.length; i++) {
                        if(chairs[i]==4 && reserved[i]==false){
                            reserved[i]=true;
                            pax-=4;
                            break;
                        }
                    }
                }System.out.println("PAX  "+pax);
                if(check==pax){
                    can=false;
                    break;
                }    
            }
            //prepare reserved table array
            x=0;
        for (int i = 0; i < reserved.length; i++) {
            if(reserved[i]==true)
                x++;
        }
        res_tables = new String[x];
        x=0;
        for (int i = 0; i < table.length; i++) {
            if(reserved[i]==true){
                res_tables[x]=table[i];
                x++;
                System.out.print("Recieved "+table[i]);
                System.out.println(" "+chairs[i]);
            }    
        }    
        }
        finally{
            c.close(); 
        }
        if(can==false)
            return notcan;
        
        return res_tables;  
    }    
}
