package Controlls;

        import GameManagment.IObserver;
        import GameManagment.IWorldInfo;
        import com.sun.tools.javac.Main;
        import model.Ball;
        import model.IControllable;
        import model.IGameObject;
        import model.Vector;
        import model.Rigbody;
        import java.lang.Math;

public class AI implements IObserver, IControll {

    private IWorldInfo worldInfo;
    private IControllable puppet;
    private double direction = 0;
    private boolean recentlyCalculatedMovement = false;
    private double moveAt;

    private double homingDistance = 30d;
    private double satisfactionDeadzone = 5d; //How close can the paddle be to the desired position for the ai to be satisfied and stop the movement

    public AI(IWorldInfo worldInfo, IControllable puppet) {
        this.worldInfo = worldInfo;
        this.puppet = puppet;
        satisfactionDeadzone = ((Rigbody)puppet).GetSpeed();
        homingDistance = ((IGameObject)puppet).GetSize().GetY()/2 -1;
        System.out.println("Activated AI");
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

    public void DeleteMe()
    {
        puppet = null;
        worldInfo = null;
        direction = 0;
    }
    //endregion

    //region AI logic
    // Check the balls movement vector.
    // If it points towards us (and we haven't already calculated its movement pattern) then make a calculation of where it will end up and move there.;
    public void Update() {
        Vector ballVelocity = worldInfo.GetBall().GetMovmentVector();
        Vector ballPosition = worldInfo.GetBall().GetPosition();
        Vector ballSize = ((Rigbody)worldInfo.GetBall()).GetSize();
        if ((((IGameObject)puppet).GetPosition().GetX() - worldInfo.GetWorldSize().GetX() /2  <  0)
                == (ballVelocity.GetX() < 0)) //is true if the ball is moving towards the puppet and the movement wasn't recently calculated
        {
            if (!recentlyCalculatedMovement) {
                moveAt = PredictPos(ballPosition, ballVelocity, ballSize, ((IGameObject)puppet).GetPosition(), ((IGameObject)puppet).GetSize())[0];

                direction = (moveAt - ((IGameObject) puppet).GetPosition().GetY()) / Math.abs(moveAt - ((IGameObject) puppet).GetPosition().GetY()); //set the direction to 1 or -1. Depending of where the desired position is in comparison to the puppets position.
                Action();
                //System.out.println("Moving to: " + moveAt + " with direction: " + direction);
                recentlyCalculatedMovement = true; //we don't need to recalculate the exact same thing next frame.
            } else if (Math.abs(ballPosition.GetX() - ((IGameObject)puppet).GetPosition().GetX()) < ballSize.GetX()/2+homingDistance)
            { //when the ball is really close to the paddle a small homing feature is activated to reduce losses from round off error
                moveAt = ((IGameObject) puppet).GetSize().GetY()/2 + ballPosition.GetY();
                direction = (moveAt - ((IGameObject) puppet).GetPosition().GetY()) / Math.abs(moveAt - ((IGameObject) puppet).GetPosition().GetY());
                Action();
                recentlyCalculatedMovement = true;
            }

        } else if (recentlyCalculatedMovement) {
            IGameObject other = (worldInfo.GetAllGameObjects()[1] == (IGameObject) puppet)? worldInfo.GetAllGameObjects()[2] : worldInfo.GetAllGameObjects()[1];
            recentlyCalculatedMovement = false; // if the ball is moving away from us then reset the recentCalculatedMovement value.

            double[] otherDir = PredictPos(ballPosition, ballVelocity, ballSize, other.GetPosition(), other.GetSize()); //A general prediction of where the ball will be after it has bounced on the other player and is on its way back
            double otherPos  = otherDir[0];
            Vector otherVel = new Vector(-otherDir[1],otherDir[2]);
            moveAt = PredictPos(new Vector(other.GetPosition().GetX(), otherPos), otherVel, ballSize, ((IGameObject)puppet).GetPosition(), ((IGameObject)puppet).GetSize())[0];
            direction = (moveAt - ((IGameObject) puppet).GetPosition().GetY()) / Math.abs(moveAt - ((IGameObject) puppet).GetPosition().GetY());
            Action();
        }

        if (Math.abs(((IGameObject) puppet).GetPosition().GetY() - moveAt) < satisfactionDeadzone + 0.1d) //The movement stops if we are in the desired position
        {
            direction = 0;
            Action();
        }
    }

    double[] PredictPos (Vector ballPosition, Vector ballVelocity, Vector ballSize, Vector destination, Vector destinationSize)
    {
        double predictPos = 0;
        while (true) {
            Vector collisionPoint;
            if (ballVelocity.GetY() > 0) //find impact between the balls movement path and the horizontal walls.
                collisionPoint = ImpactBetweenGraph(Vector.zero().VectorSumY(worldInfo.GetWorldSize().GetY() - ballSize.GetY()/2), Vector.horizontal(), ballPosition, ballVelocity);
            else if (ballVelocity.GetY() < 0)
                collisionPoint = ImpactBetweenGraph(Vector.zero().VectorSumY(ballSize.GetY()/2), Vector.horizontal(), ballPosition, ballVelocity);
            else {
                predictPos = destinationSize.GetY()/2 + ballPosition.GetY(); //If the ball moves parallel to the walls then move to its y position.
                break;
            }

            if (collisionPoint.GetX() <= destination.GetX() + destinationSize.GetX()/2 + ballSize.GetX()/2 && destination.GetX() < worldInfo.GetWorldSize().GetX()/2) // is true if the collision point between the ball and the horizontal walls is outside of the world
            { //left paddle
                predictPos = destinationSize.GetY()/2 + ImpactBetweenGraph(destination, Vector.vertical(), ballPosition.VectorSumX(-ballSize.GetX()/2), ballVelocity).GetY(); //Find the impact between the balls path and the  vertical walls and set its y position as the desired position
                break;
            } else if (collisionPoint.GetX() >= destination.GetX() - destinationSize.GetX()/2 - ballSize.GetX()/2 && destination.GetX() > worldInfo.GetWorldSize().GetX()/2)
            { //right paddle
                predictPos = destinationSize.GetY()/2 + ImpactBetweenGraph(destination, Vector.vertical(), ballPosition.VectorSumX(ballSize.GetX()/2), ballVelocity).GetY(); //Find the impact between the balls path and the  vertical walls and set its y position as the desired position
                break;
            }
            else { //If the collision between the balls path and the horizontal walls is inside of the world then recalculate the path from the impact and with the velocity for after the bounce.
                ballPosition = collisionPoint;
                double newX = ballVelocity.GetX();
                double newY = -ballVelocity.GetY();

                ballVelocity = new Vector(newX, newY);
            }
        }
        return new double[]{predictPos, ballVelocity.GetX(), ballVelocity.GetY()};
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

        if ((int)x != (int)(graph2Point.GetX() + t*graph2Vector.GetX()) || (int)y != (int)(graph2Point.GetY() + t*graph2Vector.GetY())) //This should always return true!
            {
            //throw new RuntimeException("Calculations wrong");
            //System.out.println("Calculations wrong x: " + (int)x + " != " + (int)(graph2Point.GetX() + t*graph2Vector.GetX()) + " || y: " + (int)y + " != " + (int)(graph2Point.GetY() + t*graph2Vector.GetY()));
        }

        return new Vector(x, y);
    }
    //endregion
}
