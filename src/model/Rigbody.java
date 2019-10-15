package model;

public class Rigbody {
    private final int width;
    private final int height;
    private int speed;
    Vector velocity;

    Rigbody (int width, int height, int speed){
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.velocity = null;
    }

    Rigbody (int width, int height, int speed, Vector velocity){
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.velocity = velocity;
    }


    // basic getters and setters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getSpeed() { return speed; }
    public Vector getVelocity() { return velocity; }
    public double deltaSpeedX(){ return speed * velocity.x; }
    public double deltaSpeedY(){ return speed * velocity.y; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setVelocityX(double x){ velocity.x = x; }
    public void setVelocityY(double y){ velocity.y = speed; }


    /*
    Vector velocity;
    Rigbody(Vector velocity)
    {
        this.velocity = velocity;
    }

    void Bounce (Vector axis)
    {
        this.velocity.x = axis.x*-1;
        this.velocity.y = axis.y*-1;
    }
    */

}
