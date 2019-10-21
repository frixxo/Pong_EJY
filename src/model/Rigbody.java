package model;

public abstract class Rigbody {
    // structure parent class for paddle and ball

    protected final int width;
    protected final int height;
    protected double speed;
    protected Vector position;

    Rigbody (int width, int height, double speed, Vector position){
        this.position = position;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    // basic getters and setters
    public int GetWidth() { return width; }
    public int GetHeight() { return height; }
    public double GetSpeed() { return speed; }
    public Vector GetPosition() { return new Vector(position.x, position.y); }
    public Vector GetSize() { return new Vector(width, height); }
    public void SetSpeed(double speed) { this.speed = speed; }
    public void SetPosition(Vector pos) {  position.x=pos.x;position.y=pos.y; }



}
