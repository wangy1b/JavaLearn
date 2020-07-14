package Generics;

import java.util.*;

public class PlanetMap {
    public static void main(String[] args) {
        HashMap<Integer, String> mapOfPlanets = new HashMap<>();
        mapOfPlanets.put(1,"Mercury");
        mapOfPlanets.put(2,"Venus");
        mapOfPlanets.put(3,"Earth");
        mapOfPlanets.put(4,"Mars");
        mapOfPlanets.put(5,"Jupiter");
        mapOfPlanets.put(6,"Saturn");
        mapOfPlanets.put(7,"Uranus");
        mapOfPlanets.put(8,"Neptune");
        System.out.println("Enter the desired position: ");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.printf("Solar system position %d is taken by %s%n",i, mapOfPlanets.get(i));

    }
}
