package player;

import collisions.Grid;
import drawing.GameRenderer;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Explosions {
    private final ArrayList<Explosion> explosions;
    private final Grid grid;

    public Explosions(Grid grid) {
        explosions = new ArrayList<>();
        this.grid = grid;
    }

    public void addExplosion(int x, int y){
        if(x<0 || y<0 || x>20 || y>20||
                ((x % 2 == 1) && (y % 2 == 1))){
            return;
        }
        try {
            Explosion explosion =new Explosion(x, y);
            explosions.add(explosion);
            grid.addObject(explosion);
        } catch (IOException e) {
            System.out.println("Błąd plików gry");
        }
    }

    public ArrayList<Explosion> getExplosions() {
        return explosions;
    }

    public void clearExplosions() {
        Iterator<Explosion> iterator = explosions.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            grid.removeObject(explosion);
            iterator.remove();
        }
    }

    public void draw(Graphics g, ImageObserver o) {
        ArrayList<Explosion> explosionsCopy = new ArrayList<>(explosions);
        for (Explosion explosion : explosionsCopy) {
            g.drawImage(
                    explosion.getImage(),
                    explosion.getPosition().x * GameRenderer.TILE_SIZE,
                    explosion.getPosition().y * GameRenderer.TILE_SIZE,
                    o
            );
        }
    }
}
