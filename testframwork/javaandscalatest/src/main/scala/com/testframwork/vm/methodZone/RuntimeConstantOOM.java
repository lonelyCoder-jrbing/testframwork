package com.testframwork.vm.methodZone;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantOOM {
    public static void main(String[] args) {
            //使用list保持常量池的引用,避免full_GC回收常量池的行为
        List<String> list = new ArrayList<>();
        //10MB的permSize在integer范围之内足够产生oom
        int i= 0;
          while(true){
              //当intern方法被调用的时候,它的作用是如果字符串常量池中包含一个等于此String对象的字符串
              //则返回代表池中这个字符串的String对象,否则,将String对象包含的字符串放置到常量池中,
              //并且返回String对象的引用.
              //值的注意的是  常量池是属于方法区的一部分

              list.add(String.valueOf(i++).intern());
          }




    }


}
