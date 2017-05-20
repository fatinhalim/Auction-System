package software;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author Sina
 */
public class SellerUI {
    public static void main(String[] args){
        Seller newSeller = new Seller("sina");    //take from login class
        boolean pass = false, logout = false;
        Auction auction = null;
        
        do {
            System.out.println("1.New Auction");
            System.out.println("2.Edit");
            System.out.println("3.Delete");
            System.out.println("4.Display Your Auctions");
            System.out.println("5.See All (Public) Auctions");
            System.out.println("6.Account Setting");
            System.out.println("7.Logout");

            Scanner input = new Scanner(System.in);
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
                            System.out.print("Auction has ended. The winner is: " + auction.winner);
                        }
                        
                        break;
                    case 5:
                        Seller.display();
                        break;
                    case 6:
                        System.out.println("1.Change password");
                        System.out.println("Delete account");
                       int select = input.nextInt();
                       input.nextLine();
                       switch(select){
                           case 1:
                               //       CALL CHANGE PASSWORD METHOD
                               break;
                           case 2:
                               //       CALL DELETE PASSWORD METHOD
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
    }
    
    public static boolean isValidDate(String date_time) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            myDateFormat.parse(date_time);
            return true;
        } catch (ParseException ex) {
            // You may want to print the exception here, or do something else with it
            return false;
        }
    }
    
    public static String validDateUI(String date, Scanner input){
        String startDate = "";
        while(true){
            System.out.print("Enter " + date + " (DD-MM-YYYY hh:mm) : ");
            startDate = input.nextLine();
            if(!isValidDate(startDate))
                System.out.println("Please enter date and time in the right format (DD-MM-YYYY hh:mm)");
            else
                break;
        }
        return startDate;
    }
   
    public static void addOrEditUI(Scanner input, Seller sellerID, String itemID, String addOrEdit, Auction auction){
        String auctionType = "", deadline = "", startDate = "";
        double minBidAmount = -1;
        
        System.out.print("Enter item name: ");
        String itemName = input.nextLine();

        startDate = validDateUI("auction start date and time", input);

        System.out.print("Would you like to enter minimum bid amount? (optional) y/n ");
        String yn = input.nextLine();
        if(yn.equalsIgnoreCase("y") || yn.equalsIgnoreCase("n")){
            if(yn.equalsIgnoreCase("y")){
                System.out.print("Enter minimum bid amount:");
                minBidAmount = input.nextDouble();
            }
        }

        System.out.println("Select auction type:");
        System.out.println("---------------------");
        System.out.println("\t1.English");
        System.out.println("\t2.Japanese");
        System.out.println("\t3.Blind");
        System.out.println("\t4.Vickrey");
        System.out.println("\t5.Reserve");
        int select = input.nextInt();

        switch(select){
            case 1:
                auctionType = "english";
                break;
            case 2:
                auctionType = "japanese";
                break;
            case 3:
                auctionType = "blind";
                break;
            case 4:
                auctionType = "vickrey";
                break;
            case 5:
                auctionType = "reserve";
                break;
            default:
                System.out.println("Invalid entry.");
                break;
        }
        input.nextLine();
        deadline = validDateUI("auction deadline", input);

        if(!(deadline.compareTo(startDate) <= 0))
            System.out.println("Auction deadline and start date and time can't be equal!");
        
        if (addOrEdit.equalsIgnoreCase("add")){
            sellerID.addItem(itemName, startDate, minBidAmount, auctionType, deadline);
            auction = new Auction(itemID, itemName, startDate, minBidAmount, auctionType, deadline, sellerID);
        }
        else if(addOrEdit.equalsIgnoreCase("edit"))
            sellerID.edit(itemID, itemName, startDate, minBidAmount, auctionType, deadline);
    }
    
    public static void AuctionMenu(String itemID, Seller seller){
        seller.displayBidsfor(itemID);
    }

    public static void changePassword(String sellerID) throws IOException{
        Login a = new Login();
        Scanner input = new Scanner(System.in);
       
        System.out.print("Enter your current password: ");
        String oldPass = input.nextLine();
        System.out.print("Enter new password: ");
        String temp = input.nextLine();
        System.out.print("Repeat new password: ");
        String newPass = input.nextLine();
        if(temp.equals(newPass))
            a.changePassword(sellerID, oldPass, newPass, 1);
        else
            System.out.println("Passwords don't match. Try again");
    }
    public static void deleteAccount(String bidderID) throws IOException{
        Login a = new Login();
        System.out.println("You are about to permanently delete your account.");
        System.out.println("You won't be able to recover your account.");
        System.out.println("");
        System.out.print("Enter password to confirm:");
        Scanner input = new Scanner(System.in);
        String pw = input.nextLine();
        a.deleteAcc(bidderID, pw, 1);
    }
    
   
}
