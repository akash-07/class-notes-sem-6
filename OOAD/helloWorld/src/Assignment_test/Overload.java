package Assignment_test;

/**
 * Created by @kash on 1/21/2018.
 */
public class Overload {
    void demo(int a)    {
        System.out.println("a: " + a);
    }

    void demo(int a, int b) {
        System.out.println("a = " + a + " b = " + b);
    }

    double demo(double d) {
        System.out.println("double d = " + d);
        return d*d;
    }
}

class MethodOverloading {
    public static void main(String[] args)  {
        Overload obj = new Overload();
        obj.demo(5);
        obj.demo(4,6);
        double result = obj.demo(2.0);
        System.out.println("O/P : " + result);
    }
}
