package stats;

import java.util.Timer;
import java.util.TimerTask;

public class GameStats {
    private String playerName;
    private int points;
    private int lives;
    private boolean isGameOver;
    private boolean isImmuneAfterDamage = false;


    public GameStats() {
        playerName = "Gracz";
        points = 0;
        isGameOver = false;
        lives=3;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint(){
        points++;
        if(points == 4){
            setPlayerName(playerName);
            setGameOver(true);
        }
    }

    public void dealDamage(){
        if(!isImmuneAfterDamage){
            lives--;
            isImmuneAfterDamage = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isImmuneAfterDamage = false;
                    timer.cancel();
                }
            }, 1000);
        }

        if(lives == 0){
            setPlayerName(playerName);
            setGameOver(true);

        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getLives() {
        return lives;
    }
}
