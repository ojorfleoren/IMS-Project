/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author OJT25
 */
public class DBConnection {
    Connection conn = null;
    /**
     * @param args the command line arguments
     */
        public static Connection connectDB(){    
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
            System.out.println("Connected");
            
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Connection Failed" +e);
            return null;
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        connectDB();
        new LogIn().setVisible(true);
    }
}
