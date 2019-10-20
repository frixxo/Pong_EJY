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

    //region Sums
    public Vector VectorSum(Vector other)
    {
        Vector rt = new Vector(this.GetX() + other.GetX(),this.GetY() + other.GetY());
        return rt;
    }

    public Vector VectorSumY(double y)
    {
        Vector rt = new Vector(this.GetX(),this.GetY() + y);
        return rt;
    }

    public Vector VectorSumX(double x)
    {
        Vector rt = new Vector(this.GetX() + x,this.GetY());
        return rt;
    }
    //endregion

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

