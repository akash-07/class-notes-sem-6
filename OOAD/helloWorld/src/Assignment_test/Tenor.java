package Assignment_test;

/**
 * Created by @kash on 1/21/2018.
 */
class Singer {
    public static String sing() {
        return "la";
    }
}

public class Tenor extends Singer   {
    public static String sing() {
        return "fa";
    }

    public static void main(String[] args)  {
        Tenor t = new Tenor();
        Singer s = new Singer();
        System.out.println(t.sing() + " " + s.sing());
    }
}

//Static methods can be overridden by a subclass


