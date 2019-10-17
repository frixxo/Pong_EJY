package model;
import GameManagment.IWorldInfo;

public class Ball implements IGameObject{

    private int size = 30;
    private Rigbody ball;
    private FPSLimiter update;

    private IWorldInfo worldInfo;
    private Vector position;

    public Ball(Vector position, IWorldInfo worldInfo){
        this.worldInfo = worldInfo;
        this.position = position;
        this.ball = new Rigbody(size, size, 4, new Vector(1,0));
        this.update = new FPSLimiter();
    }

    public Vector GetSize(){ return new Vector(ball.getWidth(),ball.getHeight()); }
    public Vector GetPosition() { return position; }
    public Vector GetMovmentVector() { return ball.getVelocity(); }

    public void Update() { //Updates every frame
        move();

    }

    public void move () {
        this.position.x += ball.deltaSpeedX();
        this.position.y += ball.deltaSpeedY();
    }

    public void boost() { ball.setSpeed(ball.getSpeed() * 1.05); }
    public void BounceY() { this.position.y *= -1; }
    public void BounceX() { this.position.x *= -1; }
}
