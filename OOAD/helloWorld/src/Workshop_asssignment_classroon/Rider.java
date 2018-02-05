package Workshop_asssignment_classroon;

import java.util.ArrayList;

/**
 * Created by @kash on 1/27/2018.
 */
public class Rider {
    public static void main(String[] args)  {
        Bike bike = new Bike();
        Bikepart engine = new Engine();
        Bikepart fueltank = new FuelTank();

        bike.addBikePart(engine);
        bike.addBikePart(fueltank);

        PartsChecker partsChecker = new PartsChecker();
        PartsOperator partsOperator = new PartsOperator();

        bike.accept(partsChecker);
        bike.accept(partsOperator);

        bike.visitParts(partsChecker);
        bike.visitParts(partsOperator);
    }
}

interface Visitor {
    void visit(FuelTank fuelTank);
    void visit(Engine engine);
    void visit(Bike bike);
}

class FuelTank extends Bikepart{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Engine extends Bikepart  {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Bike implements Visitable{
    ArrayList<Bikepart> bikeparts;

    Bike()  {
        bikeparts = new ArrayList<>();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    void addBikePart(Bikepart b)    {
        bikeparts.add(b);
    }

    void visitParts(Visitor visitor)   {
        for(Bikepart bp: bikeparts) {
            bp.accept(visitor);
        }
    }
}

abstract class Bikepart implements Visitable{}

interface Visitable {
    void accept(Visitor visitor);
}

class PartsChecker implements Visitor{
    @Override
    public void visit(Bike bike) {
        System.out.println("Checking parts: bike");
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Checking parts: Engine");
    }

    @Override
    public void visit(FuelTank fuelTank) {
        System.out.println("Checking parts: fueltank");
    }
}

class PartsOperator implements Visitor  {
    @Override
    public void visit(Bike bike) {
        System.out.println("Operating on parts: bike");
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Operating on parts: engine");
    }

    @Override
    public void visit(FuelTank fuelTank) {
        System.out.println("Operating on parts: fueltank");
    }
}

