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
        
        if (evt.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Left Pressed at (" + cellX + ", " + cellY + ")");
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Right Pressed at (" + cellX + ", " + cellY + ")");
        }
        
        repaint();
    }

    private void handleKeyPress(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Space Pressed");
        }
    }

    public void render(Graphics g) {
        // Draw the maze
        for (Cell[] maxRow : maze) {
            for (Cell cell : maxRow) {
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