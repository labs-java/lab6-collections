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
import java.util.Arrays;
import java.util.List;

/**
 * C2 = 1
 * C3 = 1
 */

public class Main {

    private static final List<Vegetable> _vegetables = new ArrayList<>();


    static {
        _vegetables.add(new Pepper(280.2, 100.20, PepperType.CHILI, true));
        _vegetables.add(new Cabbage(26.1, 200.44, CabbageType.PEKING, true));
        _vegetables.add(new Tomato(30.1, 200.58, TomatoType.CHERRY, true));
        _vegetables.add(new Cucumber(13.3, 120.23, CucumberType.CORNICHON, 95, false, 10, true));
    }

    public static void main(String[] args) {
        VegetableSet<Vegetable> vegetables = new VegetableSet<>();
        VegetableSet<Vegetable> vegetables2 = new VegetableSet<>(new Pepper(33, 100, PepperType.CHILI, true));
        VegetableSet<Vegetable> vegetables3 = new VegetableSet<>(_vegetables);


    }
}