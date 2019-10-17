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
    private final Ball backUpBall;
    private final Paddle backUpLeft;
    private final Paddle backUpRight;

    public ModelManager(Ball ball, Paddle left, Paddle right, IWorldInfo worldInfo){
        this.ball = ball;
        this.left = left;
        this.right = right;
        this.worldInfo = worldInfo;
        backUpBall = ball;
        backUpLeft = left;
        backUpRight = right;
    }

    public void Update(){
        if (fps.isFPS(60)){
            if (Collider.isCollision(ball, left) || Collider.isCollision(ball, right)){
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
                }
            }

            if (Collider.isCollision(ball, worldInfo.GetWorldSize().y)){
                ball.BounceY();
                ball.boost();
            }
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
        ball = backUpBall;
        left = backUpLeft;
        right = backUpRight;
    }

    private paddles checkVictory() {
        if (ball.GetPosition().x <= 0){ return paddles.LEFT; }
        else if (ball.GetPosition().x >= worldInfo.GetWorldSize().x){ return paddles.RIGHT; }
        return paddles.NONE;
    }

    private enum paddles{
        RIGHT, LEFT, NONE
    }
}
