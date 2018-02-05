package com.company;

/**
 * Created by @kash on 1/8/2018.
 */
public class Car {
    int numWheels;
    Engine engine = new Engine();

    class Engine {
        void accelerate()   {
            System.out.println("Accelerating!");
        }
        void decelerate()   {
            System.out.println("Decelearting!");
        }
    }
    public
    void switchOn() {
        engine.accelerate();
    }

    void switchOff()    {
        engine.decelerate();
    }

    public static void main(String[] args)  {
        Car car = new Car();
        car.switchOn();
        car.switchOff();
    }
}

