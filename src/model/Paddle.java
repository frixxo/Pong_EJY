package model;

import Controlls.IControll;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;

public class Paddle implements IGameObject, IControllable {

    Rigbody paddle = new Rigbody(200, 50, 5);
    FPSLimiter update = new FPSLimiter();

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

    public Vector GetSize(){return new Vector(paddle.getWidth(),paddle.getHeight());}
    public int getSpeed(){ return paddle.getSpeed(); }
    public void setVelocity(int speed){ paddle.setSpeed(speed);}
    public Vector GetPosition() { return position; }

    public void Update() //Updates every frame
    {
        if (update.isFPS(60)) {
            move(direction);
        }
    }

    public void DoAction() //Is called when an input is formed.
    {
        direction= controll.GetDirection();
    }



    public void move(double dir){
        if (dir < 0){
            dir = -1;
        } else if (dir > 0){
            dir = 1;
        }
        this.position.y += dir * paddle.getSpeed();
    }
}
