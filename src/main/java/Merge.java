import java.util.*;

public class Merge {
    private int numberThreads;
    private Object array[];
    private int left;
    private int right;
    private MergeThread [] threads;

    Merge(int threadNum, Object arr[]){
        numberThreads = threadNum;
        array = arr;
        threads = new MergeThread[numberThreads];
        left = 0;
        right = array.length;
    }

    class MergeThread extends Thread{
        private Object [] a;
        private int from;
        private int to;
        MergeThread(Object [] a, int from, int to){
            this.a = a;
            this.from = from;
            this.to = to;
        }
        @Override
        public void run() {
            sort(a, from, to);
        }
    }

    public void mergeSort(){
        if (numberThreads > array.length) {
            numberThreads = array.length;
        }
        int count = array.length / numberThreads; // сколько элементов будет сортировать каждый тред
        int additional = array.length % numberThreads; // сколько элементов будет дано дополнительно первому треду
        int from = 0;
        int to = 0;
        for (int i = 0; i < numberThreads; i++) {
            if (i == 0){
                from = 0;
                to = count + additional;
            }
            else if (i == 1){
                from = count + additional;
                to += count;
            }
            else {
                from += count;
                to += count;
            }
            threads[i] = new MergeThread(array, from, to);
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println("Array after thread's work: " + Arrays.toString(array));
        sort(array, left, right);
        System.out.println("Array after sorting: " + Arrays.toString(array));
    }

    public void sort (Object array[], int from, int to){
        Arrays.sort(array, from, to);
    }
}
