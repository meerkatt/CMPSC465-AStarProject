import java.awt.Color;
import java.awt.Graphics;

 class Cell {
    public int x;
    public int y;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
    }
}