/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

import static software.Seller.newArray;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 * @param <T>
 */
public class myBids<T extends Comparable<? super T>>
{
    private Scanner sc = new Scanner(System.in);
    
    //Private variables below
    private ArrayList<String[]> genArr = new ArrayList<>();
    private ArrayList<String[]> copyNewArray = new ArrayList<>();
    private String bidderID; 
    private String auctionType = "";
    
    /**
     * The default constructor.
     * @param bidderID 
     */
    public myBids(String bidderID) 
        {   this.bidderID = bidderID;   }    //Passed from BidderUI interface
    
    //--------------------------------------------------------------------------
    //--------------------------Main methods below------------------------------
    //--------------------------------------------------------------------------
   
    private void callOutBid() throws IOException 
    {
        System.out.println("◆ Please input the ID of the item: ");
        String itemID = sc.next();
        boolean check = false;
        
        while(check == false)
        {
            int count = 0;
            
            for(int i = 0; i<copyNewArray.size(); i++)
            {
                if(!itemID.equalsIgnoreCase(copyNewArray.get(i)[1]))
                    {   count++;    }
                else if(itemID.equalsIgnoreCase(copyNewArray.get(i)[1]))
                    {   check = true;
                        break;  }    
            }
              if(count == copyNewArray.size())
                {   System.out.println("Invalid itemID entered. Try again.");
                    System.out.println("◆ Please input the ID of the item: ");     
                    itemID = sc.next();      }
        }
        
        for(int i = 0; i<genArr.size(); i++)
        {
            if(itemID.equalsIgnoreCase(genArr.get(i)[1]) && this.bidderID.equalsIgnoreCase(genArr.get(i)[0]))
                {   genArr.remove(i);   }
        }
        
        writeIntoFile();
        System.out.println("Success!!!\n");
        
        try     {   Thread.sleep(2000);     } 
        catch (InterruptedException ex)     {   Logger.getLogger(myBids.class.getName()).log(Level.SEVERE, null, ex);   }
        
        actionTakenBidder();
    }
    
    /**
     * Writes data into the bidder.txt file. This method will always overwrite the file's
     * contents.
     */
    private void writeIntoFile()
    {
        try
            {
                PrintWriter write = new PrintWriter(new FileOutputStream("bidder.txt", false));
                String readStr = "";
                
                for(int i=0; i<genArr.size(); i++)
                {   
                    for(int j=0; j<genArr.get(i).length; j++)
                    {   
                        readStr = genArr.get(i)[j];     
                        if(j == 3) //Last index is referring to the auction type
                            {   write.println(readStr);     }
                        else
                            {   write.print(readStr + ",");     }
                    }
                }
                write.close();
            }
              catch(IOException e)
                {   System.out.println("Problem with output.");     }
    }
    
    /**
     * Displays all available bidding items based on the bidderID.
     * @param bidderID
     */
    public void display(String bidderID)
    {
        ArrayList<String[]> array = newArray("bidder.txt");
        ArrayList<String[]> newArray = new ArrayList<>();
        genArr = array;
        copyNewArray = newArray;
        
        //Filters the list based on given bidderID
        for(int i = 0; i<array.size(); i++)
        {
            if(bidderID.equalsIgnoreCase( array.get(i)[0] ))
            {   String[] newStr = array.get(i);
                newArray.add(newStr);       }
        }
        
        forClarity();
        System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
        System.out.printf("No. Bidder ID \t\tItem ID      \t    Bidding Price       Auction type\n");
        
        for(int i = 0; i<newArray.size(); i++)
        {
            System.out.printf("#%d  ", i+1);
            
            for(int j = 0; j<newArray.get(i).length; j++)
                {   System.out.printf("%-20s",newArray.get(i)[j]); }
            
            System.out.println("");
        }
        
        System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
    }
    
    /**
     * Verifies the action taken by the bidder when he/she wants to view all items available
     * for sale.
     */
    public void actionTakenBidder() throws IOException
    {
        System.out.println("Please choose an action: ");
        System.out.println("① Call out \n② Back (Let's get back to some serious business! c: )");
        System.out.println("**************************************************************");
        
        int inpNextAction = 0;
        boolean keyInShownNum = false;

        while(keyInShownNum == false)
        {
        //<editor-fold defaultstate="collapsed" desc="Checks whether inpNextAction is 1 / 2.">    
            inpNextAction = sc.nextInt();
            forClarity();
            
            switch(inpNextAction)
            {
                case 1:
                    callOutBid();
                    keyInShownNum = true;
                    break;
                case 2:
                    System.out.println("\nLoading.......\n..............\n..............\n");
                    BidderUI bidderUI = new BidderUI(this.bidderID);
                    bidderUI.viewMainInt(this.bidderID);
                    keyInShownNum = true;
                    break;
                default:
                    System.out.println("\n☹☹☹ Sorry, you've entered an invalid key. ☹☹☹");
                    System.out.println("Please select 1 or 2.");
                    System.out.println("① Choose an item \n② Exit (Get outta here! xD)");
            }
        //</editor-fold>
        }  
    }
    
    /**
     * Adds a new line after this method is invoked.
     * Merely a method to ensure a neater coding.
     */
    private void forClarity()
        {   System.out.println("");     }
}
