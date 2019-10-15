package Controlls;

        import GameManagment.IObserver;
        import GameManagment.IWorldInfo;
        import com.sun.tools.javac.Main;
        import model.Ball;
        import model.IControllable;
        import model.IGameObject;
        import model.Vector;
        import java.lang.Math;

public class AI implements IObserver, IControll {

    private IWorldInfo worldInfo;
    private IControllable puppet;
    private double direction = 0;
    private boolean recentlyCalculatedMovement = false;
    private double moveAt;

    private final double satisfactionDeadzone = 1d; //How close can the paddle be to the desired position for the ai to be satisfied and stop the movement

    public AI(IWorldInfo worldInfo, IControllable puppet) {
        this.worldInfo = worldInfo;
        this.puppet = puppet;
    }

    //region IControll
    public void Set(IControllable puppet) {
        this.puppet = puppet;
    }

    public void Action() {
        puppet.DoAction();
    }

    public double GetDirection() {
        return direction;
    }

    public IControllable GetPuppet(){return puppet;}
    //endregion

    //region AI logic
    // Check the balls movement vector.
    // If it points towards us (and we haven't already calculated its movement pattern) then make a calculation of where it will end up and move there.;
    public void Update() {
        Vector ballVelocity = worldInfo.GetBall().GetMovmentVector();
        Vector ballPoisition = worldInfo.GetBall().GetPosition();
        if ((((IGameObject)puppet).GetPosition().GetX() - worldInfo.GetWorldSize().GetX() /2  <  0)
                == (ballVelocity.GetX() < 0) && !recentlyCalculatedMovement) //is true if the ball is moving towards the puppet and the movement wasn't recently calculated
        {
            while (true){
                Vector collisionPoint;
                if (ballVelocity.GetY() > 0) //find impact between the balls movement path and the horizontal walls.
                    collisionPoint = ImpactBetweenGraph(new Vector(0, worldInfo.GetWorldSize().GetY()), ballVelocity.horizontal(), ballPoisition, ballVelocity);
                else if (ballVelocity.GetY() < 0)
                    collisionPoint = ImpactBetweenGraph(ballVelocity.zero(), ballVelocity.horizontal(), ballPoisition, ballVelocity);
                else {
                    moveAt = ballPoisition.GetY(); //If the ball moves parallel to the walls then move to its y position.
                    break;
                }

                if (collisionPoint.GetX() <= 0 || collisionPoint.GetX() >= worldInfo.GetWorldSize().GetX()) // is true if the collision point between the ball and the horizontal walls is outside of the world
                {
                    moveAt = ImpactBetweenGraph(((IGameObject) puppet).GetPosition(), ballVelocity.vertical(), ballPoisition, ballVelocity).GetY(); //Find the impact between the balls path and the  vertical walls and set its y position as the desired position
                    break;
                }
                else { //If the collision between the balls path and the horizontal walls is inside of the world then recalculate the path from the impact and with the velocity for after the bounce.
                    ballPoisition = collisionPoint;
                    double newX = ballVelocity.GetX();
                    double newY = -ballVelocity.GetY();

                    ballVelocity = new Vector(newX, newY);
                }
            }
            direction = (moveAt - ((IGameObject) puppet).GetPosition().GetY()) / Math.abs(moveAt - ((IGameObject) puppet).GetPosition().GetY()); //set the direction to 1 or -1. Depending of where the desired position is in comparison to the puppets position.
            Action();
            recentlyCalculatedMovement = true; //we don't need to recalculate the exact same thing next frame.
        } else if (recentlyCalculatedMovement) recentlyCalculatedMovement = false; // if the ball is moving away from us then reset the recentCalculatedMovement value.

        if (Math.abs(((IGameObject) puppet).GetPosition().GetY() - moveAt) < satisfactionDeadzone) //The movement stops if we are in the desired position
        {
            direction = 0;
            Action();
        }
    }

    Vector ImpactBetweenGraph(Vector graph1Point, Vector graph1Vector, Vector graph2Point, Vector graph2Vector) //Finds the impact between two graphs
    {
        double a1 = graph1Vector.GetX();
        double b1 = -graph2Vector.GetX();
        double c1 = graph2Point.GetX() - graph1Point.GetX();

        double a2 = graph1Vector.GetY();
        double b2 = -graph2Vector.GetY();
        double c2 = graph2Point.GetY() - graph1Point.GetY();

        double s = (c1*b2-c2*b1)/(a1*b2-a2*b1);
        double t = (a1*c2-a2*c1)/(a1*b2-a2*b1);

        double x = graph1Point.GetX() + s*graph1Vector.GetX();
        double y = graph1Point.GetY() + s*graph1Vector.GetY();

        if (x != graph2Point.GetX() + t*graph2Vector.GetX() || y != graph2Point.GetY() + t*graph2Vector.GetY()) //This should always return true!
        {
            throw new RuntimeException("Calculations wrong");
        }

        return new Vector(x, y);
    }
    //endregion
}
