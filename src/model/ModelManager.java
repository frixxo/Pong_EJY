package model;

import GameManagment.IObserver;
import GameManagment.IWorldInfo;

public class ModelManager implements IObserver {
    // the game class Pong, currently has functions as listed below:
    /*
    *   has a getter for player scores, setter is automatic called by class
    *   one of the observers and controls collision
    *   has the variable roundOver which should be the condition to reset the game
    *   has a reset() function which can be used to reset the game
    */

    private int score[] = {0, 0};
    public boolean roundOver = false; // should use this to reset the round

    private Ball ball;
    private Paddle left, right;

    private FPSLimiter fps = new FPSLimiter();
    private final IWorldInfo worldInfo;

    // back-ups
    private final double ballSpeed;
    private final Vector ballPos;
    private final Vector leftPos;
    private final Vector rightPos;

    public ModelManager(Ball ball, Paddle left, Paddle right, IWorldInfo worldInfo){
        this.ball = ball;
        this.left = left;
        this.right = right;
        this.worldInfo = worldInfo;

        ballSpeed = ball.GetSpeed();
        ballPos = new Vector(ball.GetPosition().x, ball.GetPosition().y);
        leftPos = new Vector(left.GetPosition().x, left.GetPosition().y);
        rightPos = new Vector(right.GetPosition().x, right.GetPosition().y);
    }

    public void Update(){

        if ((ball.GetMovmentVector().x < 0 && Collider.isCollision(ball, left)) ||
                ball.GetMovmentVector().x > 0 && Collider.isCollision(ball, right)) {
            ball.BounceX();
            ball.boost();
        } else { // if no collision with the paddles then possibly is winning
            paddles winner = checkVictory();
            if (winner != paddles.NONE){
                roundOver = true;
                switch (winner) {
                    case LEFT:
                        score[0]++;
                        break;
                    case RIGHT:
                        score[1]++;
                        break;
                }

                reset();
            }
        }

        if (Collider.isCollision(ball, worldInfo.GetWorldSize().y)){
            ball.BounceY();
            ball.boost();
        }
    }

    public int getScore(int playerIndex){
        try{
            return score[playerIndex];
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public void reset() {
        ball.BounceX();
        ball.SetSpeed(ballSpeed);
        ball.SetPosition(ballPos);
        left.SetPosition(leftPos);
        right.SetPosition(rightPos);
    }

    private paddles checkVictory() {
        if (ball.GetPosition().x <= 0){ return paddles.LEFT; }
        else if (ball.GetPosition().x + ball.GetSize().x >= worldInfo.GetWorldSize().x){ return paddles.RIGHT; }
        return paddles.NONE;
    }

    private enum paddles{
        RIGHT, LEFT, NONE
    }
}
