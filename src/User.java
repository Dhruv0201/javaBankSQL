import java.util.Random;

public class User {
    private int balance;
    private String name;
    private double phoneNumber;
    private int accountNumber;

    public User(String name, double phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountNumber = genrateRandomaccountNumber();
        this.balance = 0;
    }
    private int genrateRandomaccountNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    public int getbalance() {
        return balance;
    }
    public String getname() {
        return name;
    }
    public double getphoneNumber() {
        return phoneNumber;
    }
    public int getaccountNumber() {
        return accountNumber;
    }
    public User(String name,double phoneNumber,int balance,int accountNumber){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
