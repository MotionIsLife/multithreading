package Demo10;

import java.util.concurrent.*;

public class ParallelInExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] array = Common.prepareArray();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        long startTime = System.currentTimeMillis();
        Future<Double> feature1 = executorService.submit(new PartialCalc(array, 0, array.length / 2));
        Future<Double> feature2 = executorService.submit(new PartialCalc(array, array.length / 2, array.length));

        double sum = feature1.get() + feature2.get();
        long endTime = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println("time = " + (endTime - startTime) + "ms");

        executorService.shutdown();
    }

    private static class PartialCalc implements Callable<Double> {
        private final int[] array;
        private final int start;
        private final int end;

        public PartialCalc(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public Double call() throws Exception {
            return (Double) Common.calculate(this.array, start, end);
        }
    }
}
