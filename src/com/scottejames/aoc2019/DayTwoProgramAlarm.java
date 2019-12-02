package com.scottejames.aoc2019;

import com.scottejames.util.FileHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class DayTwoProgramAlarm {

    public static void main(String [] args){
        String testData = "1,1,1,4,99,5,6,0,99";

        FileHelper fh = new FileHelper("2019/DayTwo.txt");
        String data = fh.getFileAsString();

        ArrayList<Integer> array = FileHelper.splitStringToInt(data,",");
        Integer[] registers = array.toArray(new Integer[array.size()]);
        System.out.println(Arrays.toString(registers));

//        // Fudge data
//        registers[1] = 12;
//        registers[2] = 2;
//        runProgram(registers);
//        System.out.println(Arrays.toString(registers));

        for (int l = 0; l < 100; l++){
            for (int r = 0; r < 100; r++){
                Integer newRegisters[] = Arrays.copyOf(registers,registers.length);

                newRegisters[1] = l;
                newRegisters[2] = r;
                runProgram(newRegisters);
                if (newRegisters[0] == 19690720) {
                    System.out.println(Arrays.toString(newRegisters));
                    int result = 100 * l + r;
                    System.out.println("Day Two Result : " + result);
                }

            }
        }

    }

    public static void runProgram(Integer[] program){
        int programCounter = 0;
        int opCode = program[programCounter];
        while (opCode != 99){
            int left = program[program[programCounter+1]];
            int right = program[program[programCounter+2]];
            int destination = program[programCounter+3];
//            System.out.println("Left " + left + " Right " + " Dest " + destination);
            if (opCode == 1){
//                System.out.println("Adding " + left + " and " + right);
                int result = left + right;
                program[destination] = result;
            }

            if (opCode == 2){
//                System.out.println("Mult " + left + " and " + right);

                int result = left * right;
                program[destination] = result;
            }
            programCounter += 4;
            opCode = program[programCounter];

        }

    }
}
