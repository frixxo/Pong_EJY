package Controlls;

import model.IControllable;
import model.Vector;

public interface IControll {
    void Set(IControllable puppet);
    void Action ();
    double GetDirection();
}
