package collisions;

import java.util.ArrayList;

public class Grid {
    private final int rows;
    private final int columns;
    private final ArrayList<GameObject>[][] grid;


    @SuppressWarnings("unchecked")
    public Grid() {
        this.rows = 11;
        this.columns = 21;
        grid = new ArrayList[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = new ArrayList<>();
            }
        }
    }

    public void addObject(GameObject object) {
        int row = object.getPosition().y;
        int col = object.getPosition().x;

        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            grid[row][col].add(object);
        }
    }


    public void removeObject(GameObject object) {
        int row = object.getPosition().y;
        int col = object.getPosition().x;
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            grid[row][col].remove(object);
        }
    }



    public ArrayList<GameObject> getObjectsAt(int x, int y) {
        if (x >= 0 && x < columns && y >= 0 && y < rows) {
            return new ArrayList<>(grid[y][x]);
        }
        return new ArrayList<>();
    }


    public void updateObjectPosition(GameObject object, int newX, int newY) {
        removeObject(object);
        object.setPosition(newX, newY);
        addObject(object);
    }

    public boolean isEmpty(int x, int y) {
        if (y < 0 || y >= rows || x < 0 || x >= columns) {
            return false;
        }
        return grid[y][x].isEmpty();
    }

    public ArrayList<GameObject>[][] getGrid() {
        return grid;
    }

    public ArrayList<GameObject> getAllObjects() {
        ArrayList<GameObject> allObjects = new ArrayList<>();
        for (ArrayList<GameObject>[] arrayLists : grid) {
            for (ArrayList<GameObject> arrayList : arrayLists) {
                allObjects.addAll(arrayList);
            }
        }
        return allObjects;
    }
}
