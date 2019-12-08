package com.scottejames.aoc2019;

import com.scottejames.intcode.Computer;
import com.scottejames.util.FileHelper;

import java.util.ArrayList;

public class DayFiveSunny {

    public static void main(String [] args) {
        FileHelper fh = new FileHelper("2019/DayFive.txt");
        String data = fh.getFileAsString();
        ArrayList<Integer> program = FileHelper.splitStringToInt(data,",");


        Computer c = new Computer(program);
        c.addInput(1);
        c.runProgram();

        System.out.println("Part 1: " + c.getNextOutput());

        c = new Computer(program);
        c.addInput(5);
        c.runProgram();

        System.out.println("Part 2: " + c.getNextOutput());
    }


}
