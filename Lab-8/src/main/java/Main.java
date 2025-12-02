import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        int size = 1_000_000;
        int[] array = new int[size];

        Random rnd = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rnd.nextInt(101); // 0–100
        }

        ForkJoinPool pool = new ForkJoinPool();

        long start = System.currentTimeMillis();
        long result = pool.invoke(new SumTask(array, 0, array.length));
        long end = System.currentTimeMillis();

        System.out.println("Сума: " + result);
        System.out.println("Час виконання: " + (end - start) + " ms");
        System.out.println("Кількість потоків: " + pool.getParallelism());
    }
}
