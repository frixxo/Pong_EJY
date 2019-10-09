package Controlls;

import model.IControllable;
import model.Vector;

public class InputSystem implements IControll {

    private IControllable puppet;
    private double direction = 0;

    public void ChangeDirection(double direction)
    {
        this.direction = direction;
        Action();
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
    //endregion
}
