package model;

public class FPSLimiter {
    private static long lastUpdate;

    public FPSLimiter(){
        reset();
    }

    public boolean isFPS(double fps){
        // used to limit fps under a certain threshold
        // ex. if fps = 30 then whatever methods uses it will always be called less than 30 times per sec
        // even in a while (true) loop
        double timeElapsed = (System.currentTimeMillis() - lastUpdate) / 1000.0;
        if (1 / fps <= timeElapsed) {
            reset();
            return true;
        }
        return false;
    }

    public void reset(){
        lastUpdate = System.currentTimeMillis();
    }
}
