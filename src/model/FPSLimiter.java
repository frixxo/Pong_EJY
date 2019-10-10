package model;

public class FPSLimiter {
    private static long lastUpdate;

    FPSLimiter(){
        reset();
    }

    public boolean isFPS(double fps){
        // used to limit fps under a certain threshold
        // ex. if fps = 30 then whatever methods uses it will always be called less than 30 times per sec
        // even in a while (true) loop
        boolean returnValue = false;
        long timeElapsed = (System.currentTimeMillis() - lastUpdate) * 1000;
        if (1 / fps <= timeElapsed) { returnValue = true; }

        reset();
        return returnValue;
    }

    public void reset(){
        lastUpdate = System.currentTimeMillis();
    }
}
