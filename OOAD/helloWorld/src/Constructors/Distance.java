package Constructors;

/**
 * Created by @kash on 1/21/2018.
 */
public class Distance {
    int feet;
    int inches;
    Distance(int feet, int inches)  {
        this.feet = feet;
        this.inches = inches;
    }

    void display()  {
        System.out.println(feet + " feet " + inches + " inches");
    }

    Distance add(Distance d1)  {
        int f = d1.feet + this.feet;
        int i = d1.inches + this.inches;
        if (i >= 12)    {
            f++;
            i -= 12;
        }
        return new Distance(f,i);
    }
}

class test {
    public static void main(String[] args)  {
        Distance d1 = new Distance(10,9);
        Distance d2 = new Distance(9,10);
        System.out.println("First distance is: ");
        d1.display();
        System.out.println("Second distance is: ");
        d2.display();
        Distance d3 = d1.add(d2);
        System.out.println("After adding: ");
        d3.display();
    }
}




