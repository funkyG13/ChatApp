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
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.util.Iterator;


/**
 *
 * @author mtsir
 */
public class ApplicationMenu {
    
    private User user;
    private List<User> list;
    private Scanner sc = new Scanner(System.in) ;
    private PreparedStatement ps;
    private Statement st;
    private Connection con;
    private ResultSet rs;
    
    public ApplicationMenu() {
    }
    
    public ApplicationMenu(User user) {
        this.user = user;
    }
    
    protected void adminsMenu(){
        System.out.println("********Admin's Choices********");
        System.out.println("1.View Users");
        System.out.println("2.Create User");
        System.out.println("3.Delete User");
        System.out.println("4.Update Users");
        System.out.println("5.Assign Role to user");
        System.out.println("6.Send Message to a user");
        System.out.println("7.View Message History");
        System.out.println("8.Edit Message History");
        System.out.println("9.Delete Message");
        System.out.println("10.Exit");
        
        System.out.println("\nSelect the number of the operation you want to"
                + " perform:");
        
        int menuChoice = sc.nextInt();
        
        switch (menuChoice) {
            case 1:
                viewUser();
                System.out.println();
                backChoice();
                break;
            case 2:
                createUser();
                System.out.println();
                backChoice();
                break;
            case 3:
                deleteUser();
                backChoice();
                break;
            case 4:
                updateUser();
                System.out.println();
                backChoice();
                break;
            case 5:
                assignRole();
                System.out.println();
                backChoice();
                break;
            case 6:
                chat();
                System.out.println();
                backChoice();
                break;
            case 7:
                viewMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 8:
                editMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 9:
                deleteMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 10:
                System.exit(0);
                break;
            default:
                System.out.println("There is no such choice");
                backChoice();
                break;
        }
    }
    
    protected void managersMenu(){
        
        System.out.println("********Manager's Choices********");
        System.out.println("1.View Message History");
        System.out.println("2.Edit the Message History");
        System.out.println("3.Delete the Message History");
        System.out.println("4.Send Message");
        System.out.println("5.Exit");
        
        System.out.println("\nSelect the number of the operation you want to"
                + " perform");
        sc = new Scanner(System.in);
        
        int menuChoice = sc.nextInt();
        switch (menuChoice) {
            case 1:
                viewMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 2:
                editMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 3:
                deleteMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 4:
                chat();
                System.out.println();
                backChoice();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("There is no such choice");
                backChoice();
                break;
        }
    }
    
    protected void supervisorMenu(){
        
        System.out.println("********Supervisor's Choices********");
        System.out.println("1.View Message History");
        System.out.println("2.Edit the Message History");
        System.out.println("3.Send Message");
        System.out.println("4.Exit");
        
        System.out.println("\nSelect the number of the operation you want to"
                + " perform");
        sc = new Scanner(System.in);
        
        int menuChoice = sc.nextInt();
        switch (menuChoice) {
            case 1:
                viewMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 2:
                editMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 3:
                chat();
                System.out.println();
                backChoice();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("There is no such choice");
                backChoice();
                break;
        }
    }
    
    protected void employeeMenu(){
        
        System.out.println("********Employee's Choices********");
        System.out.println("1.View Message History");
        System.out.println("2.Send Message");
        System.out.println("3.Exit");
        
        System.out.println("\nSelect the number of the operation you want to"
                + " perform");
        sc = new Scanner(System.in);
        int menuChoice = sc.nextInt();
        
        switch (menuChoice) {
            case 1:
                viewMsgHistory();
                System.out.println();
                backChoice();
                break;
            case 2:
                chat();
                System.out.println();
                backChoice();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("There is no such choice");
                backChoice();
                break;
        }
    }
    
