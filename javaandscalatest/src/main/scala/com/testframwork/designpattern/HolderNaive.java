package com.testframwork.designpattern;

public class HolderNaive {
    private Heavy heavy;
    public HolderNaive() {
        System.out.println("Holder created");
    }
    public Heavy getHeavy() {
        if(heavy == null) {
            heavy = new Heavy();
        }
        return heavy;
    }
    //...
}
