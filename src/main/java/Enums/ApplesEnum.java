package Enums;

public class ApplesEnum {

    enum Apple {
        AURORA(10), BELMAC(12), CORTLAND(15), EMPIRE(8), GRAVENSTEIN(11);
        private int price;

        // Constructor
        Apple(int price) {
            this.price = price;
        }
        int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }


    public static void main(String args[]) {
        System.out.println("Apple price list:");
        for (Apple apple : Apple.values()) {
            System.out.println(apple + " costs "
                    + apple.getPrice() + " cents."
                    + apple.ordinal() + " cmp:"
            + apple.compareTo(Apple.GRAVENSTEIN));
        }
    }
}
