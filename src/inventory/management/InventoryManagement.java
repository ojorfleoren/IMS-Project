/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventory.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @Gabawan
 */
public class InventoryManagement {
   Connection conn = null;
    /**
     * @param args the command line arguments
     *//*
        public static Connection connectDB(){    
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
            System.out.println("Connected");
            
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Connection Failed" +e);
            return null;
        }*/
    
    public static void main(String[] args) {
    // TODO code application logic here
    //connectDB();
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new LogIn().setVisible(true);
        }
    });
  }
}
