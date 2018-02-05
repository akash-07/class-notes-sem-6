package com.company;

/**
 * Created by @kash on 1/9/2018.
 */
public class Person {
    String name;
    int age;
    void talk() {
        System.out.println("hello I am: " + name);
        System.out.println("My age is: " + age);
    }
}

class Demo {
    public static void main(String[] args)  {
        Person ak = new Person();
        ak.talk();
    }
}

class Demo1 {
    public static void main(String[] args)  {
        Person ak = new Person();
        ak.name = "Akash";
        ak.age = 20;
        ak.talk();
    }
}
