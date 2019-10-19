package model;

public class Collider {

    // static class methods, can be used without instantiating the class
    public static boolean isCollision(Ball ball, Paddle paddle) {
        // checks if the ball collide with the paddle
        // the paddle the ball is colliding with must be specified in some way
        Vector ballSize = ball.GetSize();
        Vector ballPos = ball.GetPosition();
        Vector paddleSize = paddle.GetSize();
        Vector paddlePos = paddle.GetPosition();

        return !(ballPos.y - ballSize.y > paddlePos.y) && !(ballPos.y < paddlePos.y - paddleSize.y) &&
                !(ballPos.x > paddlePos.x + paddleSize.x) && !(ballPos.x + ballSize.x < paddlePos.x);
    }

    public static boolean isCollision(Ball ball, double y) {
        // checks if collides with floor or ceiling
        // walls has nothing to do with collision
        Vector movementVector = ball.GetMovmentVector();
        Vector position = ball.GetPosition();
        Vector size = ball.GetSize();

        return (movementVector.y < 0 && position.y - size.y <= 0) ||
                (movementVector.y > 0 && position.y >= y);
    }
}
