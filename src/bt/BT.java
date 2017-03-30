/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.*;
/**
 *
 * @author RK
 */
public class BT {

    /**
     * @param args the command line arguments
     */
 
    public static void main(String[] args) {
        // TODO code application logic here
        
        CS_MN mn = new CS_MN();
        
        String cs_num ;
        String ac_type;
        String address;
        String birth ;
        String in_bl ;
        String name ;
        String phone;

        // System.out.println(mn.cs_num+" "+mn.ac_type+" "+mn.address+" "+mn.birth+" "+mn.in_bl+" "+mn.name+" "+mn.phone);
      
        
      try {	
          
          //System.in.read();
       do{
        System.out.println("<------------------------------Bank Management-------------------------------------->");
        System.out.println("Show menu(1) Create Account(2) Delete Account(3) EditCustomerInfo(4) ShowCustomerList(5) DoTransact(6) SortCustomersRecord(7) Print(8)");
        System.out.print("Please select item:");
      
        Scanner scan=new Scanner(System.in);
        String input=scan.nextLine();
        
        String outcheck=input;
        switch(input){
            case "1":
                System.out.println("................Show menu.........................");
                
                System.out.print("Please insert Citizenship number:");
                input=scan.nextLine();
                mn.ShowMenu(input);               
                break;
            case "2":
                  System.out.println("................Create Account.........................");
                
                  System.out.println("Please insert Account information:");
                 
                  System.out.print("Account name:");
                  name=scan.nextLine();
                  
                  System.out.print("Account birth:");
                  birth=scan.nextLine();
                  
                  System.out.print("Account citizenship number:");
                  cs_num=scan.nextLine();
                  
                  System.out.print("Account address:");
                  address=scan.nextLine();
                  
                  System.out.print("Account phone:");
                  phone=scan.nextLine();
                  
                  System.out.print("Account initial balance:");
                  in_bl=scan.nextLine();
                  
                  System.out.print("Account accont_type:");
                  ac_type=scan.nextLine();
                  
                  mn.CreatAccount(name, birth, cs_num, address, phone, in_bl, ac_type);
                break;
            case "3":
                System.out.println("...............Delete Account........................");
                
                 System.out.print("Please insert Citizenship number:");
                 input=scan.nextLine();
                 mn.DeleteAccount(input); 
                
                break;
            case "4":
                System.out.println("................EditCustomerInfo.........................");
                
                 System.out.print("Please insert Citizenship number:");
                 input=scan.nextLine();
                 mn.EditCustomerInfo(input);
                break;
            case "5":
                System.out.println("................ShowCustomerList.........................");
                mn.ShowCustomerList();
                break;
                
            case "6":
                System.out.println("................Dotransact.........................");
               
                System.out.print("Please insert Citizenship number:");
                String cs=scan.nextLine();
                
                System.out.println("Transact type: Deposit(1)     Withdraw(2)");
                System.out.print("Please select item:");
                input=scan.nextLine();
                if(input.equals("1"))
                {        System.out.print("Deposit balance amount:");}
                else if(input.equals("2")){
                         System.out.print("Withdraw balance amount:");
                }
                String balance=scan.nextLine();                
                
                mn.DoTransact(cs,input,balance);
                break;
            
            case "7":
                 System.out.println("................SortCustomersRecord.........................");
                
                 System.out.println("Primary key: name(1)  birth(2) phone(3) address(4)");
                 
                 
                 System.out.print("Please select item:");
                 
                 
                         
                 input=scan.nextLine();
                 String check = null;
                 switch(input){
                     case "1":
                           check="name";
                         break;
                     case "2":
                           check="birth";
                         break;
                     case "3":
                            check="phone";
                         break;
                     case "4":
                            check="address";
                         break;
                 }            
                
                 mn.SortCustomersRecord(check);
                break;
            case "8":
                System.out.println("................Print.........................");
                
                System.out.print("Please insert Citizenship number:");
                input=scan.nextLine();
                mn.Print(input);
                break;
               
        
        }   
        if(outcheck.equals("")){
         break;
        }
        
       }while(1==1);     
        
      } catch (Exception e) {
           e.printStackTrace();
      }  
      
          
    }

}
