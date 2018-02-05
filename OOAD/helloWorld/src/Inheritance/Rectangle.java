package Inheritance;

/**
 * Created by @kash on 1/13/2018.
 */
public class Rectangle {
    int breadth;
    int length;

    Rectangle(int len, int breadth)  {
        this.breadth = breadth;
        this.length = len;
    }


    void area() {
        System.out.println("Length = " + String.valueOf(length));
        System.out.println("Breadth = " + String.valueOf(breadth));
        System.out.println("The area of rectangle is: " +
                String.valueOf(breadth*length));
    }
}

class Box extends Rectangle {
    int height;
    Box(int length, int breadth, int height)   {
        super(length, breadth);
        this.height = height;
    }

    void volume() {
        area();
        System.out.println("Height = " + String.valueOf(height));
        System.out.println("Volume of the box is: " +
            String.valueOf(breadth*length*height));
    }
}

class InheritanceTest {
    public static void main(String[] args)  {
        Box b = new Box(4,5,6);
        b.volume();
    }
}
