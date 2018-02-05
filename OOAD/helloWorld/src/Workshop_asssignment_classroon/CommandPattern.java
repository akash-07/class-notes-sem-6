package Workshop_asssignment_classroon;

import java.util.ArrayList;

/**
 * Created by @kash on 1/27/2018.
 */
public class CommandPattern {
    public static void main(String[] args)  {
        Customer customer1 = new Customer("oil");
        Customer customer2 = new Customer("both");
        Manager manager = new Manager();
        ArrayList<Command> requests1 = customer1.requestRepair();
        ArrayList<Command> requests2 = customer2.requestRepair();
        System.out.println("Adding requests of Customer 1");
        for(Command c: requests1)  {
            manager.addRequest(c);
        }
        System.out.println("Processing requests of Customer 2");
        for(Command c: requests2)   {
            manager.processRequest(c);
        }
        System.out.println("Processing all added requests");
        manager.processAll();
    }
}

class Customer {
    String request;
    ArrayList<Command> requests;
    Customer(String request)    {
        this.request = request;
        requests = new ArrayList<>();
    }

    ArrayList<Command> requestRepair()  {
        if(request.equals("oil"))   {
            requests.add(new ChangeOilCommand(new Mechanic()));
        }
        else if(request.equals("electrical"))   {
            requests.add(new CheckElectricalsCommand(new Mechanic()));
        }
        else if(request.equals("both")) {
            Mechanic mechanic = new Mechanic();
            requests.add(new ChangeOilCommand(mechanic));
            requests.add(new CheckElectricalsCommand(mechanic));
        }
        else    {
            System.out.println("ERROR: wrong commnad");
        }
        return requests;
    }
}

class Manager {
    ArrayList<Command> commands;
    ArrayList<Command> toProcess;
    Manager()   {
        commands = new ArrayList<>();
        toProcess = new ArrayList<>();
    }
    void addRequest(Command command)  {
        toProcess.add(command);
    }
    void processRequest(Command command)   {
        commands.add(command);
        command.execute();
    }
    void processAll()   {
        for(Command command: toProcess) {
            command.execute();
            commands.add(command);
        }
        toProcess = new ArrayList<>();
    }
}
class Mechanic  {
    void changeOil()    {
        System.out.println("Mechanic: Changing oil");
    }

    void checkElectricals() {
        System.out.println("Mechanic: checking electricals");
    }
}

interface Command {
    void execute();
}

class ChangeOilCommand implements Command{
    Mechanic mechanic;
    ChangeOilCommand(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
    @Override
    public void execute() {
        mechanic.changeOil();
    }
}

class CheckElectricalsCommand implements Command{
    Mechanic mechanic;
    CheckElectricalsCommand(Mechanic mechanic)  {
        this.mechanic = mechanic;
    }
    @Override
    public void execute() {
        mechanic.checkElectricals();
    }
}

