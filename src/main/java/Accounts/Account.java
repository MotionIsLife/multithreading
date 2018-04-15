package Accounts;

public class Account {
    private long balance;

    public Account() {
        this(0L);
    }

    public Account(long balance) {
        this.balance = balance;
    }

    public void withdraw(long amount) {
        synchronized (this) {
            checkAmountNotNegative(amount);
            if(balance < amount) {
                throw new IllegalArgumentException("not enough money");
            }
            balance -= amount;
        }
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
        }
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
