package com.testframwork.jdk8.generalTypeTest;

/*内部维护着一个属性，但是它的类型是泛型，意味着你可以在使用的时候再定义它的具体类型*/
public class Test01<T> {

    private T t;

    public T getT() {
        return this.t;

    }

    public void setT(T t) {
        this.t = t;
    }


    public static void main(String[] args) {
        Test01<String> t = new Test01<String>();
        t.setT("jring");
        Test01<?> l = t;


    }
}
