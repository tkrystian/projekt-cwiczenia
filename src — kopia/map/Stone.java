package map;

import collisions.GameObject;
import collisions.Grid;
import drawing.GameRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Stone extends GameObject {
    private Grid grid;

    public Stone(int x, int y) throws IOException {
        super(x, y, "stone", ImageIO.read(new File("src/images/stone.png")));
    }

    private void loadImage(){

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
