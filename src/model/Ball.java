package model;
import GameManagment.IWorldInfo;

public class Ball extends Rigbody implements IGameObject{
    // TODO this is not used here
    private IWorldInfo worldInfo;

    private Vector velocity;

    public Ball(Vector position, IWorldInfo worldInfo){
        super(40, 40, 3, position);
        this.worldInfo = worldInfo;
        velocity = new Vector(1,0);
        velocity.y = GetVariation(0.2);
    }

    public void Update() { //Updates every frame
        move();
    }

    public void move () {
       position.x += deltaSpeedX();
       position.y += deltaSpeedY();
    }

    public void boost() {
        velocity.y += GetVariation(0.2);
        speed *= 1.05;
    }

    public Vector GetVelocity() { return new Vector(velocity.x, velocity.y); }
    public Vector GetMovmentVector() { return new Vector(velocity.x, velocity.y); }
    public void BounceY() { velocity.y *= -1; }
    public void BounceX() { velocity.x *= -1; }
    public void SetVelocityX(double x) { velocity.x = x; }
    public void SetVelocityY(double y) { velocity.y = y; }

    private double deltaSpeedX() { return speed * velocity.x; }
    private double deltaSpeedY() { return speed * velocity.y; }
    private double GetVariation(double var) {
        // chooses a random number from interval [-var, var]
        double divisor = 1 / (var * 2);
        return Math.random() / divisor - var;
    }

}
