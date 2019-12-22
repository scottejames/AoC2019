package com.scottejames.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static int charToInt(char c,boolean ignoreCase){
        if (ignoreCase == true)
            c= Character.toLowerCase(c);
        return c-'a'+1;

    }

    public  static List<List<Integer>> generatePerm(int min, int max){
        List<Integer> permInput = new ArrayList<>();
        for (Integer i = min; i <= max; i++)
            permInput.add(i);

        return generatePerm(permInput);
    }
    public  static List<List<Long>> generatePermLong(Long min, Long max){
        List<Long> permInput = new ArrayList<>();
        for (Long i = min; i <= max; i++)
            permInput.add(i);

        return generatePerm(permInput);
    }

    public static <E> List<List<E>> generatePerm(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
}
