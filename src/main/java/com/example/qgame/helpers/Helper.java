package com.example.qgame.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Helper {

    public static String limit(String text, int limit) {
        if (text.length() > limit) {
            return text.substring(0, limit) + " ...";
        }
        return text;
    }

    public static <T> List<List<T>> getBatches(List<T> list, int batchSize) {
        return IntStream.iterate(0, i -> i < list.size(), i -> i + batchSize)
                .mapToObj(i -> list.subList(i, Math.min(i + batchSize, list.size())))
                .toList();
    }

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 100).boxed().toList();
        System.out.println(list);
        System.out.println(getBatches(list,7));
    }
}
