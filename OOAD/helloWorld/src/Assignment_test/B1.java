package Assignment_test;

/**
 * Created by @kash on 1/21/2018.
 */
public class B1 extends A {
    int i = 12;
    public void printValue()    {
        System.out.println("Value-B");
    }
}

class A {
    int i = 10;
    public void printValue()    {
        System.out.println("Value-A");
    }
}

class Run   {
    public static void main(String[] args)  {
        A a = new B1();
        a.printValue();
        System.out.println(a.i);
    }
}
