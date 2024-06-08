package player;

import collisions.GameObject;
import collisions.Grid;
import drawing.GameRenderer;
import enemies.Enemies;
import stats.GameStats;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Player extends GameObject {
    private final Bomb bomb;
    private int movementX = 0;
    private int movementY = 0;
    private int tickCounter = 0;
    private final Set<Integer> keysPressed;
    private final GameStats gameStats;
    private final Grid grid;

    public Player(GameStats gameStats, Grid grid, Enemies enemies) throws IOException {
        super(10,4, "player", ImageIO.read(new File("src/images/player.png")));
        this.grid = grid;
        this.gameStats = gameStats;
        bomb = new Bomb(-100, -100,grid, gameStats, enemies);
        keysPressed = new HashSet<>();
        grid.addObject(this);
    }


    public void draw(Graphics g, ImageObserver o){
        g.drawImage(this.getImage(),
                super.getPosition().x * GameRenderer.TILE_SIZE,
                super.getPosition().y * GameRenderer.TILE_SIZE,
                o);
    }

    public void keyPressed(KeyEvent e){
        keysPressed.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e){
        keysPressed.remove(e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            bomb.placeBomb(super.getPosition().x, super.getPosition().y);
        }
    }

    public void tick(){
        int TICK_THRESHOLD = 5;
        if (tickCounter >= TICK_THRESHOLD) {
            tickCounter = 0;
            processMovement();
        } else {
            tickCounter++;
        }
        updateGridPosition();
    }

    private void updateGridPosition(){
        grid.updateObjectPosition(this, super.getPosition().x, super.getPosition().y);
    }

    private void processMovement(){
        movementX = 0;
        movementY = 0;
        if(keysPressed.contains(KeyEvent.VK_UP)){
            movementY = -1;
        }
        if(keysPressed.contains(KeyEvent.VK_DOWN)){
            movementY = 1;
        }
        if(keysPressed.contains(KeyEvent.VK_LEFT)){
            movementX = -1;
        }
        if(keysPressed.contains(KeyEvent.VK_RIGHT)){
            movementX = 1;
        }

        checkCollisionsV2();

        if(super.getPosition().x < 0){
            super.getPosition().x = 0;
        }else if(super.getPosition().x >= GameRenderer.COLUMNS){
            super.getPosition().x = GameRenderer.COLUMNS - 1;
        }
        if(super.getPosition().y < 0){
            super.getPosition().y = 0;
        }else if(super.getPosition().y >= GameRenderer.ROWS){
            super.getPosition().y = GameRenderer.ROWS - 1;
        }
    }


    private void checkCollisionsV2() {
        Point tempPosition = new Point(super.getPosition());
        tempPosition.translate(movementX, movementY);

        if(tempPosition.x < 0 || tempPosition.y<0 || tempPosition.x>20 || tempPosition.y>20){
            return;
        }

        if (grid.isEmpty(tempPosition.x, tempPosition.y)) {
            grid.updateObjectPosition(this, tempPosition.x, tempPosition.y);
        } else {
            ArrayList<GameObject> gameObjects = new ArrayList<>(grid.getObjectsAt(tempPosition.x, tempPosition.y));
            for (GameObject gameObject : gameObjects) {
                if (gameObject.getObjectID().equals("explosion") || gameObject.getObjectID().equals("enemy")) {
                    gameStats.dealDamage();
                    grid.updateObjectPosition(this, tempPosition.x, tempPosition.y);
                }
            }
        }
    }


    public Point getPosition(){
        return super.getPosition();
    }

    public Bomb getBomb(){
        return bomb;
    }

}
