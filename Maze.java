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
    private Cell startingPoint;
    private Cell endPoint;
    private Node startNode;
    private Node endNode;
    private Node[][] nodeGraph;

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
            System.out.println("Left Pressed at (" + cellX + ", " + cellY + ")");
            startingPoint = maze[cellX][cellY];
            startNode = nodeGraph[cellX][cellY];
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Right Pressed at (" + cellX + ", " + cellY + ")");
            endPoint = maze[cellX][cellY];
            endNode = nodeGraph[cellX][cellY];
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
            
            System.out.println("Enter Pressed");
        }
    }

    public void generateMaze() {
        // TODO: Generate the maze
        // simple logic for now for each cell it has a 30% chance of being a wall
        this.resetMaze();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (Math.random() < 0.3) {
                    maze[i][j].setWall(true);
                    nodeGraph[i][j].deleteNeighbors();
                    if(j-1 >= 0){ nodeGraph[i][j-1].getNeighbors()[2] = null; } //Removing southern neighbor from northern point
                    if(i+1 < maze.length){ nodeGraph[i+1][j].getNeighbors()[3] = null; } //Removing  western neighbor from eastern point
                    if(j+1 < maze[0].length){ nodeGraph[i][j+1].getNeighbors()[0] = null; } //Removing northern neighbor from southern point
                    if(i-1 >= 0){ nodeGraph[i-1][j].getNeighbors()[1] = null; } //Removing eastern neighbor from western point 
                }
            }
        }
        repaint();
    }

    private void resetMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].setWall(false);
                setGraphNeighbors(maze.length, maze[0].length);
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