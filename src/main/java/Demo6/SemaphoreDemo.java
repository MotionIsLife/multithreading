package Demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Пример справедливого семафора
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2, true);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DemoThread demoThread = new DemoThread(semaphore);
            threads.add(demoThread);
            demoThread.start();
        }

        Thread.sleep(20000);

        for (Thread thread : threads) {
            thread.interrupt();
        }

    }
}
