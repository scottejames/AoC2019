package com.scottejames.aoc2019;

import com.scottejames.util.FileHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DaySixOrbits {

    private static HashMap<String, String> orbits = new HashMap<>();

    public static void main(String [] args){
        FileHelper fh = new FileHelper("2019/DaySix.txt");
        List<String> data = fh.getFileAsList();

        for (String each : data) {

            String [] split = each.split("\\)");
            orbits.put(split[1],split[0]);

        }
        partOne();
        partTwo();
    }

    private static void partTwo(){
        LinkedList<String> sanPath = pathToCOM("SAN");
        LinkedList<String> youPath = pathToCOM("YOU");
        String commonAncenstor = null;
        for (String each: sanPath) {
            if (youPath.contains(each)){
                commonAncenstor = each;
                break;
            }
        }
        int distance =  + sanPath.indexOf(commonAncenstor) + youPath.indexOf(commonAncenstor) - 2;
        System.out.println("Part2: " + distance);


    }
    private static void partOne(){
        int count = 0;
        for(String each: orbits.keySet()){
            int oCoubt = getOrbitCounts(each);
//            System.out.println("Checking " + each + " found " + oCoubt);

            count += oCoubt;
        }
        System.out.println("Part 1: " + count);
    }

    private static LinkedList<String> pathToCOM(String start) {
        String body = start;
        LinkedList<String> path = new LinkedList<>();

        while(!body.equals("COM")) {
            path.add(body);
            body = orbits.get(body);
        }
        return path;
    }

    private static int getOrbitCounts(String body) {
        if (body.equals("COM")) {
            return 0;
        }
        return 1 + getOrbitCounts( orbits.get(body));
    }
}
