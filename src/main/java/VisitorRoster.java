import java.util.Random;

public class VisitorRoster {
    private final int MAX_CAPACITY = 100;
    private String[] visitors;

    public static void main(String[] args) {
        VisitorRoster roster = new VisitorRoster();
        roster.init();
        roster.registerVisitor();
        try {
            roster.printVisitorList();
        } catch (Exception e ) {
//            e.printStackTrace();
            System.out.println("Quitting on end of list");
        }
    }
    private void init() {
        visitors = new String[MAX_CAPACITY];
    }
    private void registerVisitor() {
        Random r = new Random();
        System.out.println("Registering visitors");
        for (int i = 0; i < MAX_CAPACITY; i++) {
            visitors[i] = Long.toString(Math.abs(r.nextLong()), 36);
        }
    }
    private void printVisitorList() {
        System.out.println("\nToday's Visitors:");
        int i = 0;
        while (i <= MAX_CAPACITY) {
            System.out.println(String.format("Visitor ID %s # ", i) + visitors[i++]);
//            i++;
        }
    }
}