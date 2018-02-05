package Assignment_test;

/**
 * Created by @kash on 1/21/2018.
 */
public class Jail {
    private int x = 6;
    public static void main(String[] args)  {
        int x = 8;
        new Jail().new Cell().slam();
    }

    class Cell  {
        void slam() {
            System.out.println("throw away key " + x);
        }
    }
}
