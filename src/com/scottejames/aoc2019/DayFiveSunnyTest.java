package com.scottejames.aoc2019;

import com.scottejames.intcode.Computer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class DayFiveSunnyTest {

    @Test
    public void jumpTest(){
        ArrayList<Integer> jumpTest =  new ArrayList();
        Integer []arr =
                {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                        1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                        999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        Collections.addAll(jumpTest,arr);

        Computer c = new Computer(jumpTest);
        c.addInput(7);
        c.runProgram();
        int result = c.getNextOutput();
        assertEquals(999,result);

        c = new Computer(jumpTest);
        c.addInput(8);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(1000,result);

        c = new Computer(jumpTest);
        c.addInput(9);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(1001,result);
    }

    @Test
    public void equalsEight() {
        ArrayList<Integer> compEight = new ArrayList();
        Integer[] arr =
                {3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};
        Collections.addAll(compEight, arr);
        Computer c = new Computer(compEight);

        c.addInput(8);
        c.runProgram();
        int result = c.getNextOutput();
        assertEquals(1,result);

        c = new Computer(compEight);

        c.addInput(7);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(0,result);

        c = new Computer(compEight);

        c.addInput(99);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(0,result);
    }

    @Test
    public void lessThan() {
        ArrayList<Integer> compEight = new ArrayList();
        Integer[] arr =
                {3,9,7,9,10,9,4,9,99,-1,8};
        Collections.addAll(compEight, arr);
        Computer c = new Computer(compEight);

        c.addInput(8);
        c.runProgram();
        int result = c.getNextOutput();
        assertEquals(0,result);

        c = new Computer(compEight);

        c.addInput(7);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(1,result);

        c = new Computer(compEight);

        c.addInput(99);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(0,result);
    }
    @Test
    public void equalsEight2() {
        ArrayList<Integer> compEight = new ArrayList();
        Integer[] arr =
                {3,3,1108,-1,8,3,4,3,99};
        Collections.addAll(compEight, arr);
        Computer c = new Computer(compEight);

        c.addInput(8);
        c.runProgram();
        int result = c.getNextOutput();
        assertEquals(1,result);

        c = new Computer(compEight);

        c.addInput(7);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(0,result);

        c = new Computer(compEight);

        c.addInput(99);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(0,result);
    }

    @Test
    public void lessThan2() {
        ArrayList<Integer> compEight = new ArrayList();
        Integer[] arr =
                {3,3,1107,-1,8,3,4,3,99};
        Collections.addAll(compEight, arr);
        Computer c = new Computer(compEight);

        c.addInput(8);
        c.runProgram();
        int result = c.getNextOutput();
        assertEquals(0,result);

        c = new Computer(compEight);

        c.addInput(7);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(1,result);

        c = new Computer(compEight);

        c.addInput(99);
        c.runProgram();
        result = c.getNextOutput();
        assertEquals(0,result);
    }
}
