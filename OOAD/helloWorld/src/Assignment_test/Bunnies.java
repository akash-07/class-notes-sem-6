package Assignment_test;

/**
 * Created by @kash on 1/21/2018.
 */
public class Bunnies {
    static int count = 0;
    Bunnies()   {
        while(count < 10)   {
            count++;
        }
    }

    Bunnies(int x)  {
        this();
    }

    public static void main(String[] args)  {
        new Bunnies();
        System.out.println(count);
        new Bunnies(count);
        System.out.println(count++);
    }
}
