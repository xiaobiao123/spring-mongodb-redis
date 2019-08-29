package other.thread00001;

public class AccountThread extends Thread {
    Account account;
    int delay;

    public AccountThread(Account acount, int delay) {
        this.account = acount;
        this.delay = delay;
    }

    public void run() {
        account.withdraw(100, delay);
    }

    public static void main(String[] args) {
        Account acount = new Account(100);
        AccountThread acountThread1 = new AccountThread(acount, 1000);
        AccountThread acountThread2 = new AccountThread(acount, 0);
        acountThread1.start();
        acountThread2.start();
    }
}