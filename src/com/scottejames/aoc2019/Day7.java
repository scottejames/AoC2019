package com.scottejames.aoc2019;

import com.scottejames.util.FileHelper;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

   public static void main(String[] args) throws Exception {
        Amplifier amp = new Amplifier("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5");
        amp.addInput(5);
        amp.addInput(0);
        amp.runProgram();
        List<Integer> op = amp.getAvailableOutputs();
        amp.addInput(57);
        amp.runProgram();
        System.out.println("Block me");
        op = amp.getAvailableOutputs();
        System.out.println(op);

   }


/*    public static void main(String[] args) throws Exception {
        String instructionSet = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
        boolean feedbackMode = true; //true for part 2. false for part 1;

        List<Integer> phases;
        if(feedbackMode) {
            phases = new ArrayList<>(Arrays.asList(5,6,7,8,9));
        }
        else {
            phases = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        }

        List<List<Integer>> listOfPossiblePhases = createAllPermutationsOf(phases);
        List<Integer> thrustOutputsPossible = new ArrayList<>();

        //for (List<Integer> phaseSet : listOfPossiblePhases) {
        { List<Integer> phaseSet = listOfPossiblePhases.get(0);
        System.out.println("Phase Settings : " + phaseSet);

        List<Amplifier> amplifiers = createAmplifiersForPhaseSet(phaseSet, instructionSet);

            int amplfiersRan = 0;// this is only important for non feedback mode to exit after all amps ran once
            int amplifierToRun = 0;
            do {
                Amplifier amp = amplifiers.get(amplifierToRun);
                amp.runUntilBlockedOrExited();

                amplifierToRun = (amplifierToRun + 1) % amplifiers.size();
                List<Integer> op = amp.getAvailableOutputs();
                System.out.println("Amp " + amplifierToRun + " result " + op.size() + " - " +  op);
                amplifiers.get(amplifierToRun).addInputs(op);
                amplfiersRan++;

            } while (
                    shouldRunNextAmplifier(amplifiers.get(amplifierToRun).inputs.size(),
                            amplifiers.get(amplifierToRun).lastExitReason,
                            feedbackMode,
                            amplfiersRan,
                            phaseSet.size()
                    )
                    );

            thrustOutputsPossible.addAll(amplifiers.get(amplifiers.size() -1 ).totalOutputs);
        }

        System.out.println(thrustOutputsPossible);
        System.out.println(thrustOutputsPossible.stream().reduce(Integer::max).get());
    }
*/



    public static <E> List<List<E>> createAllPermutationsOf(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = createAllPermutationsOf(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
    private static List<Amplifier> createAmplifiersForPhaseSet(List<Integer> phaseSet, String instructionSet) {
        List<Amplifier> amplifiers = new ArrayList<>();
        for (int i = 0; i < phaseSet.size(); i++) {

            Amplifier amplifier = new Amplifier(instructionSet);
            amplifier.addInput(phaseSet.get(i));
            amplifiers.add(amplifier);
            if (i == 0) {
                amplifier.addInput(0);
            }
        }
        return amplifiers;
    }

    private static boolean shouldRunNextAmplifier(
            int numNextAmpInputs,
            ExitReason nextAmpsExitReason,
            boolean feedbackMode,
            int numOfAmpsRan,
            int numberOfAmpsAvailable) {

        boolean exitIfNextAmpIsStuckOrExited = numNextAmpInputs == 0 || nextAmpsExitReason == ExitReason.EXITED;
        boolean exitIfFeedbackOffAndAllAmpsRan = !feedbackMode && numOfAmpsRan >= numberOfAmpsAvailable;

        return !(exitIfFeedbackOffAndAllAmpsRan || exitIfNextAmpIsStuckOrExited);

    }

    private enum ExitReason {
        NEED_INPUT,
        EXITED
    }

    private static class Amplifier {
        private List<Integer> programMem;
        private int programCount;

        private Queue<Integer> inputs = new LinkedList<>();
        private Queue<Integer> outputs = new LinkedList<>();
        private List<Integer> totalOutputs;

        private ExitReason lastExitReason;

        public Amplifier(String instructionSet) {
            programMem = FileHelper.splitStringToInt(instructionSet,",");

            totalOutputs = new ArrayList<>();

        }


        public void addInput(int input) {
            inputs.add(input);
        }
        public void addInputs(List<Integer> toInputs) {
            for (Integer integer : toInputs) {
                inputs.add(integer);
            }
        }

        public List<Integer> getAvailableOutputs() {
            List<Integer> outputsAvailable = new ArrayList<>();

            while (!outputs.isEmpty()) {
                outputsAvailable.add(outputs.remove());
            }

            return outputsAvailable;
        }

        public void runUntilBlockedOrExited() {
            lastExitReason = runProgram();
        }

        private ExitReason runProgram() {

            while (true) {
                int instruction = programMem.get(programCount++);

                int operationCode = instruction % 100;

                int modes = instruction / 100;
                int[] parameterModes = new int[3];
                int modesCount = 0;
                while (modes > 0) {
                    parameterModes[modesCount++] = modes % 10;
                    modes = modes / 10;
                }

                if(operationCode == 99) {
                    return ExitReason.EXITED;
                }
                else if (isThreeParameterOpCode(operationCode)) {
                    int firstParameter = parameterModes[0] == 1 ? programMem.get(programCount++) : programMem.get(programMem.get(programCount++));
                    int secondParameter = parameterModes[1] == 1 ? programMem.get(programCount++) : programMem.get(programMem.get(programCount++));
                    int finalPosition = programMem.get(programCount++);

                    int valueToSetToFinalPosition;
                    if (operationCode == 1) {
                        System.out.println("ADD " + firstParameter + " to " + secondParameter);
                        valueToSetToFinalPosition = firstParameter + secondParameter;
                    }
                    else if (operationCode == 2) {
                        System.out.println("MULTIPLY " + firstParameter + " to " + secondParameter);
                        valueToSetToFinalPosition =  firstParameter * secondParameter;
                    }
                    else if(operationCode == 7) {
                        System.out.println(firstParameter + " < " + secondParameter);
                        valueToSetToFinalPosition = firstParameter < secondParameter ? 1 : 0;
                    }
                    else if (operationCode == 8) {
                        System.out.println(firstParameter + " == " + secondParameter);

                        valueToSetToFinalPosition = firstParameter == secondParameter ? 1 : 0;
                    }
                    else {
                        throw new RuntimeException("unexpected 3 param opCode...");
                    }

                    programMem.set(finalPosition, valueToSetToFinalPosition);
                }
                else if (operationCode == 3 || operationCode == 4) {
                    int parameter1 = programMem.get(programCount++);

                    if (operationCode == 3) {
                        if (inputs.size() == 0) {
                            programCount -= 2; //try this again if this gets ran again!
                            System.out.println("BLOCKED READ");
                            return ExitReason.NEED_INPUT;
                        }
                        else {
                            int read = inputs.remove();
                            System.out.println("READ " + read);
                            programMem.set(parameter1, read);
                        }
                    }
                    else if (operationCode == 4) {
                        int output = (parameterModes[0] == 1 ? parameter1 : programMem.get(parameter1));
                        System.out.println("OUTPUT " + output);
                        outputs.add(output);
                        totalOutputs.add(output);
                    }
                }
                else if (operationCode == 5 || operationCode == 6) {
                    int parameter1 = parameterModes[0] == 1 ? programMem.get(programCount++) : programMem.get(programMem.get(programCount++));
                    int parameter2 = parameterModes[1] == 1 ? programMem.get(programCount++) : programMem.get(programMem.get(programCount++));

                    if (operationCode == 5) {
                        if (parameter1 != 0) {
                            System.out.println("JUMP FALSE");
                            programCount = parameter2;
                        }
                    }
                    else if (operationCode == 6){
                        if (parameter1 == 0) {
                            System.out.println("JUMP TRUE");
                            programCount = parameter2;
                        }
                    }
                }
                else {
                    throw new RuntimeException("unexpected Opcode");
                }
            }
        }

        private static boolean isThreeParameterOpCode(int opCode) {
            return opCode == 1 || opCode == 2 || opCode == 7 || opCode == 8;
        }
    }
}