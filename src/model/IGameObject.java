package model;

import GameManagment.IObserver;

public interface IGameObject extends IObserver {
    Vector GetPosition();
    double getX();
    double getY();
    double getWidth();
    double getHeight();

}
