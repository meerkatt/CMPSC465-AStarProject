
public class Main{
    public static void main(String[] args) {
        int gridWidth = 20;
        int gridHeight = 20;
        int windowWidth = Cell.WIDTH * (gridWidth + 1) - 8;
        int windowHeight = Cell.HEIGHT * (gridHeight + 2) - 10;
        
        GUI gui = new GUI(windowWidth, windowHeight, "AStar Project");
        Maze maze = new Maze(gridWidth,gridHeight);
        
        gui.add(maze);
        gui.show();
    }
}
