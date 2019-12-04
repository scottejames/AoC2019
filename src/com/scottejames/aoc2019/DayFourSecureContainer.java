package com.scottejames.aoc2019;

import com.scottejames.util.FileHelper;
import com.scottejames.util.Util;

public class DayFourSecureContainer {

    private static int lBound = 152085;
    private static int uBound = 670283;

    public static void main (String [] args) {


        int countPartOne = 0;
        int countPartTwo = 0;

        for (int i = lBound; i < uBound; i++) {
            if (validateNumberPartOne(i)) {
                countPartOne++;
            }
            if (validateNumberPartTwo(i)) {
                countPartTwo++;
            }
        }
        System.out.println("Part 1 number : " + countPartOne);
        System.out.println("Part 2 number : " + countPartTwo);


    }

    public static boolean validateNumberPartOne (int i){

        char lastNumber = ' ';
        int[] groupCount = new int[10];

        boolean adjacent = false;
        // Redundant given bounds but
        if (String.valueOf(i).length() != 6){
            return false;
        }

        if ((i < lBound) || (i > uBound)) {
            System.err.println("Out of bounds");
            return false;
        }

        for (char digit : String.valueOf(i).toCharArray()){
            if (digit < lastNumber) {
                return false;
            }
            if (digit == lastNumber) {
                adjacent = true;
            }

            lastNumber = digit;
        }


        if (adjacent == true)
            return true;
        else
            return false;

    }

    public static boolean validateNumberPartTwo (int i){

        char lastNumber = ' ';
        int[] groupCount = new int[10];

        boolean adjacent = false;
        // Redundant given bounds but
        if (String.valueOf(i).length() != 6){
            return false;
        }

        if ((i < lBound) || (i > uBound)) {
            System.err.println("Out of bounds");
            return false;
        }

        for (char digit : String.valueOf(i).toCharArray()){
            if (digit < lastNumber) {
                return false;
            }
            if (digit == lastNumber) {
                groupCount[Character.getNumericValue(digit)]++;
            }

            lastNumber = digit;
        }
        for( int count : groupCount){
            if (count == 1)
                adjacent =true;
        }

        if (adjacent == true)
            return true;
        else
            return false;

    }

}
