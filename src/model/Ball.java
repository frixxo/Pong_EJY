package model;
import GameManagment.IWorldInfo;

public class Ball implements IGameObject{

    private int size = 40;
    private Rigbody ball;

    private IWorldInfo worldInfo;
    private Vector position;

    public Ball(Vector position, IWorldInfo worldInfo){
        this.worldInfo = worldInfo;
        this.position = position;
        this.ball = new Rigbody(size, size, 2, new Vector(1,0));

        // starts on a random slope between [-0.2, 0.2]
        ball.setVelocityY(Math.random() / 2.5 - 0.2);
    }

    public Vector GetSize(){ return new Vector(ball.getWidth(),ball.getHeight()); }
    public Vector GetPosition() { return position; }
    public Vector GetMovmentVector() { return ball.getVelocity(); }
    public double GetSpeed(){ return ball.getSpeed(); }
    public void SetPosition(Vector pos) { position.x=pos.x; position.y=pos.y; }
    public void SetSpeed(double spd) { ball.setSpeed(spd); }

    public void Update() { //Updates every frame
        move();
    }

    public void move () {
        this.position.x += ball.deltaSpeedX();
        this.position.y += ball.deltaSpeedY();
    }

    public void boost() {
        // variation index between [-0.1, 0.1]
        ball.setVelocityY(ball.getVelocity().y + Math.random() / 5 - 0.1);
        ball.setSpeed(ball.getSpeed() * 1.05);
    }

    public void BounceY() { ball.setVelocityY(ball.getVelocity().y * -1); }
    public void BounceX() { ball.setVelocityX(ball.getVelocity().x * -1); }
}
