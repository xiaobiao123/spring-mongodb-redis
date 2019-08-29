package other.thread00001;

public class Account {
    private double balance;

    public Account(double money) {
        balance = money;
        System.out.println("Totle Money: " + balance);
    }

    public void deposit(double money) {
        balance = balance + money;
    }

    public void withdraw(double money, int delay) {
        if (balance >= money) {
            try {
                Thread.sleep(delay);
                balance = balance - money;
                System.out.println(Thread.currentThread().getName()
                        + " withdraw " + money + " successful!" +
                        balance);
            } catch (InterruptedException e) {
            }
        } else
            System.out.println(Thread.currentThread().getName()
                    + " balance is not enough, withdraw failed!" +
                    balance);
    }
}