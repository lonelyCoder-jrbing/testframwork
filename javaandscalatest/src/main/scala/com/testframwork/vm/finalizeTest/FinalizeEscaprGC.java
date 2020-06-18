package com.testframwork.vm.finalizeTest;

public class FinalizeEscaprGC {
    //任何对象的finalize()方法都只会被调用一次
    public static FinalizeEscaprGC save_Hook = null;

    public void isAlive() {
        System.out.println("yes i am  still alive");
    }
    //该方法执行的不确定性很大，不能保证一定会被执行，因此可以用finally去执行必须要执行的方法
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize() method executed! ");
        FinalizeEscaprGC.save_Hook = this;
    }

    public static void main(String[] args) throws InterruptedException {
     try{
         save_Hook = new FinalizeEscaprGC();
         //对象第一次成功拯救自己
         //在触发垃圾回收集的时候,重新建立引用链.
         save_Hook = null;
         System.gc();
         //因为finalize方法优先级很低,暂停500秒执行
         Thread.sleep(500);
         if (save_Hook != null) {
             save_Hook.isAlive();
             System.out.println("save_Hook:   "+save_Hook);
         } else {
             System.out.println("no i am dead!");
         }
         //一个对象的finalize()方法只能被调用一次
         save_Hook = null;
         System.gc();
         Thread.sleep(500);
         if (save_Hook != null) {
             save_Hook.isAlive();
         } else {
             System.out.println("no i am dead2");
         }

     }finally {
         save_Hook = new FinalizeEscaprGC();
         System.out.println("finally:   "+save_Hook);
     }

    }

    @Override
    public String toString() {
        return "FinalizeEscaprGC{}";
    }
}
