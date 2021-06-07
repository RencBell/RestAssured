package com.rest;

public class MethodChainingTest {

    public static void main(String[] args){
        a1().
        a2().
        a3();
    }

    public static MethodChainingTest a1(){
        System.out.println("this is a1 method");
        return new MethodChainingTest();
    }

    public MethodChainingTest a2(){
        System.out.println("this is a2 method");
        return this;
    }

    public MethodChainingTest a3(){
        System.out.println("this is a3 method");
        return this;
    }
}
