package player;

import collisions.GameObject;
import drawing.GameRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Explosion extends GameObject {

    public Explosion(int x, int y) throws IOException {
        super(x, y, "explosion", ImageIO.read(new File("src/images/explosion.png")));
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw(Graphics g, ImageObserver o) {

    }

    public int getPositionX() {
        return super.getPosition().x;
    }

    public int getPositionY() {
        return super.getPosition().y;
    }
}
