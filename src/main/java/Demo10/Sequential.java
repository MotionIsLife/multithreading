package Demo10;

public class Sequential {
    public static void main(String[] args) {
        int[] array = Common.prepareArray();
        long startTime = System.currentTimeMillis();
        double sum = Common.calculate(array);
        long endTimes = System.currentTimeMillis();

        System.out.println("sum = " + sum);
        System.out.println("time = " + (endTimes - startTime) + "ms");
    }
}
