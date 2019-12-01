package com.scottejames.aoc2019;


import com.scottejames.util.FileHelper;

import java.util.List;

public class DayOneMass {

    public static void main(String [] args){
        FileHelper fh = new FileHelper("2019/DayOne.txt");
        List<String> data = fh.getFileAsList();
        partOne(data);
        partTwo(data);
    }

    private static void partOne(List<String> data) {
        int totalFuel = 0;
        for (String line : data){
            int moduleSize = Integer.parseInt(line);
            int moduleFuel = calculateMass(moduleSize);
            totalFuel += moduleFuel;
        }
        System.out.println("DayOnePartOne: Total mass is " + totalFuel);
    }
    public static void partTwo(List<String> data){

        int totalFuel = 0;
        for (String line : data) {
            int moduleSize = Integer.parseInt(line);
            int moduelFuel = calculateMass(moduleSize);
            int fuelFuel = calculateMass(moduelFuel);
            while (fuelFuel > 0) {
                moduelFuel += fuelFuel;
                fuelFuel = calculateMass(fuelFuel);
            }
            totalFuel += moduelFuel;

        }
        System.out.println("DayOnePartTwo: Total mass is " + totalFuel);

    }

    public static int calculateMass(int mass){
        return (mass / 3) - 2;
    }
}
