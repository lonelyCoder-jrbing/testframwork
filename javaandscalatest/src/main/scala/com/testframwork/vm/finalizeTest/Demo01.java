package com.testframwork.vm.finalizeTest;

public class Demo01 {

    public static void main(String[] args) throws Throwable {
        Demo01 demo01 = new Demo01();
        demo01 = null;
        System.gc();
        //当我们手动请求gc的时候会调用finalize方法.
        //一般是用于释放非java资源,(如打开的文件资源,数据库资源等),或是调用非java方法时候分配的内存
    }

    //另外，重写finalize()方法意味着延长了回收对象时需要进行更多的操作，从而延长了对象回收的时间。
    @Override
    protected void finalize() throws Throwable {
        System.out.println("调用finalize方法");

    }
}
