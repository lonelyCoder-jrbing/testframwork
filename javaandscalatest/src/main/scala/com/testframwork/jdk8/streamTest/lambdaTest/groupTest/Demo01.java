package com.testframwork.jdk8.streamTest.lambdaTest.groupTest;


import com.testframwork.jdk8.streamTest.Teacher;
import com.testframwork.jdk8.streamTest.lambdaTest.Utils.CollectionProviders;
import org.junit.Test;

import java.util.List;

public class Demo01 {

    @Test
    public void test01() {
        List<Teacher> teacherList = CollectionProviders.getProvider().getTeacherList();

//        teacherList.stream().collect(Collectors.groupingBy(e->{e.getName().get(),Collectors.mapping(e.getAge().get(),Collectors.toList())};);
    }


}
