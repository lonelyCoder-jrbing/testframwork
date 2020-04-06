package com.testframwork.algorithm;

public class Test {
    public static void main(String[] args) {
           String s = "jurongbing001";

        int length = s.length();
        String replacement = "001";
        int length1 = replacement.length();
        for(int i = 0;i<length-length1+1;i++){
            System.out.println("i--->"+i);
            int i1 = i + replacement.length() ;
            System.out.println("i1--->"+i1);
            System.out.println("subString-->"+s.substring(i,i+replacement.length()));
            if(s.substring(i,i+replacement.length()).equals(replacement)){
                System.out.println("result--->"+i);
            }
        }

    }

}
