package com.testframwork.jdk8.functionTest;

public enum Name {
    JRBING("rongbingju"), BYY("hakuosang");
    private String enName;
//  private String CHName;


    Name(String enName) {
        this.enName = enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnName() {
        return enName;
    }
}
