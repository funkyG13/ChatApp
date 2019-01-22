/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package michalistsiroglou_project;

import java.io.Console;
import michalistsiroglou_project.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author mtsir
 */
public class LoginScreen {
    
    private Scanner sc = new Scanner(System.in);
    private User user;
    private PreparedStatement stmt;
    private ResultSet rs;
    private Connection con;
    private Statement st;

    public LoginScreen() {
        System.out.println("Please type your login credentials:");
    }
    
    protected User checkUser(String username, String password){

        user = new User(username, password);

        String sqlLogin = "select * from users where username=? and"
                            + " password= ? ";
        
        if(username.equals("")||password.equals("")){
            System.out.println("No username or password entered.");
        }
        else{

        List<User> list = user.userList();
        
        try {
        
            stmt = DBConnection.connector().prepareStatement(sqlLogin);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            rs= stmt.executeQuery();
            
            if(rs.next()){
            for (User user1 : list) {
                
                if(user1.getUsername().equals(rs.getString("username")) && 
                        user1.getPassword().equals(rs.getString("password"))){
                  
                user1 = new User(rs.getInt("user_id"), username, password, 
                        rs.getInt("user_role"));
//                System.out.println(user1.toString());
                return user1;   
            } 
               user = user1;
            } 
            } else{
                    System.out.println("Wrong username or password");
                }
             
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        return user;
    }

}
        


 
    
