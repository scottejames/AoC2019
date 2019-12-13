package com.scottejames.aoc2019;

import com.scottejames.intcode.Computer;
import com.scottejames.util.FileHelper;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

import static com.scottejames.util.Util.generatePerm;

public class DaySevenAmplication {
    private static int min = 0, max = 4;

    private static ArrayList  <Integer> program;
    public static void main(String [] args) {
        FileHelper fh = new FileHelper("2019/DaySeven.txt");
        String data = fh.getFileAsString();
        program = FileHelper.splitStringToInt(data,",");

        String inputs = "";
        List<List<Integer>> permutations =generatePerm(min,max);
        int maxOutput = 0;
        for(List<Integer> each: permutations){
            int result = getResult(each.get(0),each.get(1), each.get(2),each.get(3),each.get(4));
            if (result > maxOutput)
                maxOutput = result;
        }
        System.out.println("MaxOutput " + maxOutput + " ip " + inputs);
    }


    private static int getResult(int a, int b, int c, int d, int e) {
        System.out.print("TESTING " + a + " "+ b + " "+ c + " "+ d + " "+ e);
        int number = 0;
        Amplifier ampOne = new Amplifier(program,a);
        Amplifier ampTwo = new Amplifier(program,b);
        Amplifier ampThree = new Amplifier(program,c);
        Amplifier ampFour = new Amplifier(program,d);
        Amplifier ampFive = new Amplifier(program,e);
        number = ampOne.runProgram(number);
        number = ampTwo.runProgram(number);
        number = ampThree.runProgram(number);
        number = ampFour.runProgram(number);
        number = ampFive.runProgram(number);
        System.out.println(" = " + number);
        return number;

    }

//    private static List<Computer> createAmplifiersForPhaseSet(List<Integer> phaseSet, List<String> program) {
//        List<Computer> amplifiers = new ArrayList<>();
//        for (int i = 0; i < phaseSet.size(); i++) {
//
//            Computer computer = new Computer(program);
//            amplifier.addInput(phaseSet.get(i));
//            amplifiers.add(amplifier);
//            if (i == 0) {
//                amplifier.addInput(0);
//            }
//        }
//        return amplifiers;
//    }
}
class Amplifier extends Computer {

    private int phase = 0;

    public Amplifier(ArrayList<Integer> input, int phase) {
        super(input);

        this.phase = phase;
    }

    public int runProgram(int input) {
        addInput(input);
        addInput(phase);
        super.runProgram();
        int result = super.getNextOutput();
        return result;
    }
}
