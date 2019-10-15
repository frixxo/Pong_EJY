import model.FPSLimiter;

import java.time.*;
import java.io.*;
class Test{
    public static void main(String args [])
    {
        FPSLimiter fps = new FPSLimiter();
        while (true) {
            if (fps.isFPS(2)){
                System.out.println("1s elapsed");
            }
        }
    }
}
