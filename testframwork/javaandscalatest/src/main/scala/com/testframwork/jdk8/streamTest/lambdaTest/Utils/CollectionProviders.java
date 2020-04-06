package com.testframwork.jdk8.streamTest.lambdaTest.Utils;


import com.testframwork.jdk8.streamTest.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionProviders {

    static List<Teacher> list = new ArrayList<>(10);

    public static class ListProvider {
        //        private ListProvider(){}
        public List<Teacher> getTeacherList() {
            Teacher teacher = new Teacher();
            teacher.setAge(Optional.ofNullable(25));
            teacher.setName(Optional.ofNullable("Mr_Zhang"));
            teacher.setIncome(Optional.ofNullable(20000.0));
            Teacher teacher01 = new Teacher();
            teacher01.setAge(Optional.ofNullable(26));
            teacher01.setName(Optional.ofNullable("Mr_Wang"));
            teacher01.setIncome(Optional.ofNullable(20001.0));
            Teacher teacher02 = new Teacher();
            teacher02.setAge(Optional.ofNullable(27));
            teacher02.setName(Optional.ofNullable("Mr_Li"));
//            teacher02.setIncome(Optional.ofNullable(20002.0));
            list.add(teacher);
            list.add(teacher01);
            list.add(teacher02);
            return list;
        }
    }

    private CollectionProviders() {
    }

    public static ListProvider getProvider() {

        ListProvider provider = new ListProvider();
        if (provider == null) {
            synchronized (provider) {
                if (provider == null) {
                    return new ListProvider();
                } else {
                    return provider;
                }
            }
        }
        return provider;
    }
}
