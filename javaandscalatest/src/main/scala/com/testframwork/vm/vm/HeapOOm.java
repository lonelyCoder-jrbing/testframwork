package com.testframwork.vm.vm;

import java.util.ArrayList;
import java.util.List;

public class HeapOOm {

    static class OOMObject {


    }
    //java堆用于存储大量的对象实例,只要不断的创建对象,并且保证gcroots到对象之间有可达路径,
    //来避免垃圾回收机制清楚这些对象,那么在对象数量达到最大堆的容量限制之后,就会产生内存溢出异常
    //来避免垃圾回收机制清楚这些对象,那么在对象数量达到最大堆的容量限制之后,就会产生内存溢出异常,

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }


}
