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

    public Maze(int width, int height) {
        super();
        maze = new Cell[width][height];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }

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
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Right Pressed at (" + cellX + ", " + cellY + ")");
            endPoint = maze[cellX][cellY];
        }
        
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
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                if (Math.random() < 0.3) {
                    cell.setWall(true);
                }
            }
        }
        repaint();
    }

    private void resetMaze() {
        for (Cell[] mazeRow : maze) {
            for (Cell cell : mazeRow) {
                cell.setWall(false);
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