package com.springdemo.scheduletask.task;

import java.util.Collection;

public abstract class People {

    public abstract String name();

    public abstract void work();

    public void startADay() {
        System.out.println("wake up ..drunk milke ,have breakfast...");
    }

    public void makeMoney() {
        System.out.println("make money.....");

    }

    public void endAday() {
        System.out.println("end of a day.....");
    }

    public void acion(Action... actions) {
        for (Action e : actions) {
            action(e);
        }
    }

    private void action(Action e) {
        switch (e) {
            case WAKE_UP:
                startADay();
                break;
            case SLEEP:
                endAday();
                break;
            case MAKE_MONEY:
                makeMoney();
                break;
            case GO_HOME:
                goHome();
                break;
            default:
                System.out.println("there is no action defined");
                break;

        }
    }

    public void goHome() {
        System.out.println("go home......");
    }

    ;


    enum Action {
        WAKE_UP, MAKE_MONEY, SLEEP, GO_HOME;
    }
}
