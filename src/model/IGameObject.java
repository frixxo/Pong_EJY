package model;

import GameManagment.IObserver;

public interface IGameObject extends IObserver {
    Vector GetPosition();
    Vector GetSize();
    int getPoints();
}
