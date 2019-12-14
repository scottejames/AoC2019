package com.scottejames.aoc2019;

import com.scottejames.util.Coord;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * <x=4, y=12, z=13>
 * <x=-9, y=14, z=-3>
 * <x=-7, y=-1, z=2>
 * <x=-11, y=17, z=-1>
 */

public class DayTwelveNBody {
    private static List<Coordinate> xAxis;
    private static List<Coordinate> yAxis;
    private static List<Coordinate> zAxis;

    public static void main(String [] args) {
        //int [] xArr = {-1, 2, 4, 3};
        //int [] yArr = {0, -10, -8, 5};
        //int [] zArr = {2, -7, 8, -1};

        int [] xArr = {4,-9,-7,-11};
        int [] yArr = {12,14,-1,17};
        int [] zArr = {13,-3,2,-1};

        xAxis = build(xArr);
        yAxis = build(yArr);
        zAxis = build(zArr);

        partOne();

    }
    public static void partOne(){
        for (int i = 1; i <= 1000; i++) {

           // printWorld();
            moveOneStep(xAxis);
            moveOneStep(yAxis);
            moveOneStep(zAxis);
        }
        System.out.println("partOne: " + calculateEnergy());
        System.out.println("PartTwo: " + cycleCount());

    }

    private static void printWorld() {
        for (int i = 0; i < xAxis.size(); i++) {
            printCoord(xAxis.get(i), yAxis.get(i), zAxis.get(i));
        }
    }
    private static void printCoord(Coordinate x, Coordinate y, Coordinate z){
        System.out.println("pos = < " + x.getPosition() + ", " + y.getPosition() + ", " + z.getPosition() + "> " +
                    "vel = <" + x.getVelocity() + ", " + y.getVelocity() + ", " + z.getVelocity() + ">");
    }
    private static void moveOneStep(List<Coordinate> axis) {
        for (Coordinate l : axis) {
            for (Coordinate r : axis) {
                if (l!=r) {
                    l.recalculateVelocity(r);
                }
            }
        }
        for (Coordinate l : axis){
            l.applyVelocity();
        }

    }
    private static long cycleCount(){
        int xCycle = calculateStepsForCycle(xAxis);
        int yCycle = calculateStepsForCycle(yAxis);
        int zCycle = calculateStepsForCycle(zAxis);
        System.out.println(" X " + xCycle + " Y " + yCycle + " Z " + zCycle);
        long cycle = lcm(lcm(xCycle,yCycle),zCycle);
        return cycle;
    }
    private static int calculateStepsForCycle(List<Coordinate> axis) {

        List<Coordinate> origin = new ArrayList<>();
        for (Coordinate each: axis){
            origin.add(new Coordinate(each));
        }

        int c = 0;
        while(true) {
            moveOneStep(axis);
            c++;
            if(isSame(axis, origin)) {
                return c;
            }
        }
    }
    private static long lcm(long a, long b) {
        long lcm = 0;
        while((lcm+=Math.max(a, b)) % Math.min(a, b) != 0);
        return lcm;
    }



    private static boolean isSame(List<Coordinate> axis, List<Coordinate> otherAxis) {
        for(int i = 0; i < axis.size(); i++) {
            if(!axis.get(i).isSame(otherAxis.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static int calculateEnergy() {
        int energy = 0;

        for(int i = 0; i < xAxis.size(); i++) {
            energy += calculateEnergy(xAxis.get(i), yAxis.get(i), zAxis.get(i));
        }
        return energy;
    }

    private static int calculateEnergy(Coordinate x, Coordinate y, Coordinate z) {
        return (Math.abs(x.getPosition()) +   Math.abs(y.getPosition()) +
                Math.abs(z.getPosition())) * (Math.abs(x.getVelocity()) +
                Math.abs(y.getVelocity()) + Math.abs(z.getVelocity()));
    }


    public static List<Coordinate> build(int [] ip){
        ArrayList<Coordinate> result = new ArrayList<>();
        for (int each: ip) {
            result.add(new Coordinate(each));
        }
        return result;
    }

}
class Coordinate {
    private int position;
    private int velocity = 0;

    Coordinate(int position) {
        this.position = position;
    }

    Coordinate(Coordinate coordinate) {
        this.position = coordinate.position;
        this.velocity = coordinate.velocity;
    }


    void applyVelocity() {
        this.position += velocity;
    }

    void recalculateVelocity(Coordinate other) {
        if (other.position > position) {
            velocity++;
        } else if (other.position < position) {
            velocity--;
        }
    }

    boolean isSame(Coordinate coordinate) {
        return position == coordinate.position && velocity == coordinate.velocity;
    }

    int getPosition() {
        return position;
    }

    int getVelocity() {
        return velocity;
    }

}