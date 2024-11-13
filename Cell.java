import java.awt.Color;
import java.awt.Graphics;

enum CellType {
    EMPTY,
    WALL,
    STARTING_CELL,
    END_CELL,
    PATH,
}

 class Cell {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;
    public int x;
    public int y;
    public CellType type = CellType.EMPTY;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) {
        switch (this.type) {
            case EMPTY, WALL -> g.setColor(Color.BLACK);
            case STARTING_CELL -> g.setColor(Color.GREEN);
            case END_CELL -> g.setColor(Color.BLUE);
            case PATH -> g.setColor(Color.RED);
        }
        
        if (this.type == CellType.EMPTY) {
            g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        } else {
            g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }
        
    }
    
    public void setType(CellType type) {
        this.type = type;
    }
}