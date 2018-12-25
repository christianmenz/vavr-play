package ch.christianmenz.vavr;

import io.vavr.CheckedFunction1;
import io.vavr.control.Try;

import java.util.List;
import java.util.stream.Collectors;

public class TryExample {

    public static void main(String[] args) {
        Try<Integer> test = Try.of(() -> convert("test"));
        System.out.println(test.isSuccess());
        System.out.println(test.isFailure());
        System.out.println(test.getOrElse(0));
        List.of("hello", "1", "2", "world").stream().map(i -> Try.of(() -> TryExample.convert(i))).collect(Collectors.toList());
        List.of("helllo", "1", "2").stream().map(CheckedFunction1.lift(i -> convert((i)))).forEach(o -> {
            System.out.println(o.getOrElse(-1));
        });

    }

    public static Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
