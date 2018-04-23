package General;

public class Demo1 {
    /**
     * Приветствие с разных потоков
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new HelloThread().start();
        }
        System.out.println("Hello from main thread");
    }

    private static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + getName());
        }
    }
}
