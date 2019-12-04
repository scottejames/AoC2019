package com.scottejames.aoc2019;

import com.scottejames.util.*;
import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DayThreeCrossedWires {

    public static final String TEST_WIRE_ONE = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
    public static final String TEST_WIRE_TWO = "U62,R66,U55,R34,D71,R55,D58,R83";

    public static final String TEST_WIRE_THREE = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
    public static final String TEST_WIRE_FOUR = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";

    public static final String TEST_WIRE_ONE_SIMPLE = "R8,U5,L5,D3";
    public static final String TEST_WIRE_TWO_SIMPLE = "U7,R6,D4,L4";

    public static void main(String[] args) {

        FileHelper fh = new FileHelper("2019/DayThree.txt");
        List<String> data = fh.getFileAsList();

        ArrayList<Movement> wireOne = new ArrayList<>();
        ArrayList<Movement> wireTwo = new ArrayList<>();

        for (String each : data.get(0).split(",")) {
            wireOne.add(new Movement(parseMovement(each)));

        }
        for (String each : data.get(1).split(",")) {
            wireTwo.add(new Movement(parseMovement(each)));
        }

        Grid<Integer> grid = new Grid();
        Coord position = new Coord(0,0);
        int length = 1;
        grid.setPoint(position,length);


        for (Movement move: wireOne){
            for (int count = 0; count < move.getDistance(); count++){
                position = position.move(move.getDirection());
                grid.setPoint(position,length);
                length++;
            }
        }
        position = new Coord(0,0);

        int smallestDistance = Integer.MAX_VALUE;
        for (Movement move: wireTwo){
            for (int count = 0; count < move.getDistance(); count++){
                position = position.move(move.getDirection());
                if (grid.getPoint(position) != null){
                    int currentDistance = position.manhattenDistance();
                    if (currentDistance < smallestDistance)
                        smallestDistance = currentDistance;
                }
            }
        }

        System.out.println("Part one shortest distance : " + smallestDistance);
        position = new Coord(0,0);
        int wireTwoSteps = 0;

        int smallestSteps = Integer.MAX_VALUE;
        for (Movement move: wireTwo){
            for (int count = 0; count < move.getDistance(); count++){
                position = position.move(move.getDirection());
                wireTwoSteps ++;
                if (grid.getPoint(position) != null){
                    int currentSteps = grid.getPoint(position) + wireTwoSteps;

                    if (currentSteps < smallestSteps)
                        smallestSteps = currentSteps;
                }
            }
        }

        System.out.println("Part two least steps : " + smallestSteps);
    }

    public static Pair<Direction, Integer> parseMovement(String input) {
        Direction direction = Direction.getDirectionFromChar2(input.charAt(0));
        Integer distance  = Integer.parseInt(input.substring(1,input.length()));
        return new Pair<>(direction,distance);
    }

}


