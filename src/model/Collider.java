package model;

public class Collider {

    // static class methods, can be used without instantiating the class
    public static boolean isCollision(Ball ball, Paddle paddle) {
        // checks the leftmost x value of ball and rightmost x value of paddle and vice versa
        if ((ball.GetMovmentVector().x > 0 &&
                ball.GetPosition().x + ball.GetSize().x >= paddle.GetPosition().x) ||
                (ball.GetMovmentVector().x < 0 &&
                        ball.GetPosition().x <= paddle.GetPosition().x + paddle.GetSize().x)) {
            return true;
        }
        return false;
    }

    public static boolean isCollision(Ball ball, int y) {
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
