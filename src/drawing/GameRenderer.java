package drawing;

import collisions.GameObject;
import collisions.Grid;
import enemies.Enemies;
import player.Bomb;
import player.Player;
import stats.GameStats;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class GameRenderer {
    public static final int width = 1260;
    public static final int height = 660;
    public static final int TILE_SIZE = 60;
    public static final int ROWS = height / TILE_SIZE;
    public static final int COLUMNS = width / TILE_SIZE;

    private Grid grid;
    private final GameStats gameStats;

    public GameRenderer(Grid grid, GameStats gameStats) {
        this.grid = grid;
        this.gameStats = gameStats;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void drawGame(Graphics g, ImageObserver observer, Player player, Bomb bomb, Enemies enemies) {
        drawGrid(g, observer);
        bomb.draw(g, observer);
        enemies.draw(g, observer);
        player.draw(g, observer);
    }

    public void drawScoreboard(Graphics g) {
        g.setColor(new Color(181, 192, 183));
        g.fillRect(0, height, width, TILE_SIZE);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.BOLD, TILE_SIZE / 2));
        g.drawString("Gracz " + gameStats.getPlayerName()
                        + " życia: " + gameStats.getLives()
                        + " punkty: " + gameStats.getPoints(),
                TILE_SIZE * 2, height + (TILE_SIZE / 2) + g.getFont().getSize() / 2);
    }

    private void drawGrid(Graphics g, ImageObserver observer) {
        if (grid != null) {
            ArrayList<GameObject> allObjects = grid.getAllObjects();
            for (GameObject object : allObjects) {
                g.drawImage(
                        object.getImage(),
                        object.getPosition().x * TILE_SIZE,
                        object.getPosition().y * TILE_SIZE,
                        observer
                );
            }
        } else {
            System.err.println("Grid is not initialized!");
        }
    }

}
