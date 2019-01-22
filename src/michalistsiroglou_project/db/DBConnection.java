/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package michalistsiroglou_project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mtsir
 */
public class DBConnection {
    
    public static Connection connector () throws SQLException{
        
        String USER = "root";
        String PASSWORD = "1234";
        String UrlDb = "jdbc:mysql://localhost:3306/chat_app?characterEncoding=utf8";
        
        Connection conn = DriverManager.getConnection(UrlDb, USER, PASSWORD);
        
        return conn;
    }
    public static void closeCon(){
        
        try {
            if(connector()!=null)
                connector().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
