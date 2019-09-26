package Grafics;

//spara highscore med namn
public class Highscore {

    public int isHighscore(int score, int[] highscore) {
        for (int x = 0; x < highscore.length; x++) {
            if (score > highscore[lowest])
                return(ranking(score, highscore));  //ranks your score and shifts existing scores.
        }
    }

    public int ranking(int score, int[] highscore){

        for(int x=0;x<highscore.length;x++){
            if(score<=highscore[x]){
                for(int y=highscore.length;y<(highscore.length-x-1);y--){
                    highscore[y+1]=highscore[y];
                }
                highscore[x]=score;
                return x;
            }
        }
    }

}
