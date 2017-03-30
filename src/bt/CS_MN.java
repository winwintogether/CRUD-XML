/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



/**
 *
 * @author RK
 */
public class CS_MN {
  
    public String name;
    public String birth;
    public String cs_num;
    public String address;
    public String phone;
    public String in_bl;
    public String ac_type;
    public File inputFile;
    public DocumentBuilderFactory dbFactory;
    public DocumentBuilder dBuilder;
    public NodeList nList;
    public   Document doc;
    public CS_MN(){
      try{
        inputFile = new File("data.xml");
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
       
      }catch (Exception e) {
           e.printStackTrace();
      }
    }
    
    public void ShowMenu(String check){
        try {        
             
         doc.getDocumentElement().normalize();
      //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         nList = doc.getElementsByTagName("node");
       //  System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :"  + nNode.getNodeName());
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {                
             
               Element eElement = (Element) nNode;
             if(eElement.getElementsByTagName("citizenship_number").item(0).getTextContent().equals(check)){
              // System.out.println("index : " + eElement.getAttribute("index"));
               System.out.println("name : "   + eElement.getElementsByTagName("name").item(0).getTextContent());
               System.out.println("birth : "   + eElement.getElementsByTagName("birth").item(0).getTextContent());
               System.out.println("citizenship number : "  + eElement.getElementsByTagName("citizenship_number").item(0).getTextContent());
               System.out.println("address : "  + eElement.getElementsByTagName("address").item(0).getTextContent());
               System.out.println("phone number : "  + eElement.getElementsByTagName("phone").item(0).getTextContent());
               System.out.println("initial balance : "  + eElement.getElementsByTagName("initial_balance").item(0).getTextContent());
               System.out.println("account type : "  + eElement.getElementsByTagName("account_type").item(0).getTextContent());
                }
            }
         }
      }catch (Exception e) {
           e.printStackTrace();
      }
    };
  
    public void CreatAccount(String name, String birth, String cs_num,String address,String phone,String in_bl,String ac_type) throws TransformerException{
       try{            
         Element root =doc.getDocumentElement();
        
         Element newnode=doc.createElement("node");
         root.appendChild(newnode);
         
         Attr attr = doc.createAttribute("index");
         
       //  System.out.println("......Nlist length......"+nList.getLength());
         attr.setValue(cs_num);
         newnode.setAttributeNode(attr);
                  
         Element e_name = doc.createElement("name");
         e_name.appendChild( doc.createTextNode(name));
         
         newnode.appendChild(e_name);
         
         Element e_birth = doc.createElement("birth");
         e_birth.appendChild( doc.createTextNode(birth));
         newnode.appendChild(e_birth);
         
         Element e_cs_num = doc.createElement("citizenship_number");
         e_cs_num.appendChild( doc.createTextNode(cs_num));
         newnode.appendChild(e_cs_num);
         
          Element e_address = doc.createElement("address");
          e_address.appendChild( doc.createTextNode(address));
         newnode.appendChild(e_address);
         
         Element e_phone = doc.createElement("phone");
         e_phone.appendChild( doc.createTextNode(phone));
         newnode.appendChild(e_phone);
         
         Element e_in_bl = doc.createElement("initial_balance");
         e_in_bl.appendChild( doc.createTextNode(in_bl));
         newnode.appendChild(e_in_bl);
         
         Element e_ac_type = doc.createElement("account_type");
         e_ac_type.appendChild( doc.createTextNode(ac_type));
         newnode.appendChild(e_ac_type);
         
         
         
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
         DOMSource source = new DOMSource(doc);
         StreamResult outfile = new StreamResult(inputFile);
         transformer.transform(source, outfile);         
        
        // StreamResult console = new StreamResult(System.out);
     //    transformer.transform(source, console);
         
       }catch(Exception e){
        e.printStackTrace();
       }
    };
    
    public void DoTransact(String cs_num,String check, String balance){
       
          try {        
             
         doc.getDocumentElement().normalize();
      //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         nList = doc.getElementsByTagName("node");
       //  System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :"  + nNode.getNodeName());
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {                
             
               Element eElement = (Element) nNode;
             if(eElement.getElementsByTagName("citizenship_number").item(0).getTextContent().equals(cs_num)){
              
                  int u_balance=0;
                 if(check.equals("1")){
                    u_balance=Integer.valueOf(eElement.getElementsByTagName("initial_balance").item(0).getTextContent()).intValue()+Integer.valueOf(balance).intValue();
                 }
                 else if(check.equals("2")){
                    u_balance=Integer.valueOf(eElement.getElementsByTagName("initial_balance").item(0).getTextContent()).intValue()-Integer.valueOf(balance).intValue();
                 }
                  
                   eElement.getElementsByTagName("initial_balance").item(0).setTextContent(String.valueOf(u_balance));
                
                    
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                    DOMSource source = new DOMSource(doc);
                    StreamResult outfile = new StreamResult(inputFile);
                    transformer.transform(source, outfile);                     
                    
                }
            }
         }
         System.out.println("...................................");
         this.ShowMenu(cs_num);
      }catch (Exception e) {
           e.printStackTrace();
      } 
        
    };
    
    public void EditCustomerInfo(String check){
      try {        
             
         doc.getDocumentElement().normalize();
      //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         nList = doc.getElementsByTagName("node");
       //  System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :"  + nNode.getNodeName());
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {                
             
               Element eElement = (Element) nNode;
             if(eElement.getElementsByTagName("citizenship_number").item(0).getTextContent().equals(check)){
                  System.out.println("Please insert Update information:");
                 
                  Scanner scan=new Scanner(System.in);
                  System.out.print("Account name:");
                  name=scan.nextLine();
                  
                  System.out.print("Account birth:");
                  birth=scan.nextLine();
                                                     
                  System.out.print("Account address:");
                  address=scan.nextLine();
                  
                  System.out.print("Account phone:");
                  phone=scan.nextLine();
                  
                  System.out.print("Account initial balance:");
                  in_bl=scan.nextLine();
                  
                  System.out.print("Account accont_type:");
                  ac_type=scan.nextLine();
                  
                  eElement.getElementsByTagName("name").item(0).setTextContent(name);
                  eElement.getElementsByTagName("birth").item(0).setTextContent(birth);
                  eElement.getElementsByTagName("address").item(0).setTextContent(address);
                  eElement.getElementsByTagName("phone").item(0).setTextContent(phone);
                  eElement.getElementsByTagName("initial_balance").item(0).setTextContent(in_bl);
                  eElement.getElementsByTagName("account_type").item(0).setTextContent(ac_type);
                  
                    
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                    DOMSource source = new DOMSource(doc);
                    StreamResult outfile = new StreamResult(inputFile);
                    transformer.transform(source, outfile);                                
                }
            }
         }
      }catch (Exception e) {
           e.printStackTrace();
      }
    };
    
    public void SortCustomersRecord(String check){
        try{ 
            
         Bst b = new Bst();
             
         doc.getDocumentElement().normalize();
    
         nList = doc.getElementsByTagName("node");
      
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
          
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {                
             
               Element eElement = (Element) nNode;
           
              
                b.insert(eElement.getElementsByTagName(check).item(0).getTextContent(),eElement.getAttribute("index").toString());
                          
            }
         }
        
         System.out.println("order by "+check+": Citizenship number");
         b.display(b.root);
         
        }catch(Exception e){
         e.toString();
                 
        }
    };
    public void DeleteAccount(String check){
         try {        
             
         doc.getDocumentElement().normalize();
      //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         nList = doc.getElementsByTagName("node");
       //  System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :"  + nNode.getNodeName());
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {                
             
               Element eElement = (Element) nNode;
             if(eElement.getAttribute("index").equals(check)){
                   Element root =doc.getDocumentElement();
                   root.removeChild(nNode);
                 
                   
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                    DOMSource source = new DOMSource(doc);
                    StreamResult outfile = new StreamResult(inputFile);
                    transformer.transform(source, outfile);
                }
            }
         }
      }catch (Exception e) {
           e.printStackTrace();
      }
    };
    
    public void Print(String check){
      try {        
             
         doc.getDocumentElement().normalize();
      //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         nList = doc.getElementsByTagName("node");
       //  System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :"  + nNode.getNodeName());
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {                
             
               Element eElement = (Element) nNode;
             if(eElement.getElementsByTagName("citizenship_number").item(0).getTextContent().equals(check)){
              // System.out.println("index : " + eElement.getAttribute("index"));
               System.out.println("name : "   + eElement.getElementsByTagName("name").item(0).getTextContent());
               System.out.println("birth : "   + eElement.getElementsByTagName("birth").item(0).getTextContent());
               System.out.println("citizenship number : "  + eElement.getElementsByTagName("citizenship_number").item(0).getTextContent());
               System.out.println("address : "  + eElement.getElementsByTagName("address").item(0).getTextContent());
               System.out.println("phone number : "  + eElement.getElementsByTagName("phone").item(0).getTextContent());
               System.out.println("initial balance : "  + eElement.getElementsByTagName("initial_balance").item(0).getTextContent());
               System.out.println("account type : "  + eElement.getElementsByTagName("account_type").item(0).getTextContent());
                }
            }
         }
      }catch (Exception e) {
           e.printStackTrace();
      }
    };
    
    public void ShowCustomerList(){
     try {        
             
         doc.getDocumentElement().normalize();
        // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         nList = doc.getElementsByTagName("node");
        // System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :"   + nNode.getNodeName());
           System.out.println("---------");
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
              // System.out.println("index : " + eElement.getAttribute("index"));
               System.out.println("name : "   + eElement.getElementsByTagName("name").item(0).getTextContent());
               System.out.println("birth : "   + eElement.getElementsByTagName("birth").item(0).getTextContent());
               System.out.println("citizenship number : "  + eElement.getElementsByTagName("citizenship_number").item(0).getTextContent());
               System.out.println("address : "  + eElement.getElementsByTagName("address").item(0).getTextContent());
               System.out.println("phone number : "  + eElement.getElementsByTagName("phone").item(0).getTextContent());
               System.out.println("initial balance : "  + eElement.getElementsByTagName("initial_balance").item(0).getTextContent());
               System.out.println("account type : "  + eElement.getElementsByTagName("account_type").item(0).getTextContent());
            }
         }
      }catch (Exception e) {
           e.printStackTrace();
      }
    };
    
}
