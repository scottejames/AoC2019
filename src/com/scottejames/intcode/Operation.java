package com.scottejames.intcode;

import com.scottejames.util.Pair;

import java.util.ArrayList;

public class Operation {

    private Instruction instr;
    private Integer paramOne;
    private Integer paramTwo;

    public Operation() {
        instr = Instruction.NULL;
    }

    public void parseInput(ArrayList<Integer> program, int programCounter) {

        Integer instructionOp = program.get(programCounter);

        instr = Instruction.of(instructionOp % 100);

        if (instr== Instruction.ERROR){
            System.err.println("ERROR in op code at "+ programCounter + " reading " + instructionOp);
            System.exit(-1);
        }
        Mode mode = Mode.of((instructionOp / 100) % 2);
        paramOne = getParameter(program,programCounter+1,mode);
        mode = Mode.of((instructionOp / 1000) % 2);
        paramTwo = getParameter(program, programCounter+2, mode);
    }

    public int getParameter(ArrayList<Integer> program, int parameter, Mode mode) {
        if (parameter >= program.size()) {
            return 0;
        }
        if (mode == Mode.IMMEDIATE) {
            return program.get(parameter);
        } else {
            if ((program.get(parameter) < 0) || (program.get(parameter) > program.size())){
                return 0;
            }else{
                return program.get(program.get(parameter));
            }
        }
    }

    public Instruction getInstr() {
        return instr;
    }
    public Integer getParamOne() {
        return paramOne;
    }
    public Integer getParamTwo() {
        return paramTwo;
    }
}
