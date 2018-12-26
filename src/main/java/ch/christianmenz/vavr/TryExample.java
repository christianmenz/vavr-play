package ch.christianmenz.vavr;

import io.vavr.CheckedFunction1;
import io.vavr.control.Try;

import java.util.List;

public class TryExample {

    public static void main(String[] args) {
        // Simple Try example
        System.out.println("--- simple try example ---");
        Try<Integer> test = Try.of(() -> convert("test"));
        System.out.println(test.isSuccess());
        System.out.println(test.isFailure());
        System.out.println(test.getOrElse(0));

        // Try of in a stream
        System.out.println("--- try of in a stream ---");
        List.of("hello", "1", "2", "world").stream().map(i -> Try.of(() -> TryExample.convert(i))).map(o -> o.getOrElse(-1)).forEach(System.out::println);

        // Using checked function
        System.out.println("--- using checked function ---");
        List.of("helllo", "1", "2").stream().map(CheckedFunction1.lift(i -> convert((i)))).forEach(o -> {
            System.out.println(o.getOrElse(-1));
        });

    }

    public static Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
