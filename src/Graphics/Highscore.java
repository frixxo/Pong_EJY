package Graphics;

import java.io.*;
import java.util.Arrays;
import java.util.List;

//spara highscore med namn
public class Highscore {
    //Writer wr = new FileWriter("Highscore.txt");
   // Reader rd = new FileReader("Highscore.txt");


//TODO spara highscore

    public int checkHighscore(int score) {
        int[] highscore= null;
        //TODO ta in gamla highscore till highscore[]

        for (int x = 0; x < highscore.length; x++) {
            if (score > highscore[highscore.length - 1])
                return (ranking(score, highscore));  //ranks your score and shifts existing scores.
        }
        return -1;
    }

    public int ranking(int score, int[] highscore) {

        for (int x = 0; x < highscore.length; x++) {
            if (score <= highscore[x]) {
                for (int y = highscore.length; y < (highscore.length - x - 1); y--) {
                    highscore[y + 1] = highscore[y];
                }
                highscore[x] = score;
                return x;
            }
        }

    return -1;
    }
}
