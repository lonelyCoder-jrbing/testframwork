package com.testframwork.designpattern;

/**
 * create by sumerian on 2020/6/21
 * <p>
 * desc:
 **/
public class VirtualProxyPattern {
    public static void main(String[] args) {

        final HolderNaive holder = new HolderNaive();
        System.out.println("deferring heavy creation...");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());

//上述代码在单线程环境中能够正常工作，但是在多线程环境中就不尽然了。当多个线程同时调用getHeavy方法时，也许会发生竞态条件(Race Condition)
// ，导致有多个Heavy实例被创建，最直观的解决方案就是给该方法加上synchronized关键字：

        Holder holder1 = new Holder();
        Heavy heavy = holder1.getHeavy();
        System.out.println(heavy);



    }


}
