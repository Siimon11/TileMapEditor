package main;

import java.awt.*;

public class Tile {
    public int x;
    public int y;
    public int tileType;
    public Rectangle collisionBox;
    MainPanel mainPanel;
    public Tile(MainPanel mainPanel, int x, int y, int tileType) {
        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        collisionBox = new Rectangle(x, y, mainPanel.tileSize, mainPanel.tileSize);
    }
    public void update() {
        collisionBox.y = y;
    }
}
