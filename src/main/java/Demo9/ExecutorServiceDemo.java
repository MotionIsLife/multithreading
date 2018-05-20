package Demo9;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        System.out.println("submit worker 1");
        Future<String> future1 = executor.submit(new Worker("worker 1"));

        System.out.println("submit worker 2");
        Future<String> future2 = executor.submit(new Worker("worker 2"));

        System.out.println("result from worker1 = " + future1.get());
        System.out.println("result from worker2 = " + future2.get());

        System.out.println("------------------");

        System.out.println("submit workers using invokeAll()");
        List<Future<String>> futures = executor.invokeAll(Arrays.asList(new Worker("worker3"), new Worker("worker3"), new Worker("worker4")));

        System.out.println("Exiting invokeAll()");
        for (Future<String> future : futures) {
            System.out.println("Result from worker: " + future.get());
        }

        executor.shutdown();
        executor.awaitTermination(10L, TimeUnit.SECONDS);


    }
}
