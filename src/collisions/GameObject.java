package collisions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class GameObject {
    private final Point position;
    private final String objectID;
    private final BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public GameObject(int x, int y, String objectID, BufferedImage image) {
        this.position = new Point(x, y);
        this.objectID = objectID;
        this.image = image;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
    }

    public abstract void tick();

    public abstract void draw(Graphics g, ImageObserver o);

    public String getObjectID() {
        return objectID;
    }
}
