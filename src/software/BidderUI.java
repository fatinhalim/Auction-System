/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import static software.Bidder.changePassword;
import static software.Bidder.deleteAccount;

/**
 *
 * @author user
 */
public class BidderUI 
{
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String[]> arr = new ArrayList();
    
    //Private variables below
    private String bidderID;    
    private String bidderName;
    public BidderUI(String username){
        this.bidderID = username;
    }
    //--------------------------------------------------------------------------
    //--------------------------Main methods below------------------------------
    //--------------------------------------------------------------------------
    
    /**
     * Returns the given bidder's ID.
     * @return 
     */
    public String getBidderID()
        {   return this.bidderID;   }
    
    /**
     * Returns the given bidder's name.
     * @return 
     */
    public String getBidderName()
        {   return this.bidderName;   }
    
    /**
     * Views the main interface for the BidderUI class.
     */
    public void viewMainInt(String username) throws IOException
    {
        System.out.println("Please choose an action: ");
        System.out.println("① View available auctions \n② My bids \n③ Account Settings \n④ Log Out");
        System.out.println("**************************************************************");
        
        int inpNextAction = 0;
        boolean keyInShownNum = false;

        while(keyInShownNum == false)
        {
        //<editor-fold defaultstate="collapsed" desc="Checks whether inpNextAction is in between 1 - 4.">    
            inpNextAction = sc.nextInt();
            
            switch(inpNextAction)
            {
                case 1:
                    this.bidderID = username;    
                    Bidder bidder = new Bidder(getBidderID());       
                    forClarity(); forClarity();
                    bidder.display();
                    bidder.actionTakenBidder();
                    keyInShownNum = true;
                    break;
                case 2:
                    this.bidderID = username;    
                    myBids myBids = new myBids(this.bidderID);
                    myBids.display(getBidderID());
                    myBids.actionTakenBidder();
                    keyInShownNum = true;
                    break;
                case 3:
                    viewBidderProfile();
                    forClarity();
                    keyInShownNum = true;
                    break;
                case 4:
                    //Log out from the system, displays the login interface.
                    keyInShownNum = true;
                    break;
                default:
                    System.out.println("\n☹☹☹ Sorry, you've entered an invalid key. ☹☹☹");
                    System.out.println("Please select a number between 1 - 4.");
                    System.out.println("① View available auctions \n② My bids \n③ View profile \n④ Log Out");
            }
        //</editor-fold>
        }  
    }
    
    /**
     * Prints out the name and the ID of the bidder.
     */
    public void viewBidderProfile() throws IOException
    {
        System.out.println("\n∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ");
        System.out.println("Bidder ID: " + getBidderID());
        System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
        System.out.println("Please choose an action: ");
        System.out.println("① Change Password \n② Delete Account \n③ Back");
        System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
        System.out.println("∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ∷ ");
        
        int inpNextAction = 0;
        boolean keyInShownNum = false;

        while(keyInShownNum == false)
        {
        //<editor-fold defaultstate="collapsed" desc="Checks whether inpNextAction is in between 1 - 3.">    
            inpNextAction = sc.nextInt();
            
            switch(inpNextAction)
            {
                case 1:
                    changePassword(this.bidderID);
                    viewMainInt(this.bidderID);
                    keyInShownNum = true;
                    break;
                case 2:
                    deleteAccount(this.bidderID);
                    viewMainInt(this.bidderID);
                    keyInShownNum = true;
                    break;
                case 3:
                    forClarity();
                    viewMainInt(this.bidderID);
                    keyInShownNum = true;
                    break;
                default:
                    System.out.println("\n☹☹☹ Sorry, you've entered an invalid key. ☹☹☹");
                    System.out.println("Please select a number between 1 - 3.");
                    System.out.println("① Change Password \n② Delete Account \n③ Back");
            }
        //</editor-fold>
        }  
    }
    
    /**
     * Reads bidder.txt and extracts all information e.g. name, ID, 
     * bidding price of a given item, its respective ID and the bidder's status.
     * 
     * <p>
     * The method populates data into the ArrayList in the following order:
     * </p><pre>
     * --> Bidder name, Item ID, Bidding price, Auction type
     * </pre>
     */
    public void readAllBiddersFile()
    {
        try
        {
            BufferedReader pw = new BufferedReader(new FileReader("bidder.txt"));
            String readLine;
            String[] splitLine;
            int lineCount = 0;
            
            while( (readLine = pw.readLine()) != null)
            //<editor-fold defaultstate="collapsed" desc="Reading bidder.txt to calculate the number of lines.">   
            {   
                if(readLine == null)  
                    {   throw new NullPointerException("Nothing is being read into...");   }
                
                lineCount++;
            }
            System.out.println(lineCount);
            //</editor-fold> 
            pw.close();
            
            pw = new BufferedReader(new FileReader("bidder.txt"));
            while( (readLine = pw.readLine()) != null)
            //<editor-fold defaultstate="collapsed" desc="Reading bidder.txt to populate data into ArrayList.">   
            {   
                if(readLine == null)  
                    {   throw new NullPointerException("Nothing is being read into...");   }
                   
                splitLine = readLine.split(",");
                arr.add(splitLine);
            }
            //</editor-fold>
            pw.close();
        }
          catch(IOException e)
            {   System.out.println("An error occured while reading the file.");}
    }
    
    /**
     * Adds a new line after this method is invoked.
     * Merely a method to ensure a neater coding.
     */
    private void forClarity()
        {   System.out.println("");     }
    
    /**
     * The main method which calls the BidderUI interface.
     * @param args the command line arguments
     */
    
}

//<editor-fold defaultstate="collapsed" desc="A convenient comment section used to hide codes.">
//</editor-fold>