package Mechanical_workshop;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by @kash on 1/24/2018.
 */
interface Robot {
    void do_task();
    int time_ticks = -1;
}

class Pick_Place_Bot{
    Robot nextBot;
    Boolean loadOn;
    Queue<Bike> queue;
    Pick_Place_Bot(Robot nextBot, boolean onConveyer)   {
        this.nextBot = nextBot;
        this.loadOn = onConveyer;
        this.queue = new LinkedList<>();
    }
}

class ChainCleaner{
    Robot nextBot;
    Queue<Bike> queue;
    ChainCleaner(Robot nextBot) {
        this.nextBot = nextBot;
        queue = new LinkedList<>();
    }
}

class SmallPartsCleaner{
    Robot nextBot;
    Queue<Bike> queue;
    SmallPartsCleaner(Robot nextBot)    {
        this.nextBot = nextBot;
        this.queue = new LinkedList<>();
    }
}

class BodyWasher{
    Robot nextBot;
    Queue<Bike> queue;
    BodyWasher(Robot nextBot)   {
       this.nextBot = nextBot;
       this.queue = new LinkedList<>();
    }
}

class Shiner{
    Robot nextBot;
    Queue<Bike> queue;
    Shiner(Robot nextBot)   {
        this.queue = new LinkedList<>();
        this.nextBot = nextBot;
    }
}
