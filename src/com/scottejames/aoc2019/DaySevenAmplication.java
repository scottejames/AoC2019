package com.scottejames.aoc2019;

import com.scottejames.intcode.Computer;
import com.scottejames.util.FileHelper;
import com.scottejames.intcode.Status;
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
        partOne(program);
        partTwo(program);

    }
    public static void partOne(List<Integer> program){
        String inputs = "";
        List<List<Integer>> phaseSettings =generatePerm(0,4);
        int maxOutput = 0;

        for(List<Integer> each: phaseSettings) {
            List<Amplifier> ampList = new ArrayList<>();

            ampList.add(new Amplifier(program, each.get(0)));
            ampList.add(new Amplifier(program, each.get(1)));
            ampList.add(new Amplifier(program, each.get(2)));
            ampList.add(new Amplifier(program, each.get(3)));
            ampList.add(new Amplifier(program, each.get(4)));
            int result = 0;
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

    public static void partTwo(List<Integer> program){
        List<List<Integer>> phaseSettings =generatePerm(5,9);
        int maxOutput = 0;
        for(List<Integer> each: phaseSettings)
        {
            List<Amplifier> ampList = new ArrayList<>();

            ampList.add(new Amplifier(program,each.get(0)));
            ampList.add(new Amplifier(program,each.get(1)));
            ampList.add(new Amplifier(program,each.get(2)));
            ampList.add(new Amplifier(program,each.get(3)));
            ampList.add(new Amplifier(program,each.get(4)));


            int result = 0;
            boolean stop = false;
            // Probably should check for all stopped
            while (ampList.get(4).isStopped() == false){
                for (int i = 0; i < ampList.size(); i++) {
                        Status s = ampList.get(i).runProgram(result);
                        result = ampList.get(i).getNextOutput();
//                        System.out.println("AMP[" + i + "] Result is " + result + " status is " + s.toString());

                        if (s == Status.EXIT) {
                            ampList.get(i).setStopped(true);
                        }
                }
            }
            if (result > maxOutput){
                maxOutput = result;
            }
        }
       System.out.println("Part 2 " + maxOutput);

    }

}
class Amplifier extends Computer {
    private boolean stopped = false;

    public Amplifier(List<Integer> input, Integer phase) {
        super(input);

        addInput(phase);

    }

    public Status runProgram(int input) {
        addInput(input);
        Status status = super.runProgram();
        return status;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
