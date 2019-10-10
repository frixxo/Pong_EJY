package GameManagment;

import Controlls.InputSystem;
import model.Ball;
import model.IGameObject;
import model.Vector;

import java.util.List;

public interface IWorldInfo {
    IGameObject[] GetAllGameObjects();
    Ball GetBall();
    InputSystem[] GetPlayers();
    Vector GetWorldSize();
    //TODO add methods
}
