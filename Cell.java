import java.awt.Color;
import java.awt.Graphics;

 class Cell {
    public int x;
    public int y;
    public boolean isWall = false;
    public boolean cellVisited = false;
    public boolean onPath = false;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) {
        

        if (this.isWall) {
            g.setColor(Color.BLACK);
            g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        } else if(this.cellVisited) {
            g.setColor(Color.YELLOW);
            g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        } else if(this.onPath){
            g.setColor(Color.GREEN);
            g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        } else {
            g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }
        
    }
    
    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }   
}