    //option view user
    private void viewUser(){
        
        String viewQuery = "select * from users";
        
        try {
            con = DBConnection.connector();
            st = con.createStatement();
            rs = st.executeQuery(viewQuery);
            
            while(rs.next()){
                System.out.println("UserId: "+rs.getInt(1)+" Username: "
                        + ""+rs.getString(2)+" Password: "+rs.getString(3)+
                        " User_role: "+rs.getInt(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
    }
    //option create user
    private void createUser(){
        
        User u1 = new User();
        System.out.println("Enter the username of the user you want to create:");
        List<User> list = u1.userList();
       
        String username = sc.next();
     
        //check if the username already exists
        //username is unique
        for (int i=0; i<list.size();i++){
            
            if(list.get(i).getUsername().equals(username)){
               
                System.out.println("Username already exists");
                System.out.println();
                backChoice();
                break;          
            }else{
               u1.setUsername(username);
               break;  
            }
        }
        
        System.out.println("Enter the password of the user you want to create:");
        String password = sc.next();
        u1.setPassword(password);
        System.out.println("Enter the role of this user: ");
        int role = sc.nextInt();
        u1.setUserRole(role);
        
        String autoIncr = "ALTER TABLE users AUTO_INCREMENT = 1;";
        
        String query = "insert into users(username, password, user_role) "
                + "values(?,?,?)";
        
        try{
            con = DBConnection.connector();
            
            ps = con.prepareStatement(autoIncr);
            ps.executeUpdate();
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, role);
            ps.executeUpdate();
            

            list.add(u1);
            System.out.println("User: "+username+ " created.");
            
//            viewUser();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
    }
    
    //option delete user
    private void deleteUser(){
        
        viewUser();
        System.out.println();
        
        System.out.println("Type the username of the user you want to delete:");
        String username = sc.next();
        username = checkName(username);
        
        String deleteQuery = "delete from users where username='"+username+"'";
        
        try{
            con =DBConnection.connector();
            st = con.createStatement();
            
            
            st.executeUpdate(deleteQuery);
            System.out.println("User: "+username+" deleted.");
            
            st.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
    }
    //option update user
    private void updateUser(){
        
        viewUser();
        
        System.out.println("\nType the userId of the user you want to change:");
        int userId = sc.nextInt();
        userId = checkUserId(userId);
        
        System.out.println("Enter the new username:");
        String newUsername = sc.next();
        System.out.println("Enter the new password:");
        String newPassword = sc.next();
        System.out.println("Enter the role of the user.Type a number between"
                + " 1-4");
        int newRole = sc.nextInt();
        while(newRole>4 || newRole<1){
            System.out.println("There is no such role.Please type a number"
                    + "between 1-4 ");
            System.out.println("Type the number again.");
            newRole =  sc.nextInt();
        }
        String updateQuery = "update users set username = ? , "
                + "password= ? ,  user_role= ? where user_id ="+userId;
        
        try{
            con = DBConnection.connector();
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, newUsername);
            ps.setString(2, newPassword);
            ps.setInt(3, newRole);
            int i = ps.executeUpdate();
            
            if(i >0 ){
                System.out.println("User updated");
                User u1 = new User(userId, newUsername, newPassword, newRole);
                System.out.println("New Info:\n"+u1.toString());
            }else{
                System.out.println("Not updated");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
    }
    
    //option assign role to the user
    private void assignRole(){
        
        viewUser();
        
        System.out.println("Type the userId of the user you want to assign a"
                + " new role: ");
        int userId = sc.nextInt();
        userId = checkUserId(userId);
        System.out.println("Enter the role of the user.Type a number between"
                + " 1-4");
        int newRole = sc.nextInt();
        while(newRole>4 || newRole<1){
            System.out.println("There is no such role.Please type a number"
                    + "between 1-4 ");
            newRole =  sc.nextInt();
        }
        
        String assignRoleQuery = "update users set user_role=? where user_id="
                +userId;
        
        try{
            con = DBConnection.connector();
            ps = con.prepareStatement(assignRoleQuery);
            ps.setInt(1, newRole);
            
            if(ps.executeUpdate()>0){
                
                System.out.println("User's Role updated");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
    }
    
    //option send message
    private void chat(){
        
        viewUser();
        System.out.println("\nType the username of the user you want to"
                + " chat with:");
        String receiver = sc.next();
        // check if the input 'receiver' exists in DB
        receiver = checkName(receiver);
        System.out.println("Type the message you want to send "+receiver);
        String message = sc.next();
        message += sc.nextLine();
        String sender = user.getUsername();
        Date date = new Date();
        String filepath;
        
        String autoIncr = "ALTER TABLE msg_history AUTO_INCREMENT = 1;";
        
        String chatQuery = "insert into msg_history(sender, receiver, msg_data,"
                + " date_of_submission) values(?,?,?,?);";
        
        try{
            
            con = DBConnection.connector();
            
            //reset the auto_increment value
            ps = con.prepareStatement(autoIncr);
            ps.executeUpdate();
            
            //inserting values to msg_history
            ps = con.prepareStatement(chatQuery);
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setString(3, message);
            ps.setString(4, date.toString());
            
            if( ps.executeUpdate()>0){
                System.out.println("Message sent from "+sender+ " to "+receiver);
                System.out.println("Message stored at msg_history");
                filepath = "messageFile.txt";
                
                //write the msgs to the messageFile.txt where all msgs are stored
                FileAccess fa= new FileAccess();
                fa.fileWrite(filepath, sender, receiver, message, date.toString());
                System.out.println("Data written at "+filepath);
                System.out.println();
            }
            
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
    }
    
    private boolean viewMsgHistory(){
        
        String viewMsgQuery = "select * from msg_history";
        
        try{
            con = DBConnection.connector();
            st = con.createStatement();
            rs = st.executeQuery(viewMsgQuery);
            
            if(!rs.next()){
                System.out.println("No Messages to display.");
                return false;
            }   else{
                
                do{
                    System.out.println("Msg No: "+rs.getString("msg_id"));
                    System.out.println("Sender: "+rs.getString("sender")+" to"
                            + " Receiver: "+rs.getString("receiver"));
                    System.out.println("Message Info: ");
                    System.out.println(rs.getString("msg_data"));
                    System.out.println("Date: "+rs.getString("date_of_submission"));
                    
                }while(rs.next());
            }
            
            return true;
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DBConnection.closeCon();
        }
        return false;
    }
    // option edit message
    private void editMsgHistory(){
        
        if(viewMsgHistory()){
            
            System.out.println("Select the number of the message you want "
                    + "to edit:");
            int selectMsg = sc.nextInt();
            
            System.out.println("Write the new edited message:");
            String editMsg = sc.next();
            editMsg += sc.nextLine();
            Date date = new Date();
            String editMsgQuery = "update msg_history set msg_data='"
                    +editMsg+"',"+ " date_of_submission='"+date.toString()+"' "
                    + "where msg_id='"+selectMsg+"'";
            
            
            try{
                con = DBConnection.connector();
                ps = con.prepareStatement(editMsgQuery);
                
                if(ps.executeUpdate()>0){
                    System.out.println("new msg: "+editMsg+" \nat msg_id: "
                            +selectMsg+" ");
                }else{
                    System.out.println("Cannot update the message number "
                            +selectMsg+ ".\nMessage number "+selectMsg+
                            " does not exist!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                DBConnection.closeCon();
            }
        }else{
            System.out.println("No messages to edit.");
        }
    }
    // option delete message from the msg_history db
    private void deleteMsgHistory(){
        
        if(viewMsgHistory()){
            
            System.out.println("\nType the number of the message you want "
                    + "to delete");
            int selectedMsgId = sc.nextInt();
            
            String deleteMsgQuery = "delete from msg_history where msg_id='"
                    +selectedMsgId+"'";
            
            try{
                con = DBConnection.connector();
                ps = con.prepareStatement(deleteMsgQuery);
                
                if(ps.executeUpdate()>0){
                    System.out.println("Message No "+selectedMsgId+" deleted");
                }            else{
                    System.out.println("Cannot delete the message number "
                            +selectedMsgId+".\nMessage number "
                            +selectedMsgId+" does not exist!");
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                DBConnection.closeCon();
            }
        }else{
            System.out.println("No messages to delete.");
        }
    }
    
    //method to access the main menu of the user
    private void backChoice(){
        
        int back;
        System.out.println("*Back to Menu: Press 0\n"
                + "**Exit: Press 1. ");
        back = sc.nextInt();
        do {
            if(back == 0){
                if(user.getUsername().equals("admin")){
                    adminsMenu();
                }else if(user.getUsername().equals("manager")){
                    managersMenu();
                }else if(user.getUsername().equals("supervisor")){
                    supervisorMenu();
                }else {
                    employeeMenu();
                }
            }else if(back == 1){
                System.exit(0);
            }else{
                System.out.println("Please retype your answer:");
                back = sc.nextInt();
            }
        } while(back !=0 || back !=1);
        
    }
    
    //check if the given name exists
    private String checkName(String name){
        
        list = user.userList();
    
        boolean nameExists = false;
        
        do{
            
            for (int i=0; i<list.size();i++){
                
                if(list.get(i).getUsername().equals(name)){
                    
                    System.out.println("So you selected "+name);
                    nameExists =true;
                    break;
                }
            }
            if(nameExists == false){
                System.out.println("There is nobody with this username.");
                System.out.println("Rewrite the name");
                name = sc.next();
            }
        }  while(nameExists == false);
        
        return name;
}
    
    //check if the given password exists
    private int checkUserId(int id){
        
    list = user.userList();
    
        boolean userId = false;
        
        do{
            
            for (int i=0; i<list.size();i++){
                
                if(list.get(i).getUserId()==id){
                    
                    System.out.println("So you selected "+list.get(i).getUsername());
                    userId =true;
                    break;
                }
            }
            if(userId == false){
                System.out.println("There is nobody with this userId.");
                System.out.println("Rewrite the id");
                id = sc.nextInt();
            }
        }  while(userId == false);
        
        return id;

}


}