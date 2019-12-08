package com.scottejames.intcode;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestInitCode {

    @Test
    public void testOpeartionParsing(){
        ArrayList<Integer> program = new ArrayList<>();
        program.add(1002);
        program.add(4);
        program.add(3);
        program.add(4);
        program.add(33);

        int programCounter = 0;
        Operation operation = new Operation();
        operation.parseInput(program,programCounter);

        int p1 = operation.getParamOne();
        int p2 = operation.getParamTwo();

        Instruction i = operation.getInstr();

        assertEquals(i,Instruction.MULTIPLY);
        System.out.println("p1 " + p1 + " p2 " + p2 );
        assertEquals(p1,33);
        assertEquals(p2, 3);
    }
}
