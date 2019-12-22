package com.scottejames.intcode;

import java.util.*;
import static com.scottejames.intcode.IntCodeComputer.Status.*;

public class IntCodeComputer {

    public enum Status {IDLE,RUNNING, WAITING, HALTED}

    private final Map<Integer, Long> memory;

    Queue<Long> in  = new LinkedList<>();
    Queue<Long> out = new LinkedList<>();

    private final int[] pAddrs = new int[3];

    private int programCounter = 0;
    private Long offset = 0L;
    private Status status = IDLE;

    public IntCodeComputer(List<Long> code) {
        memory = new HashMap<>(code.size());
        for (int i = 0; i < code.size(); i++) {
            memory.put(i, code.get(i));
        }
    }

    public Status runProgram() {
        status = RUNNING;

        while (status == RUNNING ) {

            String cmd = String.format("%05d", load(programCounter));
            int opCode = Integer.parseInt(cmd.substring(3));
            pAddrs[0] = getParam(cmd, 1); // Param 1
            pAddrs[1] = getParam(cmd, 2); // Param 2
            pAddrs[2] = getParam(cmd, 3); // Param 3

            switch (opCode){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 99:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid opcode " + opCode + " aborting");
            }
            if (opCode == /*INPUT*/ 3) {
                if (in.isEmpty() == true) {
                    System.out.println("BLOCKED READ");
                    status = WAITING;
                } else {

                    Long input = in.remove();
                    System.out.println("READ " + input);
                    store(pAddrs[0], input);
                    programCounter += 2;

                }

            } else if (opCode == /*OUTPUT*/ 4) {
                System.out.println("OUTPUT " + load(pAddrs[0]));
                out.add(load(pAddrs[0]));
                programCounter += 2;
            } else if (opCode == /*ADJUST_RBO*/ 9) {
                System.out.println("REBASE " + load(pAddrs[0]));
                offset += load(pAddrs[0]);
                programCounter += 2;
            } else if (opCode == /*JMP_IF_TRUE*/ 5) {
                System.out.println("JUMP TRUE");
                programCounter = load(pAddrs[0]) > 0 ? load(pAddrs[1]).intValue() : programCounter + 3;
            } else if (opCode == /*JMP_IF_FALSE*/ 6) {
                System.out.println("JUMP FALSE");
                programCounter = load(pAddrs[0]) == 0 ? load(pAddrs[1]).intValue() : programCounter + 3;
            } else if (opCode == /*ADD*/ 1) {
                System.out.println("ADD " + load(pAddrs[0]) + " to " + load(pAddrs[1]));

                store(pAddrs[2], load(pAddrs[0]) + load(pAddrs[1]));
                programCounter += 4;
            } else if (opCode == /*MULT*/ 2) {
                System.out.println("MULT" + load(pAddrs[0]) + " to " + load(pAddrs[1]));

                store(pAddrs[2], load(pAddrs[0]) * load(pAddrs[1]));
                programCounter += 4;
            } else if (opCode == /*LESS_THAN*/ 7) {
                System.out.println("LESS THAN" + load(pAddrs[0]) + " to " + load(pAddrs[1]));

                store(pAddrs[2], load(pAddrs[0]) < load(pAddrs[1]) ? 1L : 0L);
                programCounter += 4;
            } else if (opCode == /*EQUALS*/ 8) {
                System.out.println("EQUALS" + load(pAddrs[0]) + " to " + load(pAddrs[1]));

                store(pAddrs[2], load(pAddrs[0]).equals(load(pAddrs[1])) ? 1L : 0L);
                programCounter += 4;
            } else if (opCode == /* TERMINATE */ 99) {
                System.out.println("TERMINATE");

                status = HALTED;
            }
        }


        return status;
    }
    private int getParam(String cmd, int i) {
        switch (cmd.charAt(3 - i)) {
            case /*POSITION*/ '0':
                return load(programCounter + i).intValue();
            case /*IMMEDIATE*/ '1':
                return programCounter + i;
            case /* RELATIVE */ '2':
                return Long.valueOf(load(programCounter + i) + offset).intValue();
            default:
                throw new RuntimeException("Unsupported opmode");
        }
    }
    private Long load(int loc) {
        return memory.computeIfAbsent(loc, (x) -> 0L);
    }

    private void store(int loc, Long val) {
        memory.put(loc, val);
    }

    public void addInput(Long ip){
        System.out.println("addInput (" + ip + ")");
        in.add(ip);
    }
    public Long getNextOutput(){
        if (out.isEmpty()){
            System.err.println("Get output from empty stack!");
            return null;
        }
        Long res = out.remove();
        System.out.println("getNextOutput(" + res + ")");
        return res;

    }
    public Status getStatus() {
        return status;
    }
}
