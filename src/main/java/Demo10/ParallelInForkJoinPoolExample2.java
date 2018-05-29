package Demo10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelInForkJoinPoolExample2 {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);

        long start = System.currentTimeMillis();
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        long end = System.currentTimeMillis();

        System.out.println("mergedResult = " + mergedResult);
        System.out.println("time = " + (end - start) + " ms");
    }

    public static class MyRecursiveTask extends RecursiveTask<Long> {

        private long workLoad = 0;

        public MyRecursiveTask(long workLoad) {
            this.workLoad = workLoad;
        }

        protected Long compute() {

            //if work is above threshold, break tasks up into smaller tasks
            if(this.workLoad > 16) {
                System.out.println("Splitting workLoad : " + this.workLoad);

                List<MyRecursiveTask> subtasks =
                        new ArrayList<>();
                subtasks.addAll(createSubtasks());

                for(MyRecursiveTask subtask : subtasks){
                    subtask.fork();
                }

                long result = 0;
                for(MyRecursiveTask subtask : subtasks) {
                    result += subtask.join();
                }
                return result;

            } else {
                System.out.println("Doing workLoad myself: " + this.workLoad);
                return workLoad * 3;
            }
        }

        private List<MyRecursiveTask> createSubtasks() {
            List<MyRecursiveTask> subtasks =
                    new ArrayList<>();

            MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
            MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

            subtasks.add(subtask1);
            subtasks.add(subtask2);

            return subtasks;
        }
    }
}
