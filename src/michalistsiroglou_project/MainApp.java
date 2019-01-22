/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package michalistsiroglou_project;

import java.util.Scanner;

/**
 *
 * @author mtsir
 */
public class MainApp {
    
    public static void main(String[] args){
     
        Scanner sc = new Scanner(System.in);

        System.out.println("*************Welcome*************");
        
        LoginScreen ls = new LoginScreen(); 
        System.out.println("");
        User user;
        
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");

        String password = sc.nextLine(); 
        
        user = ls.checkUser(username, password);
        
            if((user.getUserRole())==4){
                System.out.println("Welcome "+user.getUsername());
                ApplicationMenu ap = new ApplicationMenu(user);
                ap.adminsMenu();
           }
            if((user.getUserRole())==3){
                System.out.println("Welcome "+user.getUsername());
                ApplicationMenu ap = new ApplicationMenu(user);
                ap.managersMenu();
           }

            if((user.getUserRole())==2){
                System.out.println("Welcome "+user.getUsername());
                ApplicationMenu ap = new ApplicationMenu(user);
                ap.supervisorMenu();
           }

            if((user.getUserRole())==1){
                System.out.println("Welcome "+user.getUsername());
                ApplicationMenu ap = new ApplicationMenu(user);
                ap.employeeMenu();
           }
  } 
        }
    

