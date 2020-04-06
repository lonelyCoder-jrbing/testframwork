package com.testframwork.jdk8.jdk8Thingking.Usablefunctions;

import static java.util.stream.IntStream.range;

public class Repeat {
    public static void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }
}
