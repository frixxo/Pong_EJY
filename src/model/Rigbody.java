package model;

public class Rigbody {
    Vector velocity;
    Rigbody(Vector velocity)
    {
        this.velocity = velocity;
    }

    void Bounce (Vector axis)
    {
        this.velocity.x = axis.x*-1;
        this.velocity.y = axis.y*-1;
    }
}
