package com.testframwork.jdk8.jdk8Thingking.enums;

import java.util.stream.IntStream;

public class TrafficLight {
    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            // Note you don't have to say Signal.RED
            // in the case statement:
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
//        for (int i = 0; i < 7; i++) {
//            System.out.println(t);
//            t.change();
//        }
        IntStream.range(0, 7).mapToObj(i -> {
//           return new TrafficLight();
            t.change();
            return t.color;
        }).forEach(el -> {
            System.out.println(el);
        });
    }
}