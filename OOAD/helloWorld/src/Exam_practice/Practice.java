package Exam_practice;

/**
 * Created by @kash on 2/13/2018.
 */
public class Practice {
    public static void main(String args[])  {
        System.out.println("Hello");
        A a = new A();
        A a1 = new A();
        //B b = new B();
    }
}

class A {
    private void method(int a, char c)  {
        System.out.println("method");
    }

    A() {
        System.out.println("I am a constructor");
    }

    void method(char c, int a)  {
        System.out.println("method switched parameters");
    }

    static {
        System.out.println("This is a static block");
    }

    {
        System.out.println("This is a non static block");
    }
}

class B{
    protected void method(char c, int a) {

    }

    B() {
        System.out.println("I am a constructor for class B");
    }

    static {
        System.out.println("I am static block of class B");
    }
}

/*The sense of weaker means this:
*
* public /=/ private or protected below
* */
