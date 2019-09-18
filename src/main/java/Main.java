import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int size1 = 500;
        int size2 = 500;
        // создание первой рандомной матрицы
        UsualMatrix um1 = new UsualMatrix(size1, size2);
        Random rnd = new Random();
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                um1.setElement(i, j, rnd.nextInt(20));
            }
        }
        // создание второй рандомной матрицы
        UsualMatrix um2 = new UsualMatrix(size2, size1);
        for (int i = 0; i < size2; i++) {
            for (int j = 0; j < size1; j++) {
                um2.setElement(i, j, rnd.nextInt(20));
            }
        }
        // параллельное умножение, подсчет времени выполнения
        long start = System.nanoTime();
        ParallelMatrixProduct parallelMatrixProduct = new ParallelMatrixProduct(32);
        UsualMatrix par = parallelMatrixProduct.product(um1, um2);
        long finish = System.nanoTime();
        float timeConsumedMillis = (float) ((finish - start) * 1.0 / 1000000000);
        System.out.println("Parallel multiplication time: " + timeConsumedMillis + "s");

        // обычное умножение, подсчет времени его выполнения
        start = System.nanoTime();
        um1.product(um2);
        finish = System.nanoTime();
        timeConsumedMillis = (float) ((finish - start) * 1.0 / 1000000000);
        System.out.println("Usual multiplication time: " + timeConsumedMillis + "s");

        // сравнение двух результатирующих матриц
        if (um1.equals(par)) {
            System.out.println("Matrix are equal!");
        } else {
            System.out.println("Matrix aren't equal!");
        }

        // дополнительное задание - реализовать сортировку слиянием с помощью потоков
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of threads: ");
        int numOfThreads = in.nextInt();
        System.out.print("Enter the size of array: ");
        int size = in.nextInt();
        Integer[] a = new Integer[size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(1000000);
        }
        System.out.println("Not sorted array: " + Arrays.toString(a));
        // выполнение сортировки слиянием
        Merge merging = new Merge(numOfThreads, a);
        long startTime = System.currentTimeMillis();
        merging.mergeSort();
        long finalTime  = System.currentTimeMillis();
        long timeOfWorkUsingThreads = finalTime - startTime;
        System.out.println("Time of merge sort using threads: " + timeOfWorkUsingThreads);

        Integer[] b = new Integer[size];
        for (int i = 0; i < b.length; i++) {
            b[i] = rand.nextInt(1000000);
        }
        Merge merging2 = new Merge(1, b);
        startTime = System.currentTimeMillis();
        merging2.mergeSort();
        finalTime = System.currentTimeMillis();
        long timeOfWorkUsingThread = finalTime - startTime;
        System.out.println("Time of merge sort using 1 thread: " + timeOfWorkUsingThread);

        if (timeOfWorkUsingThreads < timeOfWorkUsingThread)
            System.out.println("Merge sort working faster using threads!");
    }
}
