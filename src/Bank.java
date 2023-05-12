import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Bank {
    static Scanner sc = new Scanner(System.in);
    static Connection con = createConncection.getCon();
    static String sql = "";

    public static User createAuser() {
        User user = null;
        try {
            String name;
            double phone_no;
            System.out.println("Enter name: ");
            name = sc.nextLine();
            System.out.println("Enter Phone No: ");
            phone_no = sc.nextDouble();
            sc.nextLine();
            if (name == "" || phone_no == 0) {
                System.out.println("Please Give Proper Info");
                return null;
            }
            user = new User(name, phone_no);
            sql = "INSERT INTO users(name,phone_no,accountNumber,balance)" +
                    "VALUES(?,?,?,?)";
            PreparedStatement ptst = con.prepareStatement(sql);
            ptst.setString(1, name);
            ptst.setDouble(2, phone_no);
            ptst.setInt(3, user.getaccountNumber());
            ptst.setInt(4, 0);
            ptst.executeUpdate();
            System.out.println("Added to the table");

        } catch (Exception e) {
            System.out.println("Error in Creating User");
        }
        return user;
    }

    public static boolean userExists(String name, double phoneNumber) {
        boolean users = false;
        try {
            sql = "SELECT * FROM users WHERE name = ? AND phone_no = ? ";
            PreparedStatement ptst = con.prepareStatement(sql);
            ptst.setString(1, name);
            ptst.setDouble(2, phoneNumber);
            ResultSet rs = ptst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                users = (count > 0);
            }
        } catch (Exception e) {
            System.out.println("Error Occoured");
            return false;
        }
        return users;
    }

    public static User getUser(String name, double phone_no) {
        try {
            sql = "SELECT * FROM users WHERE name = ? AND phone_no = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setDouble(2, phone_no);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int accountNumber = rs.getInt("accountNumber");
                int balance = rs.getInt("balance");
                return new User(name, phone_no, balance, accountNumber);
            }
        } catch (Exception e) {
            System.out.println("Error Occured");
        }
        return null;
    }

    public static void credit(User user) {
        try {
            if (userExists(user.getname(), user.getphoneNumber())) {
                System.out.println("Enter The Ammount");
                int ammount = sc.nextInt();
                sc.nextLine();
                int currentBalance = user.getbalance();
                int newBalance = currentBalance + ammount;
                sql = "UPDATE users SET balance = ? WHERE phone_no = ? ";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, newBalance);
                pst.setDouble(2, user.getphoneNumber());
                pst.executeUpdate();
                System.out.println("Money Credited Successfully");

            } else {
                System.out.println("User Does Not exist");

            }
        } catch (Exception e) {
            System.out.println("Error Occured");
        }


    }

    public static void debit(User user) {
        try {
            if (userExists(user.getname(), user.getphoneNumber())) {
                System.out.println("Enter The Ammount");
                int ammount = sc.nextInt();
                sc.nextLine();
                int currentBalance = user.getbalance();
                if (currentBalance < ammount) {
                    System.out.println("You have Low Balance");
                } else {
                    int newBalance = currentBalance - ammount;
                    sql = "UPDATE users SET balance = ? WHERE phone_no = ? ";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, newBalance);
                    pst.setDouble(2, user.getphoneNumber());
                    pst.executeUpdate();
                    System.out.println("Money Debited Successfully");
                }

            } else {
                System.out.println("User Does Not exist");

            }
        } catch (Exception e) {
            System.out.println("Error Occured");
        }
    }
    public static  User login(){
        System.out.println("Enter A name Of User:");
        String name = sc.nextLine();
        System.out.println("Enter The Phone Number:");
        double phone_no = sc.nextDouble();
        sc.nextLine();
        if(userExists(name,phone_no)){
            User loggedinuser= getUser(name,phone_no);
            System.out.println("SuccessFully Logged In");
            return loggedinuser;
        }
        else {
            System.out.println("The User Does not exist");
            return null;
        }
    }
    public static  void transfer(User sender){
        try {
            System.out.println("Give the Name of Reciver");
            String reciverName = sc.nextLine();
            System.out.println("Give the Phone Number of Reciver");
            double phone_no = sc.nextDouble();
            sc.nextLine();
            User reciver = getUser(reciverName, phone_no);
            if (reciver != null) {
                System.out.println("Give the Ammount you want to transfer");
                int ammount = sc.nextInt();
                int reciverbalance = reciver.getbalance();
                int senderbalance = sender.getbalance();
                if (senderbalance <= ammount) {
                    System.out.println("You have Insufficient balance You can not transfer Money");
                } else {
                    int newreciverbalance = reciverbalance + ammount;
                    int newsenderbalnce = senderbalance - ammount;
                    sql = "UPDATE users SET balance = ? WHERE phone_no = ? ";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, newreciverbalance);
                    pst.setDouble(2, reciver.getphoneNumber());
                    pst.executeUpdate();
                    pst.setInt(1, newsenderbalnce);
                    pst.setDouble(2, sender.getphoneNumber());
                    pst.executeUpdate();
                    System.out.println(
                            "You have Successfully Transfered " +
                                    ammount +
                                    " to User " +
                                    reciverName +
                                    " now your Balance is " +
                                    newsenderbalnce
                    );
                }
            } else {
                System.out.println("The User Does Not exist");
            }
        }
        catch(Exception e){
        System.out.println("Error Occured");}
    }
}