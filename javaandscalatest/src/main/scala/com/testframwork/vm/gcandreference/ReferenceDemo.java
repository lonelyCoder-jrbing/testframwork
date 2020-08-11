package com.testframwork.vm.gcandreference;

import com.testframwork.jdk8.aboutSort.Students;
import lombok.Data;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * create by sumerian on 2020/8/8
 * <p>
 * desc:软引用使用示例
 **/
public class ReferenceDemo {

    public static void main(String[] args) {
//        String s = softDemo();
//        System.out.println("s:  "+s);
//        weakDemo();
        getStudents();

    }

    public static String softDemo() {
//        Object obj = new Object();
        Students students = new Students(1, 12, "jurongbing");
        SoftReference<Object> sf = new SoftReference<Object>(students);
        students = null;
        sf.get();//有时候会返回null
//    sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象，当这个对象被标记为需要回收的对象时，则返回null
        boolean enqueued = sf.isEnqueued();
        System.out.println("enqueued:  " + enqueued);
        return sf.get().toString();
    }

    public static String weakDemo() {
//        Object obj = new Object();
        Students students = new Students(1, 12, "jurongbing");
        WeakReference<Object> sf = new WeakReference<Object>(students);
        students = null;
        sf.get();//有时候会返回null
//    sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象，当这个对象被标记为需要回收的对象时，则返回null
        boolean enqueued = sf.isEnqueued();
        System.out.println("enqueued:  " + enqueued);
        return sf.get().toString();

    }

    public static WeakHashMap<Integer, Object> getStudents() {
        WeakHashMap<Integer, Object> stu = new WeakHashMap<>();
        Students students = new Students(1, 12, "jurongbing");
        stu.put(0,stu);
        System.gc();
        System.out.println("stu:   size==={}"+stu.size());
        return null;
    }

    @Data
    public static class Students implements Comparable<com.testframwork.jdk8.aboutSort.Students> {
        private Integer id;
        private int age;
        private String name;

        public Students(Integer id, int age, String name) {
            this.age = age;
            this.name = name;
            this.id = id;

        }

        @Override
        public int compareTo(com.testframwork.jdk8.aboutSort.Students o) {
            return this.getAge() - o.getAge();
        }
    }

}
