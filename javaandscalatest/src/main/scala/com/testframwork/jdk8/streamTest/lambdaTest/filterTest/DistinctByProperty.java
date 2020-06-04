package com.testframwork.jdk8.streamTest.lambdaTest.filterTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class DistinctByProperty {
    public static void main(String[] args) {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Core Java", 200));
        list.add(new Book("Core Java", 300));
        list.add(new Book("Learning Freemarker", 150));
        list.add(new Book("Spring MVC", 200));
        list.add(new Book("Hibernate", 300));

        list.stream().filter(distinctByKey(Book::getName)).forEach(System.out::println);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    static class Book {
        private String name;
        private int price;

        public Book(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

}
