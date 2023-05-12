import java.sql.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("\tWelcome To Our Bank!!!");
            System.out.println("\t Are you A?");
            while (true) {
                int mainmenuans = mainMenu();
                if (mainmenuans == 1) {
                    System.out.println("\tI want to:");
                    System.out.println();
                    System.out.println("\t\t1.Login for credit debit or transfer");
                    System.out.println("\t\t2.Check balance");
                    System.out.println("\t\tType 1 or 2 for procceding");
                    int mainchoice = sc.nextInt();
                    if (mainchoice == 1) {
                        User loggedin = Bank.login();
                        if (loggedin == null) continue;
                        System.out.println();

                        System.out.println("I want to :");
                        System.out.println();
                        System.out.println("\t\t1.Credit");
                        System.out.println("\t\t2.Debit");
                        System.out.println("\t\t3.Transfer");
                        int choice = sc.nextInt();
                        sc.nextLine();
                        if (choice == 1) {
                            Bank.credit(loggedin);
                        } else if (choice == 2) {
                            Bank.debit(loggedin);
                        } else if (choice == 3) {
                            Bank.transfer(loggedin);
                        }
                        if (gotoMainmenu()) {
                            continue;
                        } else break;
                    } else if (mainchoice == 2) {
                        User s = Bank.login();
                        if (s == null) {
                            continue;
                        }

                        System.out.println(s.getbalance());
                        if (gotoMainmenu()) {
                            continue;
                        } else break;
                    }
                } else if (mainmenuans == 2) {
                    System.out.println("Open Yout Account Today");
                    Bank.createAuser();
                    if (gotoMainmenu()) {
                        continue;
                    } else {
                        break;
                    }
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t 1.Exisisting Costumer?");
        System.out.println("\t\t 2.New Costumer?");
        System.out.println();
        System.out.println("\t\t press 1 or 2 for selcting");
        int ans = sc.nextInt();
        return ans;
    }

    public static boolean gotoMainmenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "\t\t type \"Back\" for mainmenu or type \"Exit\" for logout "
        );
        String st = sc.nextLine();
        if (st.equals("Back")) return true; else return false;
    }
}