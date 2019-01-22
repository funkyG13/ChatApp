/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package michalistsiroglou_project;

import michalistsiroglou_project.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mtsir
 */
public class User {
    
    private int userId;
    private String username;
    private String password;
    private int userRole;
    private Connection con;
    private PreparedStatement st;
    private ResultSet rs;
    
    public User() {}
    
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(int userId, String username, String password, int userRole) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
    
    public User(String username, String password, int userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getUserRole() {
        return userRole;
    }
    
    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        return "User{" + "username= " + username + ", password= " + password + ", userRole= " + userRole + '}'+"\n";
    }
    
    public ArrayList<User> userList(){
        
        ArrayList<User> list = new ArrayList<>();
        String query= "select * from users";
        
        try{
            con = DBConnection.connector();
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.userId   = rs.getInt("user_id");
                user.username = rs.getString("username");
                user.password = rs.getString("password");
                user.userRole = rs.getInt("user_role");
                
                list.add(user);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
        return list;
    }
    
    
}
