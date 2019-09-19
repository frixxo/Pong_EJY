package model;

public class Paddle {
    // controls the paddles that moves up and down
    // temporary value, will see what works in graphics
    public static final int HEIGHT = 200;
    public static final int WIDTH = 50;
    public static final double SPEED = 10;

    // define the start position
    Vector position;
    Paddle(double x, double y){
        this.position = new Vector(x, y);
    }

    // get the paddle's position
    public double getX(){
        return this.position.x;
    }
    public double getY(){
        return this.position.y;
    }

    /*
    *  move the paddles
    *  positive int = move down
    *  negative int = move up
    *  zero = no move
    */
    public void move(double dir){
        // if dir == 0, dir = 0, else if dir > 0, dir = 1, else dir = -1,
        dir = (dir == 0) ? 0 : (dir > 0) ? 1 : -1;
        this.position.y += dir * this.SPEED;
    }
}
