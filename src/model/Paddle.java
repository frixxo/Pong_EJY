package model;

import Controlls.IControll;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;

public class Paddle implements IGameObject, IControllable {

    private Rigbody paddle = new Rigbody(200, 50, 5);
    private FPSLimiter update = new FPSLimiter();

    // define the start position
    private Vector position;
    private double direction = 0;

    public Paddle(Vector position, IWorldInfo worldInfo, IControll controll){
        this.worldInfo = worldInfo;
        this.controll = controll;
        this.position = position;
    }

    // TODO this is not used here
    private IControll controll; // reference to controll
    private IWorldInfo worldInfo; //reference to world

    public Vector GetSize(){return new Vector(paddle.getWidth(),paddle.getHeight());}
    public double getSpeed(){ return paddle.getSpeed(); }
    public void setVelocity(int speed){ paddle.setSpeed(speed);}
    public Vector GetPosition() { return position; }

    public void Update() {
        if (update.isFPS(60)) {
            move(direction);
        }
    }

    public void DoAction() //Is called when an input is formed.
    {
        direction= controll.GetDirection();
    }

    public void move(double dir) {
        if (dir < 0 && isPositionOK()) {
            dir = -1;
        } else if (dir > 0 && isPositionOK()) {
            dir = 1;
        }
        this.position.y += dir * paddle.getSpeed();
    }

    private boolean isPositionOK() {
        if (position.y >= worldInfo.GetWorldSize().y || position.y - paddle.getHeight() <= 0) {
            return false;
        }
        return true;
    }
}
