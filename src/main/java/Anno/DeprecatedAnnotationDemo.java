package Anno;

public class DeprecatedAnnotationDemo {

    @SuppressWarnings({"deprecation"})
    public static void main(String[] args) {
        MyTestClass testObject = new MyTestClass();
        testObject.doSomething();
        testObject.doSomethingNew("Bowling");
    }
}

class MyTestClass {
    @Deprecated
    public void doSomething() {
        System.out.println("Nothing");
    }
    public void doSomethingNew(String SomeFun) {
        System.out.println(SomeFun);
    }
}
