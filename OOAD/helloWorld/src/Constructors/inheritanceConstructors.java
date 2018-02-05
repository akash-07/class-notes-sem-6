package Constructors;

/**
 * Created by @kash on 1/21/2018.
 */
public class inheritanceConstructors {
    public static void main(String[] args)  {
        B b = new B();
        b.display();
    }
}

class A {
    int a;
    A(int p)    {
        a = p;
    }
    void display()  {
        System.out.println("a = " + a);
    }
}

class B extends A {
    B() {
        super(5);
        System.out.println("Sub class constructor");
    }
}
