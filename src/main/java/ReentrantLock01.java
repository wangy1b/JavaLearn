import java.util.concurrent.TimeUnit;

public class ReentrantLock01 {

    synchronized void m1() {
        for (int i =0; i< 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() { System.out.println("m2 ..."); }

    public static void main(String[] args) {
        ReentrantLock01 rl = new ReentrantLock01();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }
}
