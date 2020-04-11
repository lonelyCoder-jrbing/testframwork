package com.testframwork.jdk8.streamTest.lambdaTest.filterTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo01 {


    public static void main(String[] args) {
        List<Predicate<Person>> rules = getPredict();

        List<Person> persons = IntStream.range(1, 100).mapToObj(i -> {
            Person person = new Person();
            person.setName("Name" + i);
            person.setAge(i);
            person.setSex(i % 2);
            return person;
        }).collect(Collectors.toList());
        List<Person> newPersons2 = filterUsingPredicate(persons, rules);
        System.out.println(newPersons2);
        List<Person> newPersons = customFilter(persons, rules);
        System.out.println(newPersons);

    }

    private static <I> List<I> filterUsingPredicate(Collection<I> persons, List<Predicate<I>> mappers) {
        return persons.stream()
                .filter(ele -> mappers.stream()
                        .reduce(t -> true, Predicate::and)
                        .test(ele)).collect(Collectors.toList());
    }


    /**
     * 根据过滤规则过滤集合
     *
     * @param persons 数据集合
     * @param mappers 规律规则
     * @param <I>     数据类型
     * @return 过滤后的数据集合
     */
    private static <I> List<I> customFilter(Collection<I> persons, List<Predicate<I>> mappers) {
        Stream<I> iStream = persons.stream();
        return iStream.filter(ele -> {
            int compareSum = mappers.stream()
                    .mapToInt(mapper -> mapper.test(ele) ? 1 : 0).sum();
            return compareSum == mappers.size();
        }).collect(Collectors.toList());
    }

    public static List<Predicate<Person>> getPredict() {
        Predicate<Person> rule2 = person -> person.getSex() == 1;
//        Predicate<Person> negate = rule2.negate();
//        System.out.println(negate.test(persons.get(1)));
        Predicate<Person> rule3 = person -> Objects.equals(person.getName(), "Name1");
        List<Predicate<Person>> rules = new ArrayList<>();
        rules.add(rule2);
        rules.add(rule3);
        return rules;
    }


}
