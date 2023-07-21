import java.sql.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      int mainmenuans = mainMenu();
      switch (mainmenuans) {
        case 1:
          handleBankOperations(sc);
          break;
        case 2:
          System.out.println("Open Your Account Today");
          Bank.createAuser();
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }

      if (!gotoMainmenu()) {
        break;
      }
    }

    sc.close();
  }

  public static int mainMenu() {
    System.out.println("\tWelcome to Our Bank");
    System.out.println();
    System.out.println("\tChoose from the following:");
    System.out.println("\t\t1. Existing User");
    System.out.println("\t\t2. New User");
    System.out.println("\t\tType 1 or 2 for proceeding");
    Scanner sc = new Scanner(System.in);
    return sc.nextInt();
  }

  public static boolean gotoMainmenu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\nDo you want to go back to the main menu? (Y/N)");
    String choice = sc.next();
    return choice.equalsIgnoreCase("Y");
  }

  public static void handleBankOperations(Scanner sc) {
    System.out.println("\tI want to:");
    System.out.println();
    System.out.println("\t\t1. Login for credit, debit, or transfer");
    System.out.println("\t\t2. Check balance");
    System.out.println("\t\tType 1 or 2 for proceeding");
    int mainchoice = sc.nextInt();

    switch (mainchoice) {
      case 1:
        User loggedin = Bank.login();
        if (loggedin != null) {
          handleLoggedInUser(sc, loggedin);
        }
        break;
      case 2:
        User s = Bank.login();
        if (s != null) {
          System.out.println(s.getbalance());
        }
        break;
      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }

  public static void handleLoggedInUser(Scanner sc, User loggedin) {
    System.out.println();
    System.out.println("I want to:");
    System.out.println();
    System.out.println("\t\t1. Credit");
    System.out.println("\t\t2. Debit");
    System.out.println("\t\t3. Transfer");
    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
      case 1:
        Bank.credit(loggedin);
        break;
      case 2:
        Bank.debit(loggedin);
        break;
      case 3:
        Bank.transfer(loggedin);
        break;
      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }
}
