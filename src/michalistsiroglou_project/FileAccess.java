/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package michalistsiroglou_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mtsir
 */
public class FileAccess {
    
    public FileAccess() {
        
    }
    
    protected void fileWrite(String filepath, String sender, String receiver,
            String message,String date){
        
        FileWriter fw = null;
        try {
            fw = new FileWriter(filepath, true);
            BufferedWriter bf = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bf);
            pw.println("From: "+sender);
            pw.println("To: "+receiver);
            pw.println("Message info:");
            pw.println(message);
            pw.println(date);
            pw.println();
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(ApplicationMenu.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                if(fw!= null)
                    fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ApplicationMenu.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }  
}
