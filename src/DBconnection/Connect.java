/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBconnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gimhan
 */
public class Connect{   
    public Connection getConnection() throws Exception{//Connect with DB
        Class.forName("com.mysql.cj.jdbc.Driver");//Load and relocalhost:3306gister the JDBC driver
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/COFFESHOP","root","root");//Establish the connection
        System.out.println("Database connection is success\n");
        return con;
    }
}




