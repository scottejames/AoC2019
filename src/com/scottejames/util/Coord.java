package com.scottejames.util;

public class Coord {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coord(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public void move (Movement movement){
        for (int i = 0; i <  movement.getDistance(); i++){
            this.x +=  movement.getDirection().dx;
            this.y += movement.getDirection().dy;
        }
    }
    public Coord move(Direction d){
        return new Coord(this.x+d.getDx(), this.y+d.getDy());

    }

    public int manhattenDistance(Coord origin){
        return Math.abs(origin.getX() - x) + Math.abs(origin.getY() - y);
    }
    public int manhattenDistance(){
        return manhattenDistance(new Coord(0,0));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (x != coord.x) return false;
        return y == coord.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
