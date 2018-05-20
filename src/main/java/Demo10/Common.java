package Demo10;

public class Common {
    public static int[] prepareArray() {
        int[] array = new int[20000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static Double calculate(int[] array) {
        return calculate(array, 0, array.length-1);
    }

    public static Double calculate(int[] array, int start, int end) {
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += function(array[i]);
        }
        return sum;
    }

    private static Double function(int i) {
        return Math.sin(i);
    }
}
