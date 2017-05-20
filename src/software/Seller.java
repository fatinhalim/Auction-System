package software;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author fatinhalim
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Seller {
   protected String sellerID, item, date_time ,deadLine, auction_type;
   static String winner;
   private double price;
    public Seller(String sellerID){
        this.sellerID = sellerID;
    }
    
    public void addItem(String item, String date_time, double price, String auction_type, String deadLine){
        String itemID = "I" + (countItems() + 1);
        try{
            PrintWriter output = new PrintWriter(new FileOutputStream("seller.txt", true));
            if(price > 0)
                output.println(itemID + "," + item + "," + date_time + "," +price + "," + auction_type + "," +deadLine + "," + this.sellerID);
            else
                output.println(itemID + "," + item + "," + date_time + "," +"-" + "," + auction_type + "," +deadLine + "," + this.sellerID);                
            output.close();
        }
        catch(IOException e){
            System.out.println("Problem with output");
        }
    }
    
    public void edit(String itemID, String item, String startDate, double price, String auction_type, String deadLine){
        ArrayList<String[]> array = new ArrayList<>();
        int lineNo = -1;
        for(int i = 0; i < array.size(); i++){
            if(array.get(i)[0].equalsIgnoreCase(itemID))
                lineNo = i;
        }
        if(lineNo < 0)
            System.out.println("Item not found!");
        else{
            array.get(lineNo-1)[1] = item;
            array.get(lineNo-1)[2] = startDate;
            array.get(lineNo-1)[3] = String.valueOf(price) + "0";
            array.get(lineNo-1)[4] = auction_type;
            array.get(lineNo-1)[5] = deadLine;

            try{
                PrintWriter output = new PrintWriter(new FileOutputStream("seller.text"));

                for(int i = 0; i<array.size(); i++){
                    for(int j = 0; j<array.get(i).length; i++){
                        if(j !=1 ){
                            output.print(array.get(i)[j] + ",");
                        }

                        else{
                            output.print(array.get(i)[j]);
                        }
                    }
                }
                output.close();
            }

            catch(FileNotFoundException e){
                System.out.println("file not discovered");
            }

            System.out.println("edit is successful");
        }
    }
    
    public void delete(String itemID, Seller sellerID){
        int lineNo = -1;
        ArrayList<String[]> array = newArray("seller.txt");
        for(int i = 0; i < array.size(); i++){
            if(array.get(i)[0].equalsIgnoreCase(itemID))
                lineNo = i;
        }
        if(lineNo >= 0)
            array.remove(lineNo);
        else
            System.out.println("Item not found!");
        try{
            PrintWriter output = new PrintWriter(new FileOutputStream("seller.txt"));
            
             for(int i = 0; i<array.size(); i++){
                for(int j = 0; j<array.get(i).length; j++){
                    if(j != array.get(i).length - 1)
                        output.print(array.get(i)[j] + ",");
                    else
                        output.print(array.get(i)[j]);
                }
                output.println();
            }
            output.close();
        }
        
        catch(IOException e){
          System.out.println("problem with file");
        }

        System.out.println("deleting is successful");
    }
    
//    public static void chooseAuction(int choice){
//        
//        Scanner input = new Scanner(System.in);
//        
//        switch(choice){
//            case 1:
//                System.out.println("english");
//                //enter method here
//                break;
//            
//            case 2:
//                System.out.println("japanese");
//                //enter method here
//                break;
//            
//            case 3:
//                System.out.println("i forgot");
//                //enter method here
//                break;
//            
//            case 4:
//                System.out.println("vickrey");
//                //enter method here
//                break;
//                
//            case 5:
//                System.out.println("reserve");
//                //enter method here
//                break;
//        }
//    }
  
    
    public static void display(){
        
        ArrayList<String[]> array = newArray("seller.txt");
       
            for(int i = 0; i<array.size(); i++){
                System.out.printf("#%d ", i+1);
                
                    for(int j = 0; j<array.get(i).length; j++){
                    
                    System.out.printf("%-20s",array.get(i)[j]);
                    
                    }
                    
                System.out.println("");
            } 
    }
    
    public void displayBidsfor(String itemID){
        ArrayList<String[]> bids = newArray("bidder.txt");
        ArrayList<String[]> bidsForItem = new ArrayList<>();
        
        for(int i = 0; i < bids.size(); i++){
            if(bids.get(i)[1].equalsIgnoreCase(itemID))
                    bidsForItem.add(bids.get(i));
        }
        
//        System.out.println("You have " + bidsForItem.size() + " items for sale.");
//        int bidCount = 0;
//        for(int j = 0; j < sellerItems.size(); j++){
//            for(int i = 0; i < bids.size(); i++){
//                if(bids.get(i)[1].equalsIgnoreCase(sellerItems.get(j))){
//                    bidCount++;
//                    bidsForItem.add(bids.get(i));
//                }
//            }
//        }
        
        System.out.println(bidsForItem.size() + " bids have been submitted for your items.");
        for(int i = 0; i<bidsForItem.size(); i++){
                System.out.printf("#%d ", i+1);
                    for(int j = 0; j<bidsForItem.get(i).length; j++){
                    System.out.printf("%-20s",bidsForItem.get(i)[j]);
                    }  
                System.out.println("");
            } 
        
    }
    
    public static ArrayList<String[]> newArray(String filename){
        ArrayList<String[]> array = new ArrayList<>();
        
        try{
            Scanner input = new Scanner(new FileInputStream(filename));
            while(input.hasNextLine()){
                String line = input.nextLine();
                array.add(line.split(","));
            }
            input.close();
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        return array;
    }
    
    
    public int countItems(){
        int lineCount = 0;
        try{
            Scanner read = new Scanner(new FileInputStream("seller.txt"));
            while(read.hasNextLine()){
                String line = read.nextLine();
                lineCount++;
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("File not found.");
        }
        return lineCount;    
    }
    
//    public static int countItems(Seller seller){
//        int lineCount = 0;
//        try{
//            Scanner read = new Scanner(new FileInputStream("seller.txt"));
//            while(read.hasNextLine()){
//                String line = read.nextLine();
//                if(line.split(",")[6].equalsIgnoreCase(this.sellerID))
//                    lineCount++;
//            }
//            read.close();
//        }
//        catch(IOException e){
//            System.out.println("File not found.");
//        }
//        return lineCount; 
//    }

    public void myAuctions() {
        ArrayList<String[]> allItems = newArray("seller.txt");
        ArrayList<String[]> sellerItems = new ArrayList<>();
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i)[allItems.get(i).length - 1].equalsIgnoreCase(this.sellerID)){
                    sellerItems.add(allItems.get(i));
                }
        }
        for(int i = 0; i<sellerItems.size(); i++){
                System.out.printf("#%d ", i+1);
                    for(int j = 0; j<sellerItems.get(i).length; j++){
                    System.out.printf("%-20s",sellerItems.get(i)[j]);
                    }
                System.out.println("");
        } 
    }
}
    

 
