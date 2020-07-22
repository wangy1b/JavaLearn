package interview;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class T02_WithoutVolatile {
    ArrayList lists = new ArrayList();
    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        T02_WithoutVolatile c = new T02_WithoutVolatile();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+ i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t1").start();

        new Thread(()->{
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();
    }
}
