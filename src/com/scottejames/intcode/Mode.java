package com.scottejames.intcode;

enum Mode {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2),
    NULL(99),
    ERROR(100);

    public int modeID;

    Mode(int id){
        modeID = id;
    }

    public static Mode of(int id){
        switch(id) {
            case 0:
                return POSITION;
            case 1:
                return IMMEDIATE;
            case 2:
                return RELATIVE;

            case 99:
                return NULL;
            default:
                return ERROR;
        }
    }
}
