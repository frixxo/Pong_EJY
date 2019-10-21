package model;

public class Collider {

    // static class methods, can be used without instantiating the class
    public static boolean isCollision(Ball ball, Paddle paddle) {
        // checks if the ball collide with the paddle
        // the paddle the ball is colliding with must be specified in some way
        Vector ballSize = ball.GetSize();
        Vector ballPos = ball.GetPosition();
        Vector ballVector = ball.GetMovmentVector();
        Vector paddleSize = paddle.GetSize();
        Vector paddlePos = paddle.GetPosition();

        if (ballVector.x < 0 && !(ballPos.y - ballSize.y / 2 > paddlePos.y + paddleSize.y / 2) &&
                !(ballPos.y + ballSize.y / 2 < paddlePos.y - paddleSize.y / 2) &&
                !(ballPos.x - ballSize.x / 2 > paddlePos.x + paddleSize.x / 2)){
            return true;
        } else return ballVector.x > 0 && !(ballPos.y - ballSize.y / 2 > paddlePos.y + paddleSize.y / 2) &&
                !(ballPos.y + ballSize.y / 2 < paddlePos.y - paddleSize.y / 2) &&
                !(ballPos.x + ballSize.x / 2 < paddlePos.x - paddleSize.x / 2);
    }

    public static boolean isCollision(Ball ball, double y) {
        // checks if collides with floor or ceiling
        // walls has nothing to do with collision
        Vector movementVector = ball.GetMovmentVector();
        Vector position = ball.GetPosition();
        Vector size = ball.GetSize();

        return (movementVector.y < 0 && position.y - size.y / 2 <= 0) ||
                (movementVector.y > 0 && position.y + size.y / 2 >= y);
    }
}
