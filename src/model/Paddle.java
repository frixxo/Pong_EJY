package model;

public class Paddle {
    // controls the paddles that moves up and down
    // temporary value, will see what works in graphics
    public static final int HEIGHT = 200;
    public static final int WIDTH = 50;
    public static final int SPEED = 10;

    // define the start position
    Vector position;
    Paddle(int x, int y){
        this.position = new Vector(x, y);
    }


    // get the paddles position
    public int getX(){
        return position.x;
    }
    public int getY(){
        return position.y;
    }

    // move the paddles, not allowed to move anywhere along the x-axel
    public void setY(int y){
        this.position.y = y;
    }

}
