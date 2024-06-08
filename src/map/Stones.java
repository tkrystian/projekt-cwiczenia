package map;

import collisions.Grid;
import drawing.GameRenderer;

import java.io.IOException;
import java.util.ArrayList;

public class Stones {
    private final ArrayList<Stone> stones;

    public Stones(Grid grid) throws IOException {
        stones = new ArrayList<>();
        int height = GameRenderer.height;
        int TILE_SIZE = GameRenderer.TILE_SIZE;
        for (int i = 0; i < height / TILE_SIZE; i++) {
            int width = GameRenderer.width;
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
