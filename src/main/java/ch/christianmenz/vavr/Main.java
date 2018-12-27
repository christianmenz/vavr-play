package ch.christianmenz.vavr;


import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        // The main thread submits the task to the pool, is this the reason main is used?
        java.util.List<Integer> collect = forkJoinPool.submit(() -> List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).stream().parallel().map(i -> {
            System.out.println(Thread.currentThread().getName());
            return i;
        })).get().collect(Collectors.toList());

        System.out.println("time: " + (System.currentTimeMillis() - start));
        System.out.println(collect);

        // Let's check if the whole stuff is submitted by another thread

        System.out.println("---- ");

        Callable<Object> callable = () -> {
            List<Integer> collect1 = forkJoinPool.submit(() -> List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).stream().parallel().map(i -> {
                System.out.println(Thread.currentThread().getName());
                return i;
            })).get().collect(Collectors.toList());
            return null;
        };
        ForkJoinTask<Object> submit = forkJoinPool.submit(callable);// use the pool to submit...

        // wait for termination of the task if needed..
        submit.get();

    }
}
