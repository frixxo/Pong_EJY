package model;
import GameManagment.IWorldInfo;

public class Ball extends Rigbody implements IGameObject{

    private IWorldInfo worldInfo;
    private Vector velocity;

    public Ball(Vector position, IWorldInfo worldInfo){
        super(20, 20, 3, position);
        this.worldInfo = worldInfo;
        velocity = new Vector(3,0);
        SetTwist();
    }

    public void Update() { //Updates every frame
        move();
    }

    public void move () {
       position.x += velocity.x;
       position.y += velocity.y;
    }

    public void boost() {
        speed *= 1.05;
        SetTwist();
    }

    public Vector GetMovmentVector() { return new Vector(velocity.x, velocity.y); }
    public void BounceY() { velocity.y *= -1; }
    public void BounceX() { velocity.x *= -1; }
    public void SetVelocityX(double x) { velocity.x = x; }
    public void SetVelocityY(double y) { velocity.y = y; }
    @Override
    public void SetSpeed(double speed){
        this.speed = speed;
        velocity.x = velocity.x / Math.abs(velocity.x) * speed;
        velocity.y = 0;
        SetTwist();

        System.out.println("New velocity: "+velocity.x+", "+velocity.y);
    }

    private double GetVariation(double var) {
        // chooses a random number from interval [-var, var]
        double divisor = 1 / (var * 2);
        return Math.random() / divisor - var;
    }

    private void SetTwist(){
        double variation = GetVariation(1);
        velocity.y += variation;
        while (Math.pow(velocity.y, 2) >= Math.pow(speed, 2)){
            velocity.y -= variation;
            variation = GetVariation(1);
            velocity.y += variation;
        }
        velocity.x = (velocity.x / Math.abs(velocity.x)) * Math.sqrt(Math.pow(speed, 2)-Math.pow(velocity.y, 2));
    }
}
