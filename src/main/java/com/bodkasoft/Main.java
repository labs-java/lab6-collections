package com.bodkasoft;

import com.bodkasoft.collection.VegetableSet;
import com.bodkasoft.ingredient.Vegetable;
import com.bodkasoft.ingredient.vegetable.Cabbage;
import com.bodkasoft.ingredient.vegetable.Cucumber;
import com.bodkasoft.ingredient.vegetable.Pepper;
import com.bodkasoft.ingredient.vegetable.Tomato;
import com.bodkasoft.ingredient.vegetable.vegetabletypes.CabbageType;
import com.bodkasoft.ingredient.vegetable.vegetabletypes.CucumberType;
import com.bodkasoft.ingredient.vegetable.vegetabletypes.PepperType;
import com.bodkasoft.ingredient.vegetable.vegetabletypes.TomatoType;

import java.util.ArrayList;
import java.util.List;

/**
 * C2 = 1
 * C3 = 1
 * The Main class demonstrates the usage of the VegetableSet collection,
 * a custom implementation of a set using a singly linked list.
 * It showcases adding, removing, and checking the presence of vegetables
 * in the set. The static block initializes a sample collection of vegetables
 * to work with.
 * <p>
 * The class also includes utility methods to print the contents of the set
 * and check for the existence of a specific vegetable.
 */
public class Main {

    /**
     * A static list of predefined vegetables used for testing.
     */
    private static final List<Vegetable> _vegetables = new ArrayList<>();

    // Static block to initialize the list of vegetables
    static {
        _vegetables.add(new Pepper(280.2, 100.20, PepperType.CHILI, true));
        _vegetables.add(new Cabbage(26.1, 200.44, CabbageType.PEKING, true));
        _vegetables.add(new Tomato(30.1, 200.58, TomatoType.CHERRY, true));
        _vegetables.add(new Cucumber(13.3, 120.23, CucumberType.CORNICHON, 95, false, 10, true));
    }

    /**
     * The main method, which serves as the entry point for the application.
     * It demonstrates the following operations on the VegetableSet:
     * <ul>
     *   <li>Adding vegetables</li>
     *   <li>Checking if a vegetable exists in the set</li>
     *   <li>Removing vegetables from the set</li>
     *   <li>Clearing all vegetables from the set</li>
     * </ul>
     */
    public static void main(String[] args) {
        VegetableSet<Vegetable> vegetables = new VegetableSet<>();
        VegetableSet<Vegetable> vegetables2 = new VegetableSet<>(new Pepper(33, 100, PepperType.CHILI, true));
        VegetableSet<Vegetable> vegetables3 = new VegetableSet<>(_vegetables);

        vegetables.add(new Pepper(312.55, 300.12, PepperType.BULGARIAN, true));
        vegetables.add(new Cabbage(312.55, 300.12, CabbageType.GREEN, true));
        vegetables.add(new Tomato(312.55, 300.12, TomatoType.CHERRY, true));

        System.out.println("<---Added vegetables in set--->");
        printVegetables(vegetables);
        System.out.println();

        System.out.println("<---Check for containing--->");
        isExist(vegetables, new Cabbage(312.55, 300.12, CabbageType.GREEN, true));
        System.out.println();

        System.out.println("<---Removing Cabbage--->");
        vegetables.remove(new Cabbage(312.55, 300.12, CabbageType.GREEN, true));

        isExist(vegetables, new Cabbage(312.55, 300.12, CabbageType.GREEN, true));

        System.out.println();
        printVegetables(vegetables);
        System.out.println();

        System.out.println("<---Clearing vegetables--->");
        vegetables.clear();
        printVegetables(vegetables);
    }

    /**
     * Prints all vegetables in the given VegetableSet. If the set is empty,
     * it prints "No vegetables found".
     *
     * @param <T> the type of vegetables (extending Vegetable) in the set
     * @param set the VegetableSet to print
     */
    public static <T extends Vegetable> void printVegetables(VegetableSet<T> set) {
        Object[] arrayOfVegetables = set.toArray();
        if (arrayOfVegetables.length == 0) {
            System.out.println("No vegetables found");
        }
        for(Object vegetable : arrayOfVegetables) {
            System.out.println(vegetable);
        }
    }

    /**
     * Checks if the given vegetable exists in the provided VegetableSet.
     * Prints a message indicating whether the vegetable is present in the set.
     *
     * @param <T> the type of vegetable (extending Vegetable)
     * @param set the VegetableSet to check
     * @param vegetable the vegetable to search for in the set
     */
    public static <T extends Vegetable> void isExist(VegetableSet<T> set, T vegetable) {
        if (set.contains(vegetable)){
            System.out.println("Vegetables contains Cabbage");
        }else{
            System.out.println("Vegetables does not contain Cabbage");
        }
    }
}