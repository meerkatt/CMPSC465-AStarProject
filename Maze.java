import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Maze extends JPanel implements ActionListener { 
    final private Cell[][] maze;
    final private Node[][] nodeGraph;
    private Cell startingCell;
    private Cell endCell;
    private Node startingNode;
    private Node endNode;
    private float wallChance = 0.3f;
    
    public Maze(int width, int height, float wallChance) {
        super();
        this.maze = new Cell[width][height];
        this.nodeGraph = new Node[width][height];
        this.wallChance = wallChance;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = new Cell(i, j); // TODO: combine cell and node classes into one
                nodeGraph[i][j] = new Node(i, j);
            }
        }

        this.setGraphNeighbors(width, height);

        // Add Mouse Listener for press detection
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                handleMousePress(evt);
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                handleKeyPress(evt);
            }
        });
    }

    private void handleMousePress(MouseEvent evt) {
        int cellX = evt.getX() / Cell.WIDTH;
        int cellY = evt.getY() / Cell.HEIGHT;
        
        // left click = starting point for pathfinding
        // right click = end point for pathfinding
        if (evt.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Starting point at (" + cellX + ", " + cellY + ")");
            this.setStartingCell(cellX, cellY);
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            System.out.println("End point at (" + cellX + ", " + cellY + ")");
            this.setEndCell(cellX, cellY);
        }
        repaint();
    }

    private void handleKeyPress(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            // when space is pressed, generate the maze
            System.err.println("Generating Maze...");
            this.generateMaze();
            this.resetMazePath();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // when enter is pressed, start the pathfinding
            System.out.println("Pathfinding...");
            this.generatePath();
        }
    }

    private void setStartingCell(int x, int y) {
        if (startingCell == null) {
            startingCell = maze[x][y];
            startingNode = nodeGraph[x][y];
            startingCell.setType(CellType.STARTING_CELL);
            return;
        }
        
        if (startingCell.type == CellType.WALL ||
            startingCell.type == CellType.END_CELL
        ) {
            System.out.println("Can't set cell as starting cell.");
            return;
        }
        // reset the current starting cell
        startingCell.setType(CellType.EMPTY);
        // assign the new starting cell
        startingCell = maze[x][y];
        startingNode = nodeGraph[x][y];
        startingCell.setType(CellType.STARTING_CELL);
    }

    private void setEndCell(int x, int y) {
        if (endCell == null) {
            endCell = maze[x][y];
            endNode = nodeGraph[x][y];
            endCell.setType(CellType.END_CELL);
            return;
        }

        if (endCell.type == CellType.WALL ||
            endCell.type == CellType.STARTING_CELL
        ) {
            System.out.println("Can't set cell as end cell.");
            return;
        }
        // reset the current end cell
        endCell.setType(CellType.EMPTY);
        // assign the new end cell
        endCell = maze[x][y];
        endNode = nodeGraph[x][y];
        endCell.setType(CellType.END_CELL);
    }

    public void generatePath() {
        resetMazePath();

        if (startingCell != null && endCell != null) {
            Path p = new Path(startingNode, endNode);
            ArrayList<Node> path = p.traverse();

            // Check if a path exists
            if (path == null || path.isEmpty()) {
                System.out.println("No path found!");
                return;
            }

            animatePath(path);
        }
    }

    // just a helper method to animate the path
    private void animatePath(ArrayList<Node> path) {
        // Use an index to keep track of the current node
        // Clever hack to bypass Lambda expressions
        // 1 because we don't want to display the  starting node a as a path
        final int[] index = {1};

        // Timer to animate path traversal with a 25 ms delay
        Timer timer = new Timer(25, (ActionEvent e) -> {
            if (index[0] < path.size()) {
                Node node = path.get(index[0]);
                int x1 = node.getXCoord();
                int y1 = node.getYCoord();
                maze[x1][y1].setType(CellType.PATH);
                repaint();
                index[0]++; // Move to the next node
            } else {
                // Stop the timer when all nodes in the path are processed
                ((Timer) e.getSource()).stop();
            }
        });
        
        timer.start(); // Start the animation timer
    }

    public void generateMaze() {
        // TODO: Generate the maze
        // simple logic for now for each cell it has a 30% chance of being a wall
        this.resetMaze();
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                if (Math.random() < this.wallChance) {
                    cell.setType(CellType.WALL);
                }
            }
        }
        repaint();
    }


    // like resetMaze but only for path cells
    private void resetMazePath() {
        // only set path cells to empty
        for (Cell[] mazeRow : maze) { 
            for (Cell cell : mazeRow) {
                if (cell.type == CellType.PATH) {
                    cell.setType(CellType.EMPTY);
                }
            }
        }
        setGraphNeighbors(maze.length, maze[0].length);
        repaint();
    }

    // helper function for resetMazePath that resets and sets the neighbors for all nodes in nodeGraph
    private void setGraphNeighbors(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node n = null;
                Node e = null;
                Node s = null;
                Node w = null;

                if(j-1 >= 0){ n = nodeGraph[i][j-1]; }
                if(i+1 < width){ e = nodeGraph[i+1][j]; }
                if(j+1 < height){ s = nodeGraph[i][j+1]; }
                if(i-1 >= 0){ w = nodeGraph[i-1][j]; }
                
                nodeGraph[i][j].setNeighbors(n, e, s, w);
                nodeGraph[i][j].resetCost();

            }
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {

                if(maze[i][j].type ==CellType.WALL){
                    if(j-1 >= 0){ nodeGraph[i][j-1].getNeighbors()[2] = null; } //Removing southern neighbor from northern point
                    if(i+1 < maze.length){ nodeGraph[i+1][j].getNeighbors()[3] = null; } //Removing  western neighbor from eastern point
                    if(j+1 < maze[0].length){ nodeGraph[i][j+1].getNeighbors()[0] = null; } //Removing northern neighbor from southern point
                    if(i-1 >= 0){ nodeGraph[i-1][j].getNeighbors()[1] = null; } //Removing eastern neighbor from western point 
                }
            }
        }
    }

    private void resetMaze() {
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                cell.setType(CellType.EMPTY);
            }
        }
        this.startingCell = null;
        this.startingNode = null;
        this.endCell = null;
        this.endNode = null;
        repaint();
    }

    public void render(Graphics g) {
        // Draw the maze
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                cell.render(g);
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}