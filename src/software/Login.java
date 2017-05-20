/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

/**
 *
 * @author Daniela
 */
import java.util.*;
import java.io.*;
import static software.Seller.newArray;

public class Login {
    protected String userId;
    private String password;
    static boolean correctpw;
    protected int role; //1-seller   2-bidder
    
     public Login(){}
    
    //main constructor
     public Login(String username,String password,int role) throws IOException{
        int chance = 1,a=0;
        this.correctpw = false;
        this.userId = username;
        this.password = password;
        this.role = role;
        
        this.checkUser(username, role);
            
        if(this.checkUser(this.userId, this.role) == true){//IF USER EXISTS
            
            this.checkPassword(password, role);
            if(this.checkPassword(password, role)==true){
               a=1; 
            }else{
                a=2;
            }
            switch(a){
                case 1:
                    System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
                    System.out.println("welcome back, "+this.userId+" we KINDA miss u brosis");
                    System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
                    correctpw = true;
                    break;
                case 2:
                    do{
                    System.out.println("bro u sure u "+this.userId+" ke. don hack la bro");
                    System.out.println("i give 1 more chance ah bro");
                    String pw = in.next();
                    this.password = pw;
                    this.checkPassword(this.password, role);
                    }while(this.checkPassword(this.password, role)==false);
                    System.out.println("welcome back, "+this.userId+" next time memorise ur pw plz");
                    correctpw = true;
                    break;
            }
                    
        }else{//IF USER DOES NOT EXIST
            System.out.println("you're not a member lol");
            //variable exists stays false, will not call main menu or anything.
          }
     }//main constructor
    
    Scanner in = new Scanner(System.in);
    
    
    public String Password(){
        System.out.print("Enter password: ");
        this.password = in.next();
        return this.password;
        
        
    }

    
    public  boolean checkUser(String name,int role) throws FileNotFoundException, IOException{
        boolean result = false;
        if(role==1){

            Scanner read = new Scanner(new FileInputStream("user1.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString); 
            }
            read.close();
             for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(name.equalsIgnoreCase(array.get(i)[0])){
                        result = true;
                    }
                }
            } 
        }
        else{
            Scanner read = new Scanner(new FileInputStream("user2.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString);
            }
            read.close();
            for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(name.equalsIgnoreCase(array.get(i)[0])){
                        result = true;
                    }
                }
            }
        } 
        return result;
        
    }
    
    public boolean checkPassword(String password,int role) throws FileNotFoundException, IOException{
    boolean result = false;
    if(role==1){
            Scanner read = new Scanner(new FileInputStream("user1.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString);
            }
            read.close();
            for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(password.equals(array.get(i)[2])){
                        result = true;
                    }
                }
            }
    }
        else{
            Scanner read = new Scanner(new FileInputStream("user2.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString);
            }read.close();
            for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(password.equalsIgnoreCase(array.get(i)[2])){
                        result = true;
                    }
                }
            }
        } 
    return result;
    }
   
//    public boolean findAcc(String name, String password, int role) throws IOException{
//    boolean result = false;
//    this.checkUser(name, role);
//    if(this.checkUser(name, role)==true){
//        this.checkPassword(password, role);
//        if(this.checkPassword(password, role)==true){
//            result = true;
//            //call class
//        }
//        else{
//            int i = 1;
//            while(i!=5){
//            do{
//            System.out.println("Incorrect Password. Try again.");
//            this.Password();
//            this.checkPassword(password, role);
//            i++;
//            }
//            while(this.checkPassword(password, role)!=true);
//            }
//        }
//    }
//    else{
//        System.out.println("No account matches the username.");
//        //call sign up prompt.
//    }
//    return result;
//    }
//    
public void deleteAcc(String username, String password, int role) throws IOException{
        //ArrayList<String[]> newarray = newArray("seller.txt");
        
        this.userId = username;
        this.password = password;
        this.role = role;
        
        //login first 
        if(checkUser(username, role) && checkPassword(password,role)){

            ArrayList<String[]> newarray = newArray("user" + this.role + ".txt");
            for(int i = 0; i < newarray.size(); i++){
                if(newarray.get(i)[0].equalsIgnoreCase(username))
                    newarray.remove(i);
            }
         //write to the text file
             PrintWriter output = new PrintWriter(new FileOutputStream("user" + this.role + ".txt"));
             for(int i = 0; i < newarray.size(); i++){
                 for(int j = 0; j < newarray.get(i).length; j++){
                     if(j != newarray.get(i).length - 1)
                        output.print(newarray.get(i)[j] + ",");
                     else
                         output.print(newarray.get(i)[j]);
                 }
                 output.println();
             }
             output.close();    
        }
        else{
            System.out.println("khh");
        }
    }
    public void changePassword(String bidderID, String oldPass, String newPass, int role) throws IOException{
        if(checkUser(bidderID, role) && checkPassword(oldPass,role)){
            ArrayList<String[]> newarray = newArray("user" + role + ".txt");
            for(int i = 0; i < newarray.size(); i++){
                if(newarray.get(i)[0].equalsIgnoreCase(bidderID))
                    newarray.get(i)[2] = newPass;
            }
            PrintWriter write = new PrintWriter(new FileOutputStream("user" + role + ".txt"));
            for(int i = 0; i < newarray.size(); i++){
                for(int j = 0; j < newarray.get(i).length; j++){
                    if(j == newarray.get(i).length - 1)
                        write.print(newarray.get(i)[j]);
                    else
                        write.print(newarray.get(i)[j] + ",");
                }
                write.println();
            }
            write.close();
        }
    }

    
}
