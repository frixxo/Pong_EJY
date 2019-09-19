package model;
import java.util.*;

public class Ball{
    // temporary value
    public final static int HEIGHT = 40;
    public final static int WIDTH = 40;
    public final static double SPEED = 5;

    Rigbody bounce;

    // defines the start position of the ball
    Vector position;
    Ball(double x, double y){
        this.position = new Vector(x, y);
    }

    // get the ball's position
    public double getX(){
        return this.position.x;
    }
    public double getY(){
        return this.position.y;
    }

    /*
    *  x_dir and y_dir decides which direction to go, -1, 1 or 0
    *  positive int is the same as 1
    *  negative int is the same as -1
    *
    *  positive y_dir moves down
    *  negative y_dir moves up
    *  positive x_dir moves right
    *  negative y_dir moves left
    *
    *  k is the slope it will move on
    *  k = (y2 - y1) / (x2 - x1) , y2 and x2 are unknown
    *  if we assume at the next frame the ball moves 1 pixel at the x-axis
    *  x2 = x1 + x_dir (it can go at both directions)
    *  y2 = k * (x1 + x_dir - x1) + y1
    *  y2 = k * x_dir + y1
    *
    *  note that y2 is the position, not the distance
    */
    public void move(double x_dir, double y_dir, double k) {
        x_dir = (x_dir == 0) ? 0 : (x_dir > 0) ? 1 : -1;
        y_dir = (y_dir == 0) ? 0 : (y_dir > 0) ? 1 : -1;

        // k can never be 0, 1 gives a horizontal or vertical line
        if (k == 0) k = 1;

        double y_distance;
        // if x_dir is 0, set y_distance as 1
        // y_distance is always positive
        y_distance = Math.abs((x_dir != 0) ? k * x_dir : 1);
        if (y_distance > 1){
            // not allowing the y distance to surpass 1
            double abs_y_dir = Math.abs(y_dir);
            x_dir /= abs_y_dir;
            y_distance /= abs_y_dir;
        }

        this.position.x += this.SPEED * x_dir;
        this.position.y += this.SPEED * y_distance * y_dir;
    }
}
