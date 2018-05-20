package Demo9;

import java.util.concurrent.Callable;

public class Worker implements Callable<String> {
    private final String s;

    public Worker(String s) {
        this.s = s;
    }

    @Override
    public String call() throws InterruptedException {
        long sleepTime = (long) (Math.random() * 10000L);
        System.out.println(s + " started, going to sleep for " + sleepTime);
        Thread.sleep(sleepTime);
        System.out.println(s + " finished");
        return this.s;
    }
}
