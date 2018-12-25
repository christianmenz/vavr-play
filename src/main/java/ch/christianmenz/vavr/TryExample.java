package ch.christianmenz.vavr;

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

    }

    public static Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
