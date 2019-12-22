package com.scottejames.intcode;


import java.util.List;

public class Operation {
    private static Integer rebase = 0;
    private Instruction instr;
    private Integer paramOne;
    private Integer paramTwo;

    public Operation() {
        instr = Instruction.NULL;
    }

    public void parseInput(List<Integer> program, int programCounter) {

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

    public int getParameter(List<Integer> program, int parameter, Mode mode) {
        if (parameter >= program.size()) {
            return 0;
        }

        int offset = getOffSet(parameter,mode);

        if (mode == Mode.IMMEDIATE) {
            return program.get(parameter );
        } else {
            if ((program.get(parameter) < 0) || (program.get(parameter) > program.size())) {
                return 0;
            } else {
                return program.get(program.get(offset));
            }
        }
    }

    public int getOffSet(int parameter, Mode mode){

        if (mode == Mode.IMMEDIATE) {
            return 0;
        } else if (mode == Mode.POSITION) {
            return parameter;
        } else if (mode == Mode.RELATIVE ) {
            return parameter + rebase;
        } else {
            throw new IllegalArgumentException("Invalid Mode while calculating offset");
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

    public static void setRebase(Integer rebase) {
        Operation.rebase += rebase;
    }
}
