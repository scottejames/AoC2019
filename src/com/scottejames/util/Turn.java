package com.scottejames.util;

public enum Turn {
    LEFT, STRAIGHT, RIGHT;


    public Turn nextTurn() {
        switch (this) {
            case LEFT: return STRAIGHT;
            case STRAIGHT: return RIGHT;
            case RIGHT: return LEFT;
        }
        throw new RuntimeException();
    }

}