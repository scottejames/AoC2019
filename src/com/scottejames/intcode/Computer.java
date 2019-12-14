package com.scottejames.intcode;

import java.util.*;

public class Computer {
    Queue<Integer> inputStream  = new LinkedList<>();
    Queue<Integer> outputStream = new LinkedList<>();

    private List<Integer> program  = new ArrayList<>();
    int programCounter = 0;

    public  Computer(List<Integer> input){
        for(Integer each : input) {
            this.program.add(each);
        }
    }

    public void addInput(Integer i) {
        inputStream.add(i);
    }

    public Integer getNextOutput(){
        if (outputStream.isEmpty()){
            System.err.println("Get output from empty stack!");
            return null;
        }
        return outputStream.remove();
    }

    public Status runProgram() {

        Operation operation = new Operation();
        while (operation.getInstr() != Instruction.HALT){
            operation.parseInput(program,programCounter);
            int offset = operation.getInstr().offset;
            int opLocation = program.get(programCounter + offset -1);
           // System.out.println("PC : " + programCounter + " opCode " + operation.getInstr().opCodeId + " ");

            switch (operation.getInstr()) {
                case ADD:
//                    System.out.println("ADD " + operation.getParamOne() + " to " + operation.getParamTwo() +  " place result in " + opLocation);
                    program.set(opLocation, operation.getParamOne() + operation.getParamTwo());
                    break;
                case MULTIPLY:
//                    System.out.println("MULT " + operation.getParamOne() + " to " + operation.getParamTwo()  + " place result in " + opLocation);

                    program.set(opLocation, operation.getParamOne() * operation.getParamTwo());
                    break;
                case INPUT:
                    if (inputStream.isEmpty()==true) {
                        return Status.AWAITING_INPUT;
                    }
                    else{
                       int input = inputStream.remove();
//                    System.out.println("INPUT " + input + " to " + opLocation);
                        program.set(opLocation, input);

                    }

                    break;
                case OUTPUT:
//                    System.out.println("OUTPUT " + operation.getParamOne());
                    outputStream.add(operation.getParamOne());
                    break;
                case JUMP_IF_TRUE:
//                    System.out.println("JMP if TRUE " + operation.getParamOne() + " != zero to " + operation.getParamTwo());

                    programCounter = operation.getParamOne() != 0? operation.getParamTwo() - offset : programCounter;
                    break;
                case JUMP_IF_FALSE:
//                    System.out.println("JMP if FALSE " + operation.getParamOne() + " == zero to " + operation.getParamTwo());

                    programCounter = operation.getParamOne() == 0? operation.getParamTwo() - offset : programCounter;
                    break;
                case LESS_THAN:
//                    System.out.println("LESS THAN set " +opLocation + " if " + operation.getParamOne()+  " is less than " + operation.getParamTwo());

                    if (operation.getParamOne() < operation.getParamTwo()){
                        program.set(opLocation,1);
                    }  else {
                        program.set(opLocation,0);
                    }
                    break;
                case REBASE:
//                    System.out.println("REBASE set to "+ operation.getParamOne());
                    Operation.setRebase(operation.getParamOne());


                case EQUALS:
//                    System.out.println("EQUALS set " +opLocation + " if " + operation.getParamOne()+  " equals " + operation.getParamTwo());
                    if (operation.getParamTwo().equals(operation.getParamOne())){
                        program.set(opLocation,1);
                    }  else {
                        program.set(opLocation,0);
                    }

                    break;
                case HALT:
                    return Status.EXIT;

                default:
                    System.err.println("Found OpCode not expecting: " + operation.getInstr());
                    break;
            }
            programCounter += offset;
        }

        return Status.UNEXPECETED;
    }
}
