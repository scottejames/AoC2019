package com.scottejames.aoc2019;

import com.scottejames.intcode.IntCodeComputer;
import com.scottejames.util.FileHelper;

import java.util.List;

public class DayNineSensorBoost {

    public static void main(String [] args){
        FileHelper fh = new FileHelper("2019/DayNine.txt");
        String data = fh.getFileAsString();

        List<Long> program = FileHelper.splitStringToLong(data,",");
        IntCodeComputer computer = new IntCodeComputer(program);
        computer.addInput(2L);
        computer.runProgram();
        Long result = computer.getNextOutput();

        System.out.println("Part One : " + result);

    }
}
