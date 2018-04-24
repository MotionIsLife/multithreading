package Demo7;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new DemoThread(countDownLatch).start();
        }
    }

    private static class DemoThread extends Thread {
        private final CountDownLatch countDownLatch;

        DemoThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        private void runUnsafe() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 10000L));
            System.out.println(getName() + " finished initialization");

            countDownLatch.countDown();
            countDownLatch.await();

            System.out.println(getName() + " entered main phase");

            Thread.sleep((long) (Math.random() * 10000L));
        }

        @Override
        public void run() {
            try {
                runUnsafe();
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
            }
        }
    }
}
