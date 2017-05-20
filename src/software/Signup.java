/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;
import static software.Seller.newArray;

/**
 *
 * @author Daniela
 */
import java.util.*;
import java.io.*;

public class Signup {
    Login a = new Login();
    
    Scanner in = new Scanner(System.in);
    
    private String userID;
    private String name;
    private String password;
    private int role;
    
//    public Signup(){}
    
    public Signup(String name, String username,String password, int role) throws IOException{
        this.name = name;
        this.userID = username;
        this.password = password;
        this.role = role;
        
        this.createAccount(role);
    }
    
//    public String name(){
//        System.out.print("Enter First name: ");
//        String n1 = in.next();
//        System.out.print("Enter Last name: \n");
//        String n2 = in.next();
//        
//        this.name = n1+" "+n2;
//        return this.name;
//    }
//    
//    public String userID() throws IOException{
//        System.out.print("Pick a username: ");
//        String id = in.next();
//        if(a.checkUser(name, role)==true){
//            System.out.println("Username is already taken. Please choose another one.");
//            this.userID();
//        }else{
//            this.userID = id;
//        }  
//        return this.userID;
//    }
//    
//    public String password(){
//        System.out.print("Choose a password. (Must contain 6 characters or more): ");
//        String pw = in.next();
//        if(pw.length()<6){
//            System.out.println("Password is too weak!");
//            this.password();
//        }else{
//            this.password = pw;
//        }
//        return this.password;
//    }
//    
//    public int role() throws IOException{
//        
//        System.out.print("Choose an account: [1]Sellerz [2]Bidderz ");
//        int r = in.nextInt();
//        switch(r){
//            case 1:
//                System.out.println("congratz you will b able 2 sell stuff lol");
//                this.createAccount(r);
//                break;
//            case 2:
//                System.out.println("congratz will b able 2 bid on stuff lol");
//                this.createAccount(r);
//                break;
//            default: 
//                System.out.println("read plz. 1/2 only lol");
//                break;
//        }
//          
//        return this.role;
//    }
    
     public void createAccount(int a) throws IOException{
        boolean duplicate = false;
        if(this.role==1){
            
            FileOutputStream file = new FileOutputStream("user1.txt", true);   
            PrintWriter fw = new PrintWriter(file);
            ArrayList<String[]> array = newArray("user1.txt");
            for(int i = 0; i < array.size(); i++){
                if(this.userID.equalsIgnoreCase(array.get(i)[0]))
                    duplicate = true;
            }
            if(!duplicate){
                ArrayList list = new ArrayList();

                list.add(this.userID);
                list.add(this.name);
                list.add(this.password);

                int sz = list.size();
                for(int i=0; i<sz; i++){
                    if(i != sz - 1)
                        fw.print(list.get(i).toString()+",");
                    else
                        fw.print(list.get(i).toString() + "\n");
                }
                //fw.println();
                fw.close();
            }
            
        }
        else if(this.role==2){
            System.out.println("Bidder Account: ");
             FileOutputStream file = new FileOutputStream("user2.txt", true);   
            PrintWriter fw = new PrintWriter(file);
            ArrayList<String[]> array = newArray("user2.txt");
            for(int i = 0; i < array.size(); i++){
                if(this.userID.equalsIgnoreCase(array.get(i)[0]))
                    duplicate = true;
            }
            if(!duplicate){
                ArrayList list = new ArrayList();

                list.add(this.userID);
                list.add(this.name);
                list.add(this.password);

                int sz = list.size();
                for(int i=0; i<sz; i++){
                    if(i != sz - 1)
                        fw.print(list.get(i).toString()+",");
                    else
                        fw.print(list.get(i).toString() + "\n");
                }
                fw.close();
            }
        }
        else{}
    }
}
