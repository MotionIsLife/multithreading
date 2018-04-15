package General;

public class Demo2 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println("Hello from " + Thread.currentThread().getName())).start();
        }
        System.out.println("Hello from main thread");
    }

    private static class HelloRunnableThread implements Runnable {

        public void run() {
            System.out.println("Hello from " + Thread.currentThread().getName());
        }
    }
}
