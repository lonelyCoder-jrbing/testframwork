package com.testframwork.jdk8.jdk8Thingking.strategy;

public class Strategize {
    Strategy strategy;
    String msg;

    public Strategize(String msg) {
        this.strategy = new Soft();
        this.msg = msg;
    }

    void communicate() {
        System.out.println(strategy.approach(msg));
    }


    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        //了解为啥会在这里创建多个不同的对象
        Strategy[] strategies = {
                //1.匿名内部类的实现方式
                new Strategy() {
                    //重新实现了策略.
                    @Override
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                //2.jdk8的实现方式  lambda表达式
                msg -> msg.substring(0, 5), // [3]
                //3.
                Unrelated::twice // [4]
        };
        System.out.println("size:  " + strategies.length);

//     Strategize s = new Strategize("hello there");
        Strategize s = new Strategize("Hello there");
        s.communicate();
        //调用的是默认的实现  soft,构造器中的策略是soft,
        for (Strategy newStrategy : strategies) {
//            System.out.println("msg: "+s.msg);
//            System.out.println("strategy:    "+s.strategy);
            s.changeStrategy(newStrategy); // [5]
            s.communicate(); // [6]
        }
        //=====================test01============
        Strategy[] strategies1 = {Unrelated::twice};
        Strategize s2 = new Strategize("strange!");
        s2.changeStrategy(strategies1[0]);
        s.communicate();
//        strategies1[0].approach("strange!");


    }

}
