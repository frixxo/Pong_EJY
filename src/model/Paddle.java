package model;

import Controlls.IControll;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;

public class Paddle implements IGameObject, IControllable {
    // controls the paddles that moves up and down
    // temporary value, will see what works in graphics
    public static final int HEIGHT = 200;
    public static final int WIDTH = 50;
    public static final double SPEED = 10;

    // define the start position
    Vector position;
    double direction = 0;
    public Paddle(Vector position, IWorldInfo worldInfo, IControll controll){
        this.worldInfo = worldInfo;
        this.controll = controll;
        this.position = position;
    }

    IWorldInfo worldInfo; //reference to world
    IControll controll; // reference to controll

    // get the paddle's position
    public double getX(){
        return this.position.x;
    }
    public double getY(){
        return this.position.y;
    }


    public void Update() //Updates every frame
    {
        move(direction);
    }

    public void DoAction() //Is called when an input is formed.
    {
        direction= controll.GetDirection();
    }
    public Vector GetPosition() // returns position
    {
        return position;
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
        this.position.y += dir * this.SPEED; //TODO sync movement with time, (multiply with deltaTime between iterations of update)
    }
}
