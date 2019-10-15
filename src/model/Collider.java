package model;

public class Collider {

    // static class methods, can be used without instantiating the class
    public static boolean isCollision(Ball ball, Paddle paddle) {
        // checks if the ball collide with the paddle
        if (ball.GetPosition().y - ball.GetSize().y > paddle.GetPosition().y ||
            ball.GetPosition().y < paddle.GetPosition().y - paddle.GetSize().y ||
                (ball.GetPosition().x > paddle.GetPosition().x + paddle.GetSize().x && ball.GetMovmentVector().x < 0) ||
                (ball.GetPosition().x + ball.GetSize().x < paddle.GetPosition().x && ball.GetMovmentVector().x > 0)) {
            return false;
        }
        return true;
    }

    public static boolean isCollision(Ball ball, double y) {
        // checks only floor and ceiling
        // walls has nothing to do with collision
        // x,y starts at bottom-left, right?
        if ((ball.GetMovmentVector().y > 0 && ball.GetPosition().y <= 0) ||
                (ball.GetMovmentVector().y < 0 && ball.GetPosition().y >= y)){
            return true;
        }
        return false;
    }
}
