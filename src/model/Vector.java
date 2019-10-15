package model;

public class Vector{
    // simple class that saves a 2-dimensional position
    double x;
    double y;
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double GetX(){return x;}
    public double GetY(){return y;}

    //region basicVectors
    public Vector zero() {
        return new Vector(0,0);
    }

    public Vector horizontal () {
        return new Vector(1,0);
    }

    public Vector vertical () {
        return new Vector(0, 1);
    }
    //endregion
}

