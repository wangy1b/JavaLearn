package Threads;

import java.util.*;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class HorseRaceTest {
    private final int NUMBER_OF_HOURSE = 12;
    private final static RaceHorsePhaser manager = new RaceHorsePhaser();

    public static void main(String[] args) {
        Thread raceMonitor = new Thread(new RaceMonitor());
        raceMonitor.setDaemon(true);
        raceMonitor.start();
        new HorseRaceTest().manageRace();
    }
    public void manageRace(){
        ArrayList<Horse> horseArray = new ArrayList<Horse>();
        for (int i = 0; i < NUMBER_OF_HOURSE; i++) {
            horseArray.add(new Horse());
        }
        runRace(horseArray);
    }

    private void runRace(Iterable<Horse> team) {
        log("Assign all horses, then start race");
        //manager.bulkRegister(NUMBER_OF_HOURSE);
        for (final Horse horse : team) {
            final String dev = horse.toString();
            log("assign " + dev + " to the race");
            manager.register();
            new Thread(() -> {
                try {
                    Thread.sleep((new Random()).nextInt(1000));
                } catch (InterruptedException e) {
                }
                log(dev + ", please await all horse to run");
                manager.arriveAndAwaitAdvance();
                horse.run();
                log(dev + ", please await all horse to leave");
                manager.arriveAndAwaitAdvance();
                horse.leave();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        log("All arriveed at starting gate, start race");
        manager.arriveAndDeregister();
    }

    private static void log(String msg){
        System.out.println(msg);
    }

    private static class Horse {
        private final static AtomicInteger idSource = new AtomicInteger();
        private final int id = idSource.incrementAndGet();

        public void run() {
            log(toString() + ": running");
        }

        public void leave() {
            log(toString() + ": leaving");
        }

        @Override
        public String toString() {
            return "horse #" + id;
        }
    }

    private static class RaceMonitor implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Number of horse ready : "
                        + HorseRaceTest.manager.getArrivedParties());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private static class RaceHorsePhaser extends Phaser{

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("All horses are running！");
                    return false;
                case 1:
                    System.out.println("All horses are leaving！");
                    return false;
                default:
                    return true;

            }
        }
    }

}



