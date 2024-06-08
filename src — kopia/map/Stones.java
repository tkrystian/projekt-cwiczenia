package map;

import collisions.Grid;
import drawing.GameRenderer;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

public class Stones {
    private final ArrayList<Stone> stones;
    private int height = GameRenderer.height;
    private final int TILE_SIZE = GameRenderer.TILE_SIZE;
    private int width = GameRenderer.width;

    public Stones(Grid grid) throws IOException {
        stones = new ArrayList<>();
        for (int i = 0; i < height / TILE_SIZE; i++) {
            for (int j = 0; j < width / TILE_SIZE; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    Stone stone = new Stone(j, i);
                    stones.add(stone);
                    grid.addObject(stone);
                }
            }
        }
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }
}
