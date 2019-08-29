package other.thread00001;

public   class  BankAccount {
    private int number;
    private int balance;

    public BankAccount(int number, int balance) {
        this.number = number;
        this.balance = balance;
    }

    public  int getBalance() {
        return balance;
    }

    public  void deposit(int amount) {
        balance = balance + amount;
    }

    public  void withdraw(int amount) {
        balance = balance - amount;
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount a = new BankAccount(1, 1000);
        Thread t1 = new Thread(new Depositor(a, 100), "depositor");
        Thread t2 = new Thread(new Withdrawer(a, 100), "withdraw");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a.getBalance());
    }

     static  class  Depositor implements Runnable {
        BankAccount account;
        int amount;

        public Depositor(BankAccount account, int amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++)
                account.deposit(amount);
        }
    }

    static class Withdrawer implements Runnable {
        BankAccount account;
        int amount;

        public Withdrawer(BankAccount account, int amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++)
                account.withdraw(amount);
        }
    }
}