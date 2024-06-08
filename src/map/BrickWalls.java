package map;
import collisions.Grid;

import java.io.IOException;
import java.util.ArrayList;

public class BrickWalls {
    private final ArrayList<BrickWall> brickWalls;

    public BrickWalls(Grid grid) throws IOException {
        int[][] placements = {
                {7, 0}, {8, 0}, {10, 0}, {12, 0}, {14, 0}, {15, 0}, {18, 0}, {19, 0},
                {4, 1}, {6, 1}, {14, 1}, {16, 1}, {18, 1}, {20, 1},
                {1, 2}, {2, 2}, {4, 2}, {8, 2}, {9, 2}, {13, 2}, {14, 2},
                {2, 3}, {6, 3},
                {0, 4}, {2, 4}, {4, 4}, {5, 4}, {9, 4}, {18, 4},
                {0, 5}, {8, 5}, {10, 5}, {12, 5}, {14, 5}, {20, 5},
                {1, 6}, {2, 6}, {4, 6}, {5, 6}, {6, 6}, {7, 6}, {9, 6}, {10, 6}, {13, 6}, {15, 6}, {18, 6}, {19, 6},
                {4, 7}, {6, 7}, {8, 7}, {14, 7}, {16, 7}, {18, 7},
                {0, 8}, {5, 8}, {7, 8}, {8, 8}, {10, 8}, {11, 8}, {18, 8}, {20, 8},
                {0, 9}, {4, 9}, {14, 9}, {18, 9}, {20, 9},
                {1, 10}, {3, 10}, {4, 10}, {5, 10}, {6, 10}, {7, 10}, {9, 10}, {11, 10}, {15, 10}, {17, 10}, {18, 10}, {20, 10},
        };

        brickWalls = new ArrayList<>();
        for (int[] placement : placements) {
            BrickWall brickWall = new BrickWall(placement[0], placement[1]);
            brickWalls.add(brickWall);
            grid.addObject(brickWall);
        }
    }

    public ArrayList<BrickWall> getBrickWalls() {
        return brickWalls;
    }
}
