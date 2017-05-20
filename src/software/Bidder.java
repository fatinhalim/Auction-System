/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;
import static software.Seller.newArray;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MAC
 */
public class Bidder 
{
    private Scanner sc = new Scanner(System.in);
    
    //Variable declaration
    protected String bidderID;
    private boolean canEnterAuction;
    private String auctionType = "";
    
    public Bidder()
        {}
    
    public Bidder(String bidderID)
        {   this.bidderID = bidderID;   }
    
    //--------------------------------------------------------------------------
    //--------------------------Main methods below------------------------------
    //--------------------------------------------------------------------------
    
    /**
     * This method will verify whether the user is eligible to enter
     * any given auction at a given time/based on auction constraints.
     * @param itemID 
     */
    public void enterAuction(String itemID)
    { 
        String auctionType = "";
        String auctionStartDate = null;
        String deadline = null;
        
        //<editor-fold defaultstate="collapsed" desc="try-catch block">
        try
        {
            Scanner read = new Scanner(new FileInputStream("seller.txt"));
            while(read.hasNextLine())
            //<editor-fold defaultstate="collapsed" desc="If ItemID is found, the respective auction type and deadline will be assigned to its respective item.">    
            {
                String line = read.nextLine();
                
                if(line.split(",")[0].equalsIgnoreCase(itemID))
                {
                    auctionType = line.split(",")[4];
                    this.auctionType = auctionType;
                    auctionStartDate = line.split(",")[2];
                    deadline = line.split(",")[5];
                }
            }
            //</editor-fold>
            read.close();
        }
          catch(IOException e)
            {   System.out.println("File not found.");  }
        //</editor-fold>
        
        //Date Format
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date now = new Date();
        String dateNow = df.format(now);
        
        if(dateNow.compareTo(auctionStartDate) <= 0)
        {
            System.out.println("You have successfully entered this auction. \nAuction beginning time: " + auctionStartDate);
            this.canEnterAuction = true;
        }
        else
        {
            if(auctionType.equalsIgnoreCase("japanese"))
            {   System.out.println("Sorry! You should be more punctual to enter a Japanese auction :) ");
                canEnterAuction = false;                    }
            else if(dateNow.compareTo(deadline) < 0 && !auctionType.equalsIgnoreCase("japanese"))
            {   System.out.println("You have entered this auction. \nThe auction has already begun, but you can still set a bid now.");
                canEnterAuction = true;                     }
            else
            {   System.out.println("You were late! Auction is over. ⊙﹏⊙");
                canEnterAuction = false;                    }
        }
    }   
    
    /**
     * If the bidder is able to bid, this method is invoked to write 
     * the most recently added item information into bidder.txt.
     * @param itemID
     * @param bidAmount 
     */
    public void bid(String itemID, double bidAmount)
    {   
        try{
            PrintWriter write = new PrintWriter(new FileOutputStream("bidder.txt", true));
            if(this.canEnterAuction)
            {
                    write.println(this.bidderID + "," + itemID.toUpperCase() + "," + bidAmount + "," + this.auctionType);
                    write.close();
            }
        }
        
        catch(IOException e){
            System.out.println("file not found");
        }
    }
    
