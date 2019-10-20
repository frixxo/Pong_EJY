package model;

import Controlls.IControll;
import GameManagment.IObserver;
import GameManagment.IWorldInfo;

import javax.naming.ldap.Control;

public class Paddle extends Rigbody implements IGameObject, IControllable {
    // define the start position
    private double direction = 0;

    public Paddle(Vector position, IWorldInfo worldInfo, IControll controll){
        super(10,50,5, position);
        this.worldInfo = worldInfo;
        this.controll = controll;
    }

    // TODO this is not used here
    private IControll controll; // reference to controll
    private IWorldInfo worldInfo; //reference to world

    public void Update() {
        move(direction);
    }

    public void DoAction() { // Is called when an input is formed.
        direction = controll.GetDirection();
    }

    public void move(double dir) {
        if (dir > 0 && position.y <= worldInfo.GetWorldSize().y) {
            dir = 1;
        } else if (dir < 0 && position.y - height >= 0) {
            dir = -1;
        } else{
            dir = 0;
        }
        this.position.y += dir * speed;
    }

    public void SetControll(IControll controll)
    {
        this.controll = controll;
    }

    public IControll GetControll()
    {
        return controll;
    }
}
