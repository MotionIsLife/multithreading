package Accounts2;

public class Account {
    private long balance;

    public Account() {
        this(0L);
    }

    public Account(long balance) {
        this.balance = balance;
    }

    private void checkAmountNotNegative(long amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }

    public void deposit(long amount) {
        synchronized (this) {
            checkAmountNotNegative(amount);
            balance += amount;
            notifyAll();
        }
    }

    public synchronized void waitAndWithdraw(long amount) throws InterruptedException {
        checkAmountNotNegative(amount);
        while (balance < amount) {
            wait(100L);
        }
        balance -= amount;
        System.out.println(balance);
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
