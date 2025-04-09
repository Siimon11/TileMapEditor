package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    MainPanel mainPanel;
    Graphics2D g2;
    public BufferedImage tileSheet;
    public BufferedImage tileImage;
    public UI(MainPanel mainPanel, String path, ArrayList<Rectangle> tileClips) {
        this.mainPanel = mainPanel;
        loadImage(path);
        loadTileClips(tileClips);
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawCanvas();
        drawSlots();
        drawCurrentTile();
        drawTileChooser();
        //drawCamera();
    }
    public void drawCanvas() {
        int canvasWidth = mainPanel.tileMapTileWidth * mainPanel.tileSize * mainPanel.scale;
        int canvasHeight = mainPanel.tileMapTileHeight * mainPanel.tileSize * mainPanel.scale;
        g2.setColor(Color.white);
        g2.fillRect(-mainPanel.camera.x, -mainPanel.camera.y, canvasWidth, canvasHeight);
    }
    public void drawSlots() {
        if(!mainPanel.slots.isEmpty()) {
            for(int i = 0; i < mainPanel.slots.size(); i++) {
                if(mainPanel.slots.get(i).heldTile != -1 && mainPanel.slots.get(i).collisionBox.intersects(mainPanel.camera)) {
                    tileImage = getTileImage(mainPanel.slots.get(i).heldTile);
                    g2.drawImage(tileImage, null, mainPanel.slots.get(i).x - mainPanel.camera.x, mainPanel.slots.get(i).y - mainPanel.camera.y);
                }
            }
        }
    }
    public void drawCurrentTile() {
        g2.setColor(Color.black);
        g2.fillRect(0,0,mainPanel.tileSize+4, mainPanel.tileSize+4);
        tileImage = getTileImage(mainPanel.currentTile);
        g2.drawImage(tileImage, null, 2, 2);
    }

    public void drawTileChooser() {
        g2.setColor(Color.gray);
        g2.fillRect(mainPanel.drawTilesWidth, 0, 500, mainPanel.screenHeight);
        for(Tile tile : mainPanel.tiles) {
            if(tile.y + mainPanel.tileSize > 0 && tile.y < mainPanel.screenHeight) {
                tileImage = getTileImage(tile.tileType);
                g2.drawImage(tileImage, null, tile.x, tile.y);
            }
        }
    }
    public void drawCamera() {
        g2.setColor(Color.red);
        g2.fillRect(0,0, mainPanel.camera.width, mainPanel.camera.height);
    }

    private void loadImage(String path) {
        try {
            tileSheet = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadTileClips(ArrayList<Rectangle> tileClips) {
        int x = 0;
        int y = 0;
        for(int i = 0; i < mainPanel.differentTiles; i++) {
            tileClips.add(new Rectangle(x, y, mainPanel.tileSize, mainPanel.tileSize));
            x += mainPanel.tileSize;
            if(x >= mainPanel.tileSheetTileWidth * mainPanel.tileSize) {
                x = 0;
                y += mainPanel.tileSize;
            }
        }
    }
    private BufferedImage getTileImage(int tileType) {
        return tileSheet.getSubimage(mainPanel.tileClips.get(tileType).x, mainPanel.tileClips.get(tileType).y, mainPanel.tileClips.get(tileType).width, mainPanel.tileClips.get(tileType).height);
    }
}
