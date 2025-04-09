package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel implements Runnable {
    // Muuttujat
    public final int tileSize = 128;
    public final int tileSheetTileWidth = 8;
    public final int tileSheetTileHeight = 12;
    public final int tileMapTileWidth = 40;
    public final int tileMapTileHeight = 40;
    public final String tileSheetPath = "/GardenSpriteSheet4.png";

    public final int differentTiles = tileSheetTileWidth * tileSheetTileHeight;
    public final int totalTiles = tileMapTileWidth * tileMapTileHeight;

    public final int speed = 30;
    //------------------//

    Thread thread;

    ArrayList<Tile> tiles = new ArrayList<Tile>(tileMapTileWidth * tileMapTileHeight);
    ArrayList<Slot> slots = new ArrayList<Slot>(tileMapTileWidth * tileMapTileHeight);
    ArrayList<Rectangle> tileClips = new ArrayList<Rectangle>(differentTiles);

    MouseHandler mouseHandler = new MouseHandler(this);
    KeyHandler keyHandler = new KeyHandler(this);
    UI ui = new UI(this, tileSheetPath, tileClips);
    SlotManager slotManager = new SlotManager(this);
    TilePicker tilePicker = new TilePicker(this);
    Config config = new Config(this);

    public int drawTilesWidth = 800;
    public int screenWidth = 1200;
    public int screenHeight = 600;
    private final int FPS = 10;
    public int scale = 1;

    public int currentTile = 0;

    public Rectangle camera = new Rectangle(0, 0, drawTilesWidth, screenHeight);
    public Rectangle tileDrawerRect = new Rectangle(0, 0, drawTilesWidth, screenHeight);

    public Point mousePosition = new Point(0,0);
    public Rectangle tilePickerRect = new Rectangle(drawTilesWidth, 0, 500, screenHeight);


    public MainPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 0, 0));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }
    public void setupProgram() {
        slotManager.loadSlots();
        tilePicker.loadTiles();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (thread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        mousePosition.x = MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x;
        mousePosition.y = MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y;
        for(Tile tile : tiles) {
            tile.update();
        }
        if(keyHandler.leftPressed) {
            camera.x -= speed;
        }
        if(keyHandler.rightPressed) {
            camera.x += speed;
        }
        if(keyHandler.upPressed) {
            if(tileDrawerRect.intersects(mousePosition.x, mousePosition.y, 1, 1)) {
                camera.y -= speed;
            } else if (tilePickerRect.intersects(mousePosition.x, mousePosition.y, 1, 1)) {
                moveTiles(20);
            }
        }
        if(keyHandler.downPressed) {
            if(tileDrawerRect.intersects(mousePosition.x, mousePosition.y, 1, 1)) {
                camera.y += speed;
            } else if (tilePickerRect.intersects(mousePosition.x, mousePosition.y, 1, 1)) {
                moveTiles(-20);
            }
        }
        for(Slot slot : slots) {
            if(slot.collisionBox.intersects(camera.x + mousePosition.x, camera.y + mousePosition.y, 1, 1) && slot.collisionBox.intersects(camera)) {
                if(mouseHandler.leftDown) {
                    slot.heldTile = currentTile;
                } else if (mouseHandler.rightDown) {
                    slot.heldTile = -1;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        ui.draw(g2);
        g2.dispose();
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }
    public void moveTiles(int amount) {
        for(Tile tile : tiles) {
            tile.y += amount;
        }
    }
}
