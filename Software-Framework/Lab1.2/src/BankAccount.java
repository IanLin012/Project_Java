// 1.2 練習3：封裝與this
public class BankAccount {
    private String accountNumber; // 帳號
    private double balance; // 餘額

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) { // 存款
        if (amount > 0) {
            balance += amount;
            System.out.println("已存款：" + amount);
        } else {
            System.out.println("存款金額必須大於 0。");
        }
    }

    public void withdraw(double amount) { // 提款
        if (amount > balance) {
            System.out.println("餘額不足");
        } else if (amount <= 0) {
            System.out.println("提款金額必須大於 0。");
        } else {
            balance -= amount;
            System.out.println("已提款：" + amount);
        }
    }

    public void displayBalance() { // 顯示目前的餘額
        System.out.println("目前餘額：" + balance + "\n");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("123", 1000.0);

        account.displayBalance();

        account.deposit(500.0);
        account.displayBalance();

        account.withdraw(200.0);
        account.displayBalance();

        account.withdraw(1500.0);
        account.displayBalance();
    }
}
