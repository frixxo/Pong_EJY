package model;
import GameManagment.IWorldInfo;

public class Ball implements IGameObject{

    Rigbody rigidbody = new Rigbody(30, 30, 8, new Vector(1, 0));
    FPSLimiter update = new FPSLimiter();

    IWorldInfo worldInfo;
    Vector position;

    public Ball(Vector position, IWorldInfo worldInfo){
        this.worldInfo = worldInfo;
        this.position = position;
    }

    public double getX(){
        return this.position.x;
    }
    public double getY(){
        return this.position.y;
    }
    public Vector GetPosition() { return position; }
    public Vector GetMovmentVector() {return rigidbody.getVelocity();}

    public void Update()
    { //Updates every frame
        if (update.isFPS(60)){
            move();
        }
    }

    public void move ()
    {
        this.position.x += rigidbody.deltaSpeedX();
        this.position.y += rigidbody.deltaSpeedY();
    }
}