    /**
     * Displays all available bidding items.
     */
    public void display()
    {
        ArrayList<String[]> array = newArray("seller.txt");
        
        System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
        System.out.printf("No. Item ID \t\tItem Name           Start Date/Time     Seller's Price \t    Auction type \tEnd Date/Time \t    Seller's Name\n");
        
        for(int i = 0; i<array.size(); i++)
        {
            System.out.printf("#%d  ", i+1);
            
            for(int j = 0; j<array.get(i).length; j++)
                {   System.out.printf("%-20s",array.get(i)[j]); }
            
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
        System.out.println("① Choose auction \n② Back (Get outta here! xD)");
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
                    chooseAuction();
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
                    System.out.println("Please select a number between 1 - 3.");
                    System.out.println("① Choose auction \n② Back (Get outta here! xD)");
            }
        //</editor-fold>
        }  
    }

    /**
     * Verifies the action taken by the bidder when he/she wants to view all items available
     * for sale.
     */
    public void chooseAuction() throws IOException
    {
        System.out.println("Please choose an action: ");
        System.out.println("① Enter auction \n② See others' bids \n③ Back");
        System.out.println("**************************************************************");
        
        int inpNextAction = 0;
        boolean keyInShownNum = false;

        while(keyInShownNum == false)
        {
        //<editor-fold defaultstate="collapsed" desc="Checks whether inpNextAction is in between 1 - 3.">    
            inpNextAction = sc.nextInt();
            forClarity();
            
            switch(inpNextAction)
            {
                case 1:
                    display();
                    checkEligibility();
                    keyInShownNum = true;
                    break;
                case 2:    
                    seeOthersBids();
                    keyInShownNum = true;
                    break;
                case 3:
                    System.out.println("\nLoading.......\n..............\n..............\n");
                    Bidder bidder = new Bidder();
                    bidder.actionTakenBidder();
                    keyInShownNum = true;
                    break;
                default:
                    System.out.println("\n☹☹☹ Sorry, you've entered an invalid key. ☹☹☹");
                    System.out.println("Please select a number between 1 - 3.");
                    System.out.println("① Enter auction \n②See others' bids \n③ Back");
            }
        //</editor-fold>
        }     
    }
    
    /**
     * The bidder is able to see other bidder's bids without altering
     */
    private void seeOthersBids() throws IOException 
    {   ArrayList<String[]> auctions = newArray("seller.txt");
        ArrayList<String[]> newArray = newArray("bidder.txt");
        ArrayList<String[]> genArr = new ArrayList<>();
        System.out.print("◆ Please input the ID of the item: ");
        String itemID = sc.next();
        boolean canSee = true;
        String winner = "";
        
        for(int i = 0; i < auctions.size(); i++){
            if(auctions.get(i)[1].equalsIgnoreCase(itemID)){
                if(auctions.get(i)[3].equalsIgnoreCase("blind") || auctions.get(i)[3].equalsIgnoreCase("vickrey"))
                    canSee = false;
            }
        }
        if(!canSee)
            System.out.println("You can't see other's bids in Blind and Vickrey auctions!");
        //<editor-fold defaultstate="collapsed" desc="Displays all auctions selling the item according to given itemID.">
        //Filters the list based on given bidderID
        if(canSee){
            for(int h=0; h<newArray.size(); h++)
            {
                if(itemID.equalsIgnoreCase( newArray.get(h)[1] ))
                {   String[] newStr = newArray.get(h);
                    genArr.add(newStr);       }
            }
            
            String auctionTypee = genArr.get(0)[3];

            forClarity();
            System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
            System.out.printf("No. Bidder ID \t\tItem ID      \t    Bidding Price       Auction type\n");

            for(int i = 0; i<genArr.size(); i++)
            {
                System.out.printf("#%d  ", i+1);

                for(int j = 0; j<genArr.get(i).length; j++)
                    {   System.out.printf("%-20s",genArr.get(i)[j]); }

                System.out.println("");
            }

            for(int pass = 1; pass < genArr.size(); pass++){
            for(int i = 0; i < genArr.size() - 1; i++){
                if(Double.parseDouble(genArr.get(i)[2]) < Double.parseDouble(genArr.get(i+1)[2])){
                    String[] hold = genArr.get(i);
                    genArr.set(i, genArr.get(i+1));
                    genArr.set((i+1), genArr.get(i));
                }
            }
        }
            System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
            //</editor-fold>
            if(!Auction.hasEnded(itemID))
                System.out.println("The highest bid so far: " + genArr.get(0)[2] + " by " + genArr.get(0)[0]);
            else{
                switch(auctionTypee){
                    case "english":
                        winner = Auction.englishAuction(itemID, genArr);
                        break;
                    case "japanese":
                        winner = Auction.japaneseAuction(itemID, genArr);
                        break;
                    case "blind":
                        winner = Auction.blindAuction(itemID, genArr);
                        break;
                    case "vickrey":
                        winner = Auction.vickreyAuction(itemID, genArr);
                        break;
                    case "reserve":
                        System.out.println("Wait for it..");
                        break;
                    default:
                        System.out.println("bug");
                        break;
                }
                System.out.println("The winner of auction is: " + winner);
            }
        }
        chooseAuction();
    }
    
    /**
     * Checks whether the bidder is eligible to participate in any of the available auctions,
     * else this method will print out the error message.
     */
    private void checkEligibility() throws IOException
    {
        Bidder bidder = new Bidder(bidderID);
        String itemID;
        
        System.out.println("◆ Please input the ID of the item: ");
        itemID = sc.next();   
        bidder.enterAuction(itemID);
        
        if(bidder.canEnterAuction)
        {
            System.out.println("◆ Please enter bidding price: ");
            double bidAmount = sc.nextDouble();
            bidder.bid(itemID, bidAmount);//bidder class
            System.out.println("Bid submitted.\n");
            //Where's this code???
            //auction2 = new Auction(itemID);
        }

        try {   Thread.sleep(2000); } 
        catch (InterruptedException ex) {   Logger.getLogger(Bidder.class.getName()).log(Level.SEVERE, null, ex);   }
        
        display();
        actionTakenBidder();
    }
    
    /**
     * Adds a new line after this method is invoked.
     * Merely a method to ensure a neater coding.
     */
    private void forClarity()
        {   System.out.println("");     }
    
    public static void deleteAccount(String bidderID) throws IOException{
        Login a = new Login();
        System.out.println("You are about to permanently delete your account.");
        System.out.println("You won't be able to recover your account.");
        System.out.println("");
        System.out.print("Enter password to confirm:");
        Scanner input = new Scanner(System.in);
        String pw = input.nextLine();
        a.deleteAcc(bidderID, pw, 2);
    }
    
    public static void changePassword(String bidderID) throws IOException{
        Login a = new Login();
        Scanner input = new Scanner(System.in);
       
        System.out.print("Enter your current password: ");
        String oldPass = input.nextLine();
        System.out.print("Enter new password: ");
        String temp = input.nextLine();
        System.out.print("Repeat new password: ");
        String newPass = input.nextLine();
        if(temp.equals(newPass))
            a.changePassword(bidderID, oldPass, newPass, 2);
        else
            System.out.println("Passwords don't match. Try again");
        
        
    }
    
    
}
