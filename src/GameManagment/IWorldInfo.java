package GameManagment;

import Controlls.InputSystem;
import model.Ball;
import model.IGameObject;

import java.util.List;

public interface IWorldInfo {
    IGameObject[] GetAllGameObjects();
    Ball GetBall();
    InputSystem[] GetPlayers();
    //TODO add methods
}
