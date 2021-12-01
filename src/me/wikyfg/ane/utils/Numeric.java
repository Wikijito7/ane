package me.wikyfg.ane.utils;

import java.util.List;

public class Numeric {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);

        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static double sum(List<Double> list) {
        double sum = 0;

        for (double i : list) sum = sum + i;

        return sum;
    }

}
