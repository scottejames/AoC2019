package com.scottejames.intcode;

public enum Instruction {

    ADD (1,4),
    MULTIPLY(2,4),
    INPUT(3,2),
    OUTPUT(4,2),
    JUMP_IF_TRUE(5,3),
    JUMP_IF_FALSE(6,3),
    LESS_THAN(7,4),
    EQUALS(8,4),
    REBASE(9,2),
    HALT(99,0),
    NULL(100,0),
    ERROR(101,0);

    public int opCodeId = 0;
    public int offset = 0;

    Instruction(int id,int offset){
        this.opCodeId = id;
        this.offset = offset;
    }

    public static Instruction of(int id) {
        switch(id) {
            case 1:
                return ADD;
            case 2:
                return MULTIPLY;
            case 3:
                return INPUT;
            case 4:
                return OUTPUT;
            case 5:
                return JUMP_IF_TRUE;
            case 6:
                return JUMP_IF_FALSE;
            case 7:
                return LESS_THAN;
            case 8:
                return EQUALS;
            case 9:
                return REBASE;
            case 99:
                return HALT;
            case 100:
                return NULL;
            default:
                return ERROR;
        }

    }
}
