package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    MainPanel mainPanel;

    public boolean leftDown, rightDown;

    public MouseHandler(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouseClickedPosition = e.getPoint();
        if(e.getButton() == 2) {
            for(Slot slot : mainPanel.slots) {
                if(slot.collisionBox.intersects(mainPanel.camera.x + mouseClickedPosition.x, mainPanel.camera.y + mouseClickedPosition.y, 1, 1) && slot.collisionBox.intersects(mainPanel.camera)) {
                    if(slot.heldTile != -1) {
                        mainPanel.currentTile = slot.heldTile;
                    }
                }
            }
        }else if (e.getButton() == 1) {
            if(mainPanel.tilePickerRect.intersects(mouseClickedPosition.x, mouseClickedPosition.y, 1, 1)) {
                for(Tile tile : mainPanel.tiles) {
                    if(tile.collisionBox.intersects(mouseClickedPosition.x, mouseClickedPosition.y, 1, 1)) {
                        mainPanel.currentTile = tile.tileType;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getButton());
        if(e.getButton() == 1) {
            leftDown = true;
        }
        if(e.getButton() == 3) {
            rightDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == 1) {
            leftDown = false;
        }
        if(e.getButton() == 3) {
            rightDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
