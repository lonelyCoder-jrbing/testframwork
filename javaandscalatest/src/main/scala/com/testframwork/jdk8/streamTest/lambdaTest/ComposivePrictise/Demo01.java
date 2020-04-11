package com.testframwork.jdk8.streamTest.lambdaTest.ComposivePrictise;


import com.testframwork.jdk8.streamTest.Teacher;
import com.testframwork.jdk8.streamTest.lambdaTest.Utils.CollectionProviders;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo01 {
    @Test
    public void test01() {
        CollectionProviders.ListProvider provider = CollectionProviders.getProvider();
        List<Teacher> teacherList = provider.getTeacherList();
        System.out.println(teacherList);
        Map<String, Teacher> collect = teacherList.stream().collect(Collectors.toMap(apply -> apply.getAge().get() + apply.getName().get(),
                apply -> new Teacher(apply.getName(), apply.getAge()),
                (oldvalue, newvalue) -> {
                    newvalue.setName(oldvalue.getName());
                    return newvalue;
                }));
        System.out.println(collect);
        BigDecimal bg = new BigDecimal(12);
        BigDecimal add = bg.add(new BigDecimal(13));
        System.out.println(add.intValue());
    }

    @Test
    public void test02() {
        CollectionProviders.ListProvider provider = CollectionProviders.getProvider();
        List<Teacher> teacherList = provider.getTeacherList();
        Map<Optional<Integer>, Optional<String>> collect = teacherList.stream().collect(Collectors.toMap(Teacher::getAge, Teacher::getName, (v1, v2) -> {
            //lamda 表达式再将list转为map的操作中，如果key值出现重复，以上写法会将k2对应的value覆盖k1对应的value
            System.out.println("v1:" + v1);
            System.out.println("v2:" + v2);
            return v2;
        }));
        collect.values().forEach(e -> {
            System.out.println("value:   " + e.get());
        });
    }
}
