package enemies;

import collisions.GameObject;
import collisions.Grid;
import drawing.GameRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject {
    private boolean isMovingHorizontally;
    private boolean mirrorMovement;
    private int pixelX, pixelY;


    public Enemy(int x, int y, boolean isMovingHorizontally) throws IOException {
        super(x, y, "enemy", ImageIO.read(new File("src/images/enemy.png")));
        this.isMovingHorizontally = isMovingHorizontally;
        this.mirrorMovement = false;
        this.pixelX = x * GameRenderer.TILE_SIZE;
        this.pixelY = y * GameRenderer.TILE_SIZE;
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public void setPixelX(int pixelX) {
        this.pixelX = pixelX;
    }

    public void setPixelY(int pixelY) {
        this.pixelY = pixelY;
    }


    public int getX() {
        return super.getPosition().x;
    }

    public int getY() {
        return super.getPosition().y;
    }

    public boolean isMovingHorizontally() {
        return isMovingHorizontally;
    }


    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g, ImageObserver o) {

    }

    public boolean isMirrorMovement() {
        return mirrorMovement;
    }

    public void setMirrorMovement(boolean mirrorMovement) {
        this.mirrorMovement = mirrorMovement;
    }

    public void setMovingHorizontally(boolean movingHorizontally) {
        isMovingHorizontally = movingHorizontally;
    }
}
