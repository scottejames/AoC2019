package com.scottejames.util;

public class TestGrid {


    public static void main(String [] args){

        Grid grid = new Grid();

        System.out.println("Print Empty Grid");
        grid.printGrid();

        System.out.println("Print sparce Grid with one point");
        grid.setPoint(new Coord(1,3),'X');
        grid.printGrid();

        System.out.println("Print sparce Grid with expanded with two point");

        grid.setPoint(new Coord(5,5),'Y');
        grid.printGrid();



    }
}
