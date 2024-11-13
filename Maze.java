import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Maze extends JPanel implements ActionListener { 
    final private Cell[][] maze;
    private Cell startingCell;
    private Cell endCell;

    public Maze(int width, int height) {
        super();
        maze = new Cell[width][height];

        nodeGraph = new Node[width][height];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = new Cell(i, j);
                nodeGraph[i][j] = new Node(i,j); 
            }
        }
        setGraphNeighbors(width, height);


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
        this.renderPath();
        repaint();
    }

    private void handleKeyPress(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            // when space is pressed, generate the maze
            System.err.println("Generating Maze...");
            this.generateMaze();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // when enter is pressed, start the pathfinding
            System.out.println("Pathfinding...");
            this.generatePath();
        }
    }

    private void setStartingCell(int x, int y) {
        if (startingCell == null) {
            startingCell = maze[x][y];
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
        startingCell.setType(CellType.STARTING_CELL);
    }

    private void setEndCell(int x, int y) {
        if (endCell == null) {
            endCell = maze[x][y];
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
        endCell.setType(CellType.END_CELL);
    }

    private void generatePath() {
        // for each iteration of the A* algorithm,
        // repaint the maze to visualize the path
        // finding process

        // TODO: Implement the A* algorithm
    }

    public void generateMaze() {
        // TODO: Generate the maze
        // simple logic for now for each cell it has a 30% chance of being a wall
        this.resetMaze();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (Math.random() < 0.3) {
                    cell.setType(CellType.WALL);
                }
            }
        }
        repaint();
    }

    private void resetMaze() {
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                cell.setType(CellType.EMPTY);
            }
        }
    }

    public void render(Graphics g) {
        // Draw the maze
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                cell.render(g);
            }
        }
    }

    public void renderPath(){
        if(startingPoint != null && endPoint != null){
            Path p = new Path(startNode,endNode);
            p.traverse();
            while(p.getCurrentNode() != startNode)
            {
                int x = p.getCurrentNode().getXCoord();
                int y = p.getCurrentNode().getYCoord();

                maze[x][y].onPath = true;
            }
        } 
    }

    public void setGraphNeighbors(int width, int height)
    {
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