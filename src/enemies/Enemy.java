package enemies;

import collisions.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject {
    private boolean isMovingHorizontally;
    private boolean mirrorMovement;


    public Enemy(int x, int y, boolean isMovingHorizontally) throws IOException {
        super(x, y, "enemy", ImageIO.read(new File("src/images/enemy.png")));
        this.isMovingHorizontally = isMovingHorizontally;
        this.mirrorMovement = false;
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
