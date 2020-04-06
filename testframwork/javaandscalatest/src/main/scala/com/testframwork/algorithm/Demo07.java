package com.testframwork.algorithm;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Demo07 implements MethodBeforeAdvice {
    private static final Map<String, Integer> map = new HashMap<String, Integer>();
    private int allCount;


    public static void main(String[] args) {
        Demo07 demo07 = new Demo07();

    }


    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        count(method);

    }

    public void count(Method m) {
        count(m.getName());
    }

    public void count(String name) {
        Integer i = map.get(name);
        i = (i != null ? new Integer(i.intValue() + 1) : new Integer(1));
        map.put(name, i);
        ++allCount;
    }


}
