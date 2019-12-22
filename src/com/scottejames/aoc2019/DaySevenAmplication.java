package com.scottejames.aoc2019;

import com.scottejames.intcode.IntCodeComputer;
import com.scottejames.util.FileHelper;
import java.util.ArrayList;
import java.util.List;

import static com.scottejames.util.Util.generatePermLong;

public class DaySevenAmplication {
    private static int min = 0, max = 4;

    private static ArrayList  <Long> program;
    public static void main(String [] args) {
        FileHelper fh = new FileHelper("2019/DaySeven-test2.txt");
        String data = fh.getFileAsString();
        program = FileHelper.splitStringToLong(data,",");
       // partOne(program);
        partTwo(program);
//        testCase(program);

    }
    public static void partOne(List<Long> program){
        List<List<Long>> phaseSettings =generatePermLong(0L,4L);
        Long maxOutput = 0L;

        for(List<Long> each: phaseSettings) {
            List<Amplifier> ampList = new ArrayList<>();

            ampList.add(new Amplifier(program, each.get(0)));
            ampList.add(new Amplifier(program, each.get(1)));
            ampList.add(new Amplifier(program, each.get(2)));
            ampList.add(new Amplifier(program, each.get(3)));
            ampList.add(new Amplifier(program, each.get(4)));
            Long result = 0L;
            for (Amplifier amp : ampList) {
                amp.runProgram(result);
                result = amp.getNextOutput();
            }
            if (result > maxOutput) {
                maxOutput = result;
            }
        }
        System.out.println("Part 1: " + maxOutput);

    }

    public static void partTwo(List<Long> program){
        List<List<Long>> phaseSettings =generatePermLong(5L,9L);
        Long maxOutput = 0L;
        //for(List<Long> each: phaseSettings)
        List<Long> each = phaseSettings.get(0);
        {
            System.out.println("Phase Settings : " + each);
            List<Amplifier> ampList = new ArrayList<>();

            ampList.add(new Amplifier(program, each.get(0)));
            ampList.add(new Amplifier(program, each.get(1)));
            ampList.add(new Amplifier(program, each.get(2)));
            ampList.add(new Amplifier(program, each.get(3)));
            ampList.add(new Amplifier(program, each.get(4)));

            Long result = 0L;
            // Probably should check for all stopped

            while (ampList.get(4).isHalted() == false) {

                for (int i = 0; i < ampList.size(); i++) {
                    IntCodeComputer.Status s = ampList.get(i).runProgram(result);

                    result = ampList.get(i).getNextOutput();
                    System.out.println("Amp " + i + " result " + result);

                }
            }
            System.out.println("Result = " + result);
            if (result > maxOutput) {
                maxOutput = result;
            }
        }

       System.out.println("Part 2 " + maxOutput);

    }

    public static void testCase(List<Long> p){
        Amplifier amp = new Amplifier(p, 5L);
        amp.runProgram(0L);
        amp.getNextOutput();
        amp.runProgram(57L);
        Long result = amp.getNextOutput();
        System.out.println(result);
        amp.runProgram(1881L);
        result = amp.getNextOutput();
        System.out.println(result);
    }

}
class Amplifier extends IntCodeComputer {

    public Amplifier(List<Long> input, Long phase) {
        super(input);
        addInput(phase);

    }

    public Status runProgram(Long input) {
        addInput(input);
        return super.runProgram();
    }

    public boolean isHalted() {

        if (getStatus() == Status.HALTED) {
            return true;
        } else {
            return false;
        }
    }

}
