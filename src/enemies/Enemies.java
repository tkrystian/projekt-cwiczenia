package enemies;

import collisions.GameObject;
import collisions.Grid;
import drawing.GameRenderer;
import stats.GameStats;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Enemies {
    private final ArrayList<Enemy> enemies;
    private int tickCounter = 0;
    private final Grid grid;
    private final GameStats gameStats;

    public Enemies(Grid grid, GameStats gameStats) throws IOException {
        this.grid = grid;
        this.gameStats = gameStats;
        int[][] placements = {
                {0, 0}, {12, 1}, {15, 2}, {12, 6}
        };
        enemies = new ArrayList<>();
        for (int[] placement : placements) {
            Enemy enemy = new Enemy(placement[0], placement[1], placement[1] % 2 == 0);
            enemies.add(enemy);
            grid.addObject(enemy);
        }
    }

    public void draw(Graphics g, ImageObserver o){
        for(Enemy enemy: enemies){
            g.drawImage(enemy.getImage(),
                    enemy.getX() * GameRenderer.TILE_SIZE,
                    enemy.getY() * GameRenderer.TILE_SIZE,
                    o);
        }
    }

    public void tick() {
        int TICK_THRESHOLD = 20;
        if (tickCounter >= TICK_THRESHOLD) {
            tickCounter = 0;
            Iterator<Enemy> iterator = enemies.iterator();
            ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (enemy.isMovingHorizontally()) {
                    if (checkCollisionsV2(enemy, 1, 0)) {
                        enemiesToRemove.add(enemy);
                    }
                } else {
                    if (checkCollisionsV2(enemy, 0, 1)) {
                        enemiesToRemove.add(enemy);
                    }
                }
            }
            for (Enemy enemy : enemiesToRemove) {
                grid.removeObject(enemy);
                enemies.remove(enemy);
                gameStats.addPoint();
            }
        } else {
            tickCounter++;
        }
    }

    private boolean checkCollisionsV2(Enemy enemy, int movementX, int movementY) {
        int newX = enemy.getPosition().x + (enemy.isMirrorMovement() ? -movementX : movementX);
        int newY = enemy.getPosition().y + (enemy.isMirrorMovement() ? -movementY : movementY);

        if (newX < 0 || newX > 20 || newY < 0 || newY > 10 ||
                ((newX % 2 == 1) && (newY % 2 == 1))) {
            enemy.setMirrorMovement(!enemy.isMirrorMovement());
            return false;
        }

        ArrayList<GameObject> gameObjects = grid.getObjectsAt(newX, newY);
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getObjectID().equals("explosion")) {
                grid.updateObjectPosition(enemy, newX, newY);
                return true;
            } else if (!gameObject.getObjectID().equals("player")) {
                enemy.setMirrorMovement(!enemy.isMirrorMovement());
                if (new Random().nextInt(3) == 1 && (enemy.getPosition().x + enemy.getPosition().y) % 2 == 0) {
                    enemy.setMovingHorizontally(!enemy.isMovingHorizontally());
                }
                return false;
            }
        }
        grid.updateObjectPosition(enemy, newX, newY);
        return false;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
