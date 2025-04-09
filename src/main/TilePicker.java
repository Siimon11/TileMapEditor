package main;

public class TilePicker {
    MainPanel mainPanel;
    public TilePicker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    public void loadTiles() {
        int difference = 5;
        int left = mainPanel.drawTilesWidth + difference;
        int x = left;
        int y = difference;
        for(int i = 0; i < mainPanel.differentTiles; i++) {
            mainPanel.tiles.add(new Tile(mainPanel, x, y, i));
            x += mainPanel.tileSize + 5;
            if(x >= mainPanel.screenWidth-96) {
                x = left;
                y += mainPanel.tileSize + difference;
            }
        }
    }
}
