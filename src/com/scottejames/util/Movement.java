package com.scottejames.util;

public class Movement {

    private Direction direction;
    private int distance;

    public Movement(Pair<Direction,Integer> input) {
        direction = input.getLeft();
        distance = input.getRight();

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString(){
        return direction + " : " + distance;
    }
}
