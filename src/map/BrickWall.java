package map;

import collisions.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BrickWall extends GameObject {

    public BrickWall(int x, int y) throws IOException {
        super(x, y, "brick", ImageIO.read(new File("src/images/brick_wall.png")));
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g, ImageObserver o) {

    }

}
