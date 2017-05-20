package software;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Auction {
    protected String userID, winner, itemName, itemID;
    protected static String startDate = "", deadline = "";
    String auctionType = "";
    double minBidAmount;
    boolean auctionEnded;
    
    public Auction(String itemID, String item, String startDate, double minBidAmount, String auction_type, String deadLine, Seller user){
        this.userID = user.sellerID;
        this.itemID = itemID;
        this.itemName = item;
        this.startDate = startDate;
        this.minBidAmount = minBidAmount;
        this.auctionType = auctionType;
        this.deadline = deadline;
    }
    
//    public Auction(String itemName){
//        ArrayList<String[]> bidsForItem = new ArrayList<>();
//        try{
//            Scanner read = new Scanner(new FileInputStream("bidder.txt"));
//            String line;
//            while(read.hasNextLine()){
//                line = read.nextLine();
//                if(line.split(",")[1].equalsIgnoreCase(itemName))
//                    bidsForItem.add(line.split(","));   //in bidder.txt, all bids for itemName will be added to array
//            }
//            read = new Scanner(new FileInputStream("seller.txt"));
//            while(read.hasNextLine()){
//                line = read.nextLine();
//                if(line.split(",")[0].equalsIgnoreCase(itemName))   //reads seller.txt to find out auctionType for itemName
//                    this.auctionType = line.split(",")[4];
//            }
//            read.close();
//        }
//        catch(IOException e){
//            System.out.println("File not found.");
//        }
////        System.out.println(this.auctionType);
////        System.out.println(bidsForItem.get(0)[2]);        
////        for(int i = 0; i < bidsForItem.size(); i++){
////            for(int j = 0; j < bidsForItem.get(i).length; j++){
////                System.out.print(bidsForItem.get(i)[j] + "\t");
////            }
////            System.out.println();
////        }
//        switch(auctionType){
//            case "english":
//                this.winner = englishAuction(itemName, bidsForItem);
//                writeWinner(this.winner, itemName,auctionType);
//                
//                break;
//            case "japanese":
//                this.winner = japaneseAuction(itemName, bidsForItem);
//                writeWinner(this.winner, itemName,auctionType);
//                break;
//            case "vickrey":
////                for(int i = 0; i < bidsForItem.size(); i++){
////                    for(int j = 0; j < bidsForItem.get(i).length; j++){
////                        System.out.print(bidsForItem.get(i)[j]);
////                    }
////                    System.out.println();
////                }
//                this.winner =vickreyAuction(itemName, bidsForItem);
//                writeWinner(this.winner, itemName,auctionType);
//                break;
//            case "blind":
//                this.winner = blindAuction(itemName, bidsForItem);
//                writeWinner(this.winner, itemName,auctionType);
//                break;
//            case "reserve":
//                this.winner = reserveAuction(itemName, bidsForItem);
//                writeWinner(this.winner, itemName,auctionType);
//                break;
//            default:
//                System.out.println("Congrats! You're seeing a bug we couldn't find!!");
//                break;
//        }
//    }
    
    
    
    public static String englishAuction(String itemName, ArrayList<String[]> bids){
        sortArrayByBidAmount(bids);
//        return toString(bids.get(0));  
        String line = "";
        for(int i = 0; i < bids.get(0).length; i++){
            if(i != bids.get(0).length - 1)
                line += bids.get(0)[i] + ",";
            else
                line += bids.get(0)[i];
        }
        return line;
    }
    public static String japaneseAuction(String itemName, ArrayList<String[]> bids){
        sortArrayByBidAmount(bids);
//        return toString(bids.get(0));  
        String line = "";
        for(int i = 0; i < bids.get(0).length; i++){
            if(i != bids.get(0).length - 1)
                line += bids.get(0)[i] + ",";
            else
                line += bids.get(0)[i];
        }
        return line;
    }
    public static String vickreyAuction(String itemName, ArrayList<String[]> bids){   //same as blind. with one difference
        sortArrayByBidAmount(bids);
//        String winner = bids.get(0)[2];
//        String winnerLine = "";
//        String winnerLineNew = "";
//        for(int i = 0; i < bids.get(0).length; i++){
//            winnerLine += bids.get(0)[i];
//            if(i != bids.get(0).length - 1)
//                winnerLine += ",";
//            if(i != 1){
//                winnerLineNew += bids.get(0)[i];
//                if(i != bids.get(0).length - 1)
//                    winnerLineNew += ",";
//            }
//            else{
//                winnerLineNew += bids.get(1)[i];
//                if(i != bids.get(0).length - 1)
//                    winnerLineNew += ",";
//            }       
//        }
//        try{
//            BufferedReader read = new BufferedReader(new FileReader("bidder.txt"));
//            String oldtext = "", line = "";            
//            while((line = read.readLine()) != null){
//                oldtext += line + System.lineSeparator(); 
//            }
//            read.close();
//            String newtext = oldtext.replaceAll(winnerLine, winnerLineNew);
//            PrintWriter write = new PrintWriter(new FileOutputStream("bidder_test.txt"));
//            write.print(newtext);
//            write.close();
//        }
//        catch(IOException e){
//            System.out.println("Problem with output.");
//        }
//        System.out.println(winnerLineNew + "\n" + winnerLine);
//        ArrayList<String> array = fileToArray("bidder.txt");
//        System.out.println(bids.size());
        if(bids.size() > 1)
            bids.get(0)[1] = bids.get(1)[1];
        
        return toString(bids.get(0));
    }
    public static String blindAuction(String itemName, ArrayList<String[]> bids){
        sortArrayByBidAmount(bids);
//        String winner = bids.get(0)[2];
//        System.out.println(winner);
        String line = "";
        for(int i = 0; i < bids.get(0).length; i++){
            if(i != bids.get(0).length - 1)
                line += bids.get(0)[i] + ",";
            else
                line += bids.get(0)[i];
        }
        return line;
    }
    public static String reserveAuction(String itemName, ArrayList<String[]> bids){
        sortArrayByBidAmount(bids);
        String line = "";
        for(int i = 0; i < bids.get(0).length; i++){
            if(i != bids.get(0).length - 1)
                line += bids.get(0)[i] + ",";
            else
                line += bids.get(0)[i];
        }
        return line;
    }
    
    
    private ArrayList<String> fileToArray(String fileName){
        ArrayList<String> array = null;
        try{
            Scanner read = new Scanner(new FileInputStream(fileName));
            String line;
            array = new ArrayList<>();
            while(read.hasNextLine()){
                line = read.nextLine();
                array.add(line);
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("File not found.");
        }
        return array;
    }
    
    private static void sortArrayByBidAmount(ArrayList<String[]> bids){
        String[] temp;
        for(int k = 1; k < bids.size(); k++){
            for(int i = 0; i < bids.size() - k; i++){
//                System.out.println(bids.get(i)[1] + "\t" + bids.get(i + 1)[1]);
                if(Double.parseDouble(bids.get(i)[2]) < Double.parseDouble(bids.get(i + 1)[2])){
                    temp = bids.get(i);
                    bids.set(i, bids.get(i+1));
                    bids.set(i+1, temp);
                }
            }
        }
    }
    
    
    private static String toString(String[] array){
        String line = "";
        for(int i = 0; i < array.length; i++){
            if(i != array.length - 1)
                line += array[i] + ",";
            else
                line += array[i];
        }
        return line;
    }
    
    private void writeWinner(String winner, String itemID, String auctionType){
        try{
            PrintWriter write = new PrintWriter(new FileOutputStream("winner.txt", true));
            write.println(itemID + "," + winner + "," + this.auctionType);
            
            write.close();
        }
        catch(IOException e){
            
        }
    }
    
    public void winnerOfAuctionFor(String itemID){
        ArrayList<String[]> sellerItems = SellerItems(this.userID);
        if (!sellerItems.isEmpty()){
            System.out.println("You have " + sellerItems.size() + " auctions:");
            for(int i = 0; i < sellerItems.size(); i++){
                for(int j = 0; j < sellerItems.get(i).length; j++){
                    System.out.println(sellerItems.get(i)[j] + "\t");
                }
            }
        }
        else
            System.out.println("");
    }
    
    public ArrayList<String[]> SellerItems(String sellerID){
        ArrayList<String> allItems = fileToArray("seller.txt");
        ArrayList<String[]> sellerItems = new ArrayList<>();
        
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i).split(",")[allItems.size() - 1].equalsIgnoreCase(sellerID)){
                sellerItems.add(allItems.get(i).split(","));
            }
        }
        return sellerItems;
        
    }
    
    public static boolean hasEnded(String itemID){
        ArrayList<String[]> array = Seller.newArray("seller.txt");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date date = new Date();
        String deadline = "";
        String now = df.format(date);
        
        for(int i = 0; i < array.size(); i++){
            if(array.get(i)[0].equalsIgnoreCase(itemID))
                deadline = array.get(i)[5];
        }
        
        return now.compareToIgnoreCase(deadline) > 0;
    }
}
