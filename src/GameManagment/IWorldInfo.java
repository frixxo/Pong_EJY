package GameManagment;

import model.Ball;
import model.IGameObject;

import java.util.List;

public interface IWorldInfo {
    List<IGameObject> GetAllGameObjects();
    Ball GetBall();
    //TODO add methods
}
