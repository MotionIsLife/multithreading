package Accounts2;

public class Demo5 {
    /***
     * Снимем со счета 50К тогда, когда на нем будет нужная сумма
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        new DepositThread(account).start();
        System.out.println("calling waitAndWithdraw()...");
        account.waitAndWithdraw(50_000_000);
        System.out.println("waitAndWithdraw() finished");
    }

    private static class DepositThread extends Thread {

        private final Account account;

        public DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50_000_000; i++) {
                account.deposit(1);
            }
        }
    }
}
