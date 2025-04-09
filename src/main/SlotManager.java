package main;

public class SlotManager {
    MainPanel mainPanel;
    public SlotManager(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    public void loadSlots() {
        int x = 0;
        int y = 0;
        for(int i = 0; i < mainPanel.totalTiles; i++) {
            mainPanel.slots.add(new Slot(mainPanel, x, y, -1));
            x += mainPanel.tileSize;
            if(x >= mainPanel.tileMapTileWidth * mainPanel.tileSize) {
                x = 0;
                y += mainPanel.tileSize;
            }
        }
    }
}
