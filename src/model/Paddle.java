package model;

import Controlls.IControll;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;

public class Paddle implements IGameObject, IControllable {

    private Rigbody paddle;

    // define the start position
    private Vector position;
    private double direction = 0;

    public Paddle(Vector position, IWorldInfo worldInfo, IControll controll){
        this.worldInfo = worldInfo;
        this.controll = controll;
        this.position = position;

        this.paddle  = new Rigbody(5, 50, 5);
    }

    // TODO this is not used here
    private IControll controll; // reference to controll
    private IWorldInfo worldInfo; //reference to world

    public Vector GetSize(){return new Vector(paddle.getWidth(),paddle.getHeight());}
    public double getSpeed(){ return paddle.getSpeed(); }
    public void setVelocity(int speed){ paddle.setSpeed(speed);}
    public Vector GetPosition() { return position; }
    public void SetPosition(Vector pos) {  position.x=pos.x; position.y=pos.y; }

    public void Update() {
        move(direction);
    }

    public void DoAction() //Is called when an input is formed.
    {
        direction= controll.GetDirection();
    }

    public void move(double dir) {
        if (dir > 0 && position.y <= worldInfo.GetWorldSize().y) {
            dir = 1;
        } else if (dir < 0 && position.y - paddle.getHeight() >= 0) {
            dir = -1;
        } else{
            dir = 0;
        }
        this.position.y += dir * paddle.getSpeed();
    }
}
