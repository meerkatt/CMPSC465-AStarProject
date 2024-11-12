import java.awt.Color;
import java.awt.Graphics;

 class Cell {
    public int x;
    public int y;
    public boolean isWall = false;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);

        if (this.isWall) {
            g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        } else {
            g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }
        
    }
    
    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }   
}