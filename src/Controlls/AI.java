package Controlls;

import GameManagment.IObserver;
import GameManagment.IWorldInfo;
import model.IControllable;

public class AI implements IObserver, IControll {

    private IWorldInfo worldInfo;
    private IControllable puppet;
    private double direction = 0;

    public AI(IWorldInfo worldInfo, IControllable puppet) {
        this.worldInfo = worldInfo;
        this.puppet = puppet;
    }

    //region IControll
    public void Set(IControllable puppet) {
        this.puppet = puppet;
    }

    public void Action() {
        puppet.DoAction();
    }

    public double GetDirection() {
        return direction;
    }

    public IControllable GetPuppet(){return puppet;}
    //endregion


    //TODO Plan: check the balls movement vector.
    // If it points towards us then make a calculation of where it will end up and move there.
    // Take bouncing into walls into the calculation;
    public void Update() {
    }
}
