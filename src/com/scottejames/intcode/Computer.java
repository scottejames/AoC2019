package com.scottejames.intcode;

import java.util.ArrayList;
import java.util.Stack;

public class Computer {
    Stack<Integer> inputStream  = new Stack<>();
    Stack<Integer> outputStream = new Stack<>();

    private ArrayList<Integer> program  = new ArrayList<>();
    int programCounter = 0;

    public  Computer(ArrayList<Integer> input){
        for(Integer each : input) {
            this.program.add(each);
        }
    }

    public void addInput(Integer i) {
        inputStream.push(i);
    }

    public Integer getNextOutput(){
        return outputStream.pop();
    }

    public void runProgram() {

        Operation operation = new Operation();
        while (operation.getInstr() != Instruction.HALT){
            operation.parseInput(program,programCounter);
            int offset = operation.getInstr().offset;
            int opLocation = program.get(programCounter + offset -1);
//            System.out.print("PC : " + programCounter + " opCode " + operation.getInstr().opCodeId + " ");
      
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
                    int input = inputStream.pop();
//                    System.out.println("INPUT " + input + " to " + opLocation);

                    program.set(opLocation, input);
                    break;
                case OUTPUT:
//                    System.out.println("OUTPUT " + operation.getParamOne());

                    outputStream.push(operation.getParamOne());
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
                case EQUALS:
//                    System.out.println("EQUALS set " +opLocation + " if " + operation.getParamOne()+  " equals " + operation.getParamTwo());
                    if (operation.getParamTwo().equals(operation.getParamOne())){
                        program.set(opLocation,1);
                    }  else {
                        program.set(opLocation,0);
                    }

                    break;
                case HALT:
                    return;

                default:
                    System.err.println("Found OpCode not expecting: " + operation.getInstr());
                    break;
            }
            programCounter += offset;
        }
    }
}
