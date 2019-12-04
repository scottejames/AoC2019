package com.scottejames.util;

import java.util.HashMap;



public class Grid<Value> {

    private HashMap<Coord, Value> points = new HashMap();
    private int xBound = 0;
    private int yBound = 0;
    private int xlBound = 0;
    private int ylBound = 0;

    public Grid() {
    }

    public void setPoint(Coord c, Value v){
        points.put(c,v);

        if (c.getX() > xBound)
            xBound = c.getX();
        if (c.getY() > yBound)
            yBound = c.getY();
        if (c.getX() < xlBound)
            xlBound = c.getX();
        if (c.getY() < ylBound)
            ylBound = c.getY();
    }

    public Value getPoint(Coord c){
        Value result = points.get(c);
        return result;
    }

    public int getxBound() {
        return xBound;
    }

    public void setxBound(int xBound) {
        this.xBound = xBound;
    }

    public int getyBound() {
        return yBound;
    }

    public void setyBound(int yBound) {
        this.yBound = yBound;
    }

    public void printGrid(){
        System.out.println("X bounds " + xlBound + " " + xBound);
        System.out.println("Y bounds " + ylBound + " " + yBound);

        for (int x = xlBound; x <= xBound ; x++){
            for (int y = ylBound; y <= yBound ; y++) {
                Coord c = new Coord(x,y);
                Value v = points.get(c);
                if (v==null){
                    System.out.print("-");
                } else {
                    System.out.print(v);
                }
            }
            System.out.println(" ");
        }
    }
}
