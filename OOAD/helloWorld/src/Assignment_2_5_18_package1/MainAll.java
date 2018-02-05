package Assignment_2_5_18_package1;

/**
 * Created by @kash on 2/5/2018.
 */
public class MainAll {
    static int count = 0;
    public static void main(String[] args)  {
        //Labelled break
        outerLoop:
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 2 ; j++) {
                System.out.println("(i,j) = (" + i + "," + j + ")");
                if(i > 3)
                    break outerLoop;
            }


        //Labelled Continue
        outerLoop1:
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 3 ; j++) {
                System.out.println("(i,j) = (" + i + "," + j + ")");
                if(j > 1)
                    continue outerLoop1;
            }

            sayHi();
            Animal animal = new Dog();
            Dog dog = new Dog();
            //dog.eat();
            DalmationDog dalmationDog = new DalmationDog();
            //Eat has a protected access
            dalmationDog.eat();
            Conrete conrete = new Conrete();
            conrete.sayAbstract();
            try {
                int z = 1/0;
            }
            catch (Exception e) {
               System.out.println(e.getMessage());
            }
            finally {
                System.out.println("I am in a finally block");
            }

            //Demonstrating throw and throws
            try{
                access();
            }
            catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
    }

        static void sayHi() {
            System.out.println("I am a static method");
            System.out.println("I am incrementing count static variable --> ");
            count++;
            System.out.println("Count = " + count);
        }

        // Method which throws exception
        static void access() throws IllegalAccessException   {
            throw new IllegalAccessException("accessed illegally");
        }
}

interface LivingBeing   {
    void numLegs();
}
//extends
class Animal implements LivingBeing{
    Animal()    {
        System.out.println("I am an Animal");
    }

    void speak()    {
        System.out.println("I am an undefined animal, I cannot speak");
    }

    @Override
    public void numLegs() {
        System.out.println("I am an undefined animal, I don't have legs yet");
    }
}

class Dog extends Animal  {
    Dog()   {
        super();
        System.out.println("Basically I am street dog");
    }

    Dog(String type)    {
        super();
        System.out.println("I am a " + type + " dog");
    }

    @Override
    public void numLegs() {
        System.out.println("#legs = 4");
    }

    @Override
    void speak() {
        System.out.println("Bhow bhow");
    }

    final protected void eat()  {
        System.out.println("I am eating");
    }
}

class DalmationDog extends Dog  {
    DalmationDog()  {
        super();
        System.out.println("I am a Dalmation dog");
    }
    /*
    public void eat()  {

    }
    */
}

//abstract class
abstract class Abstract {
    abstract void sayAbstract();
}

class Conrete extends Abstract  {
    @Override
    void sayAbstract() {
        System.out.println("I am a concrete class of an abstract super class");
    }
}





