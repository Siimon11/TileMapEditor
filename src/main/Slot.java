package main;

import java.awt.*;

public class Slot {
    public int x;
    public int y;
    public int heldTile = -1;
    public Rectangle collisionBox;

    MainPanel mainPanel;

    public Slot(MainPanel mainPanel, int x, int y, int heldTile) {
        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;
        this.heldTile = heldTile;
        collisionBox = new Rectangle(x, y, mainPanel.tileSize, mainPanel.tileSize);
    }
}
