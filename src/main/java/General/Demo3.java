package General;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        SleepThread sleepThread = new SleepThread();
        WorkerThread workerThread = new WorkerThread();

        System.out.println("Starting threads");
        workerThread.start();
        sleepThread.start();

        Thread.sleep(100L);

        System.out.println("Interrupted threads");
        workerThread.interrupt();
        sleepThread.interrupt();

        System.out.println("Joining threads");
        workerThread.join();
        sleepThread.join();

        System.out.println("All done");
    }

    private static class WorkerThread extends Thread {

        @Override
        public void run() {
            long sum = 0;
            for (int i = 0; i < 1_000_000_000; i++) {
                sum += i;
                if(i % 100 == 0 && isInterrupted()) {
                    System.out.println("lookup interrupted at i = " + i);
                    break;
                }
            }
        }
    }

    private static class SleepThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
    }
}
