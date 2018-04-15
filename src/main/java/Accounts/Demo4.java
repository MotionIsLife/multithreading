package Accounts;

public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(100_000);
        System.out.println("begin balance " + account.getBalance());

        Thread withdrawThread = new WithdrawThread(account);
        Thread depositThread = new DepositThread(account);

        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();

        System.out.println("end balance = " + account.getBalance());
    }

    private static class WithdrawThread extends Thread {

        private final Account account;

        public WithdrawThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20_000; i++) {
                account.withdraw(1);
            }
        }
    }

    private static class DepositThread extends Thread {

        private final Account account;

        public DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20_000; i++) {
                account.deposit(1);
            }
        }
    }
}
