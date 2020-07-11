package DesignPatterns.singleton;

public class Mgr05 {


    private static Mgr05 INSTANCE;
    private Mgr05(){};

    public static Mgr05 getInstance(){
        if(INSTANCE == null) {
            //试图通过减小同步代码的方式来提高效率，然而不可行
            synchronized(Mgr05.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
            }

        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                System.out.println(Mgr05.getInstance().hashCode());
            }).start();
        }
    }
}
