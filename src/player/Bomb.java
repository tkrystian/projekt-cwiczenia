package player;

import collisions.GameObject;
import collisions.Grid;
import drawing.GameRenderer;
import enemies.Enemies;
import stats.GameStats;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject {
    private boolean isActive = false;
    private final ScheduledExecutorService executorService;
    private final Grid grid;
    private final Explosions explosions;
    private final GameStats gameStats;
    private final Enemies enemies;

    public Bomb(int x, int y, Grid grid, GameStats gameStats, Enemies enemies) throws IOException {
        super(x, y, "bomb", ImageIO.read(new File("src/images/bomb.png")));
        this.grid = grid;
        this.gameStats = gameStats;
        this.enemies = enemies;
        this.explosions = new Explosions(grid);
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void placeBomb(int x, int y) {
        if (!isActive) {
            isActive = true;
            super.setPosition(x, y);
            grid.addObject(this);
            executorService.schedule(this::explode, 1, TimeUnit.SECONDS);
        }
    }

    private void explode() {
        isActive = false;
        grid.removeObject(this);

        int[][] offsets = {{0, 0}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] offset : offsets) {
            int explosionX = super.getPosition().x + offset[0];
            int explosionY = super.getPosition().y + offset[1];
            explosions.addExplosion(explosionX, explosionY);
        }

        ArrayList<GameObject> objectsForRemoval = new ArrayList<>();

        for(Explosion explosion:explosions.getExplosions()){
            objectsForRemoval.addAll(grid.getObjectsAt(explosion.getPositionX(), explosion.getPositionY()));
        }

        for(GameObject gameObject : objectsForRemoval){
            switch (gameObject.getObjectID()) {
                case "brick" -> grid.removeObject(gameObject);
                case "enemy" -> {
                    grid.removeObject(gameObject);
                    enemies.getEnemies().remove(gameObject);
                    gameStats.addPoint();
                }
                case "player" -> gameStats.dealDamage();
            }
        }

        executorService.schedule(this::clearExplosion, 1, TimeUnit.SECONDS);
    }

    private void clearExplosion() {
        explosions.clearExplosions();
    }

    @Override
    public void tick() {
    }

    public void draw(Graphics g, ImageObserver o) {
        if (isActive) {
            g.drawImage(this.getImage(),
                    super.getPosition().x * GameRenderer.TILE_SIZE,
                    super.getPosition().y * GameRenderer.TILE_SIZE,
                    o
            );
        }
        explosions.draw(g, o);
    }
}
