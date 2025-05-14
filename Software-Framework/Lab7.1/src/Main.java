class BankAccount {
    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void displayBalance() {
        System.out.println("帳戶餘額：" + this.balance);
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(12345, 1000.0);
        // 直接修改帳戶餘額 (違反資訊隱藏)
        account.withdraw(1500);
        account.displayBalance(); // 輸出錯誤的餘額
    }
}
