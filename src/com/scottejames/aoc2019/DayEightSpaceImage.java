package com.scottejames.aoc2019;

import com.scottejames.util.FileHelper;

public class DayEightSpaceImage {

    public static void main(String[] args) {
        int wide = 25;
        int tall = 6;

        FileHelper fh = new FileHelper("2019/DayEight.txt");
        String picture = fh.getFileAsString();

        String[] layers = new String[picture.length() / wide];

        for (int i = 0; i < ((picture.length() / wide) / tall); i++) {
            layers[i] = picture.substring(i * wide * tall, (i * wide * tall) + wide * tall);

        }
        String[] layersNoZero = new String[100];
        for (int x = 0; x < 100; x++) {
            layersNoZero[x] = layers[x].replace("0", "");
        }
        String leastZeros = layersNoZero[0];
        for (int x = 0; x < 100; x++) {
            if (leastZeros.length() <= layersNoZero[x].length()) {
                leastZeros = layersNoZero[x];
            }
        }
        // System.out.println("\n" + leastZeros);
        int oneCount = 0;
        int twoCount = 0;
        for (int x = 0; x < leastZeros.length(); x++) {
            if (leastZeros.substring(x, x + 1).equals("1")) {
                oneCount++;
            } else if (leastZeros.substring(x, x + 1).equals("2")) {
                twoCount++;
            }
        }
        System.out.println("(Part A): 1s*2s: " + oneCount * twoCount);
        String partB = picture;
        System.out.println("(Part B):");
        for (int i = 0; i < 100; i++) {
            for (int a = 0; a < 150; a++) {
                if (!(layers[i].substring(a, a + 1).equals("2")) && partB.substring(a, a + 1).equals("2")) {
                    partB = partB.substring(0, a) + layers[i].substring(a,a+1) + partB.substring(a+1, partB.length());

                }
            }
        }
        for (int j = 0; j < tall; j++) {
            System.out.println(partB.substring(j * wide, j * wide + wide).replace("1", "X").replace("0", " "));
        }


    }
}
