package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    MainPanel mainPanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(e.isControlDown() && code == KeyEvent.VK_R) {
            mainPanel.config.loadTileMap("res/maps/map1.map");
        }
        if(e.isControlDown() && code == KeyEvent.VK_S) {
            mainPanel.config.saveTileMap("res/maps/map1.map");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
