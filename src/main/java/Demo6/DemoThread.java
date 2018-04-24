package Demo6;

import java.util.concurrent.Semaphore;

public class DemoThread extends Thread{

    private Semaphore semaphore = null;

    public DemoThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            runUnsafe();
        } catch (InterruptedException ex) {
            System.out.println(getName() + " interrupted");
        }
    }

    private void runUnsafe() throws InterruptedException {
        for( ;; ) {
            semaphore.acquire();
            try {
                System.out.println(getName() + " acquired semaphore");
                Thread.sleep(5000L);
            } finally {
                System.out.println(getName() + " released semaphore");
                semaphore.release();
            }
        }
    }
}
