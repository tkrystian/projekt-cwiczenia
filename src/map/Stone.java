package map;

import collisions.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Stone extends GameObject {

    public Stone(int x, int y) throws IOException {
        super(x, y, "stone", ImageIO.read(new File("src/images/stone.png")));
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g, ImageObserver o) {

    }

}
