package software;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import static software.Bidder.deleteAccount;
import static software.Seller.newArray;
import static software.SellerUI.addOrEditUI;
import static software.SellerUI.changePassword;

/**
 *
 * @author Sina
 */
public class tester {
    
    public static void main(String[] args) throws IOException {
//        Auction sib = new Auction("shampoo");
//        System.out.println(sib.auctionType);
//        Seller sina = new Seller("Sina");
//        System.out.println(sina.sellerID);
//        sina.addItem("football boots", "24-05-2016 3:30", 270, "japanese", "25-05-2016 00:00");
//        sina.display();
//        sina.edit(1, "house", date_time, 0, auction_type, deadLine);
//        Bidder n = new Bidder("Sina");
//        n.enterAuction("I3");
//        n.bid("I3", 1000);
        
        System.out.println("Welcome:");
        Auction auction2 = null;
	Scanner input = new Scanner(System.in);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int role = 0;
        Login user = null;
        int select;
        String username = null, pw, name = null;
        System.out.println("         ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
        System.out.println("         ◕‿◕｡ Welcome to E-Auction ◕‿◕｡ ");
        System.out.println("         ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
        System.out.println("");
        do{
            System.out.println("      ☾ Please log in or sign up to continue ☽");
            System.out.println("☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕");
            System.out.println("             Enter ➊ Sign-up ➋ Login.");
            System.out.println("☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕☕");
            select = input.nextInt();
            boolean pass = false;
            while(!pass){
                if(select == 2){
                    System.out.println("");
                    System.out.println("      ◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈");
                    System.out.println("       Are you ➊ Selling or ➋ Bidding?");
                    System.out.println("      ◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈");
                    role = input.nextInt();
                    System.out.print("                    Username ☞ ");
                    username = input.next();
                    System.out.print("                    Password ☞ ");
                    pw = input.next();


                    pass = true;

                    user = new Login(username, pw, role); //users from seller and bidder class
                }
                else if(select == 1){
                    System.out.println("      ◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈");
                    System.out.println("         Are you ➊ Seller or ➋ Buyer?");
                    System.out.println("      ◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈");
                    role = input.nextInt();
                    input.nextLine();
                    System.out.print("                   Name ☞ ");
                    name = input.nextLine();
                    System.out.print("                   Username ☞ ");
                    username = input.next();
                    System.out.print("                   Password ☞ ");
                    pw = input.next();
                    pass = true;
                    Signup newUser = new Signup(name, username, pw, role);
    //                user = new Login(username, pw, role);
                }
                else{
                    System.out.println("            ╰╯╭╮╰╯ Wrong entry ╰╯╭╮╰╯");
                }
            }
        }while(!Login.correctpw);
        switch (role) {
            case 1:
                Seller newSeller = new Seller(username);    //take from login class
        boolean logout = false;
        Auction auction = null;
        
        do {
            System.out.println("1.New Auction");
            System.out.println("2.Edit");
            System.out.println("3.Delete");
            System.out.println("4.Display Your Auctions");
            System.out.println("5.See All (Public) Auctions");
            System.out.println("6.Account Setting");
            System.out.println("7.Logout");

            int choice = input.nextInt();
            if(choice == 7)
                break;
            else if(choice > 7){
                System.out.println("Invalid entry. Please try agains");
                System.out.println("");
                continue;
            }
            input.nextLine();
//            while(true){
                System.out.println("");
                switch(choice){
                    case 1:
                        addOrEditUI(input, newSeller, null, "add", auction);
                        break;
                    case 2:
                        newSeller.myAuctions();
                        System.out.print("Enter ID of the item you wish to edit: ");
                        String itemID = input.nextLine();
                        addOrEditUI(input, newSeller, itemID, "edit", auction);
                        break;
                    case 3:
                        newSeller.myAuctions();
                        System.out.print("Enter ID of the item you wish to edit: ");
                        itemID = input.nextLine();
                        newSeller.delete(itemID, newSeller);
                        break;
                    case 4:
                        newSeller.myAuctions();
                        System.out.print("Enter item ID to see auction details or enter 0 to go back: ");
                        itemID = input.nextLine();
                        newSeller.displayBidsfor(itemID);
                        if(Auction.hasEnded(itemID)){
                            ArrayList<String[]> array = newArray("bidder.txt");
                            ArrayList<String[]> sellerItems = new ArrayList<>();
                            
                            for(int i = 0; i < array.size(); i++){
                                if(itemID.equalsIgnoreCase(array.get(i)[1]))
                                    sellerItems.add(array.get(i));
                            }
                            String auctionTyppe = sellerItems.get(0)[3];
                            String winner = "";
                            if(!Auction.hasEnded(itemID))
                                System.out.println("The highest bid so far: " + array.get(0)[2] + " by " + array.get(0)[0]);
                            else{
                                switch(auctionTyppe){
                                case "english":
                                    winner = Auction.englishAuction(itemID, array);
                                    break;
                                case "japanese":
                                    winner = Auction.japaneseAuction(itemID, array);
                                    break;
                                case "blind":
                                    winner = Auction.blindAuction(itemID, array);
                                    break;
                                case "vickrey":
                                    winner = Auction.vickreyAuction(itemID, array);
                                    break;
                                case "reserve":
                                    String chance = Auction.reserveAuction(itemID, array);
                                    break;
                                default:
                                    System.out.println("bug");
                                    break;
                            }
                                if(!auctionTyppe.equalsIgnoreCase("reserve"))
                                    System.out.println("The winner of auction is: " + winner);
                                else{
                                    System.out.println("Are you selling to this person? y/n");
                                    String answ = input.nextLine();
                                    if(answ.equalsIgnoreCase("y"))
                                        winner = Auction.reserveAuction(itemID, array);
                                    
                                        
                                }
                        }
                        }
                        
                        break;
                    case 5:
                        Seller.display();
                        break;
                    case 6:
                        System.out.println("1.Change password");
                        System.out.println("2.Delete account");
                       select = input.nextInt();
                       input.nextLine();
                       switch(select){
                           case 1:
                               changePassword(username);
                               break;
                           case 2:
                               deleteAccount(username);
                               break;
                           default:
                               System.out.println("Invalid entry!");
                               break;
                       }
                        break;
                    case 7:
                        logout = true;
                        break;
                    default:

                        break;
                }   
//            }
                System.out.println("");
        } while(!logout);
                break;
            case 2:
                BidderUI bidUI = new BidderUI(username);
                bidUI.viewMainInt(username);  
                break;
            default:
                System.out.println("✿ You need to sign up to gain access the system ✿ ");
                break;
        }    }
}
