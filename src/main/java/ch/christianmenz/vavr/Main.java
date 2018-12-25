package ch.christianmenz.vavr;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        java.util.List<Integer> collect = forkJoinPool.submit(() -> List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).stream().parallel().map(i -> {
            System.out.println(Thread.currentThread().getName());
            return i;
        })).get().collect(Collectors.toList());

        System.out.println("time: " + (System.currentTimeMillis() - start));
        System.out.println(collect);

    }
}
