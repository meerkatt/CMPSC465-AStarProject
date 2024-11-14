
public class Main{
    static final int WIDTH_OFFSET = 8;
    static final int HEIGHT_OFFSET = 10;
    
    public static void main(String[] args) {
        final int gridWidth = 20;
        final int gridHeight = 20;
        final int windowWidth = Cell.WIDTH * (gridWidth + 1) - WIDTH_OFFSET;
        final int windowHeight = Cell.HEIGHT * (gridHeight + 2) - HEIGHT_OFFSET;
        final float wallChance = 0.30f;

        GUI gui = new GUI(windowWidth, windowHeight, "AStar Project");
        Maze maze = new Maze(gridWidth,gridHeight, wallChance);
        
        gui.add(maze);
        gui.show();
    }
}
