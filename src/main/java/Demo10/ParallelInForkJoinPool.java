package Demo10;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelInForkJoinPool {
    public static void main(String[] args) {
        int[] array = Common.prepareArray();
        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
        Double sum = pool.invoke(new RecursiveCalc(array, 0, array.length-1));
        long endTime = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println("time = " + (endTime - startTime) + "ms");
        pool.shutdown();
    }

    private static class RecursiveCalc extends RecursiveTask<Double> {

        private static final int SEQUENTIAL_THRESHOLD = 50000;

        private final int end;
        private final int start;
        private final int[] array;

        public RecursiveCalc(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        protected Double compute() {
            if(end -  start <= SEQUENTIAL_THRESHOLD) {
                return Common.calculate(array, start, end);
            } else {
                int mid = start + (end - start) / 2;
                RecursiveCalc left = new RecursiveCalc(array, start, mid);
                RecursiveCalc right = new RecursiveCalc(array, mid, array.length-1);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }
}
