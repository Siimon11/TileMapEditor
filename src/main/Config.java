package main;

import java.io.*;
import java.util.Objects;

public class Config {
    MainPanel mainPanel;
    public Config(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    public void saveTileMap(String path) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
            StringBuilder out = new StringBuilder();
            for(int i = 0; i < mainPanel.tileMapTileHeight; i++) {
                for(int j = i * mainPanel.tileMapTileWidth; j < mainPanel.tileMapTileWidth * (i+1); j++) {
                    out.append(String.valueOf(mainPanel.slots.get(j).heldTile)).append(" ");
                }
                printWriter.println(out);
                out.delete(0, out.length());
            }

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    public void loadTileMap(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            int j = 0;
            for(int i = 0; i < mainPanel.tileMapTileHeight; i++) {
                String line = bufferedReader.readLine();
                String[] nums = line.split(" ");
                for(String string : nums) {
                    mainPanel.slots.get(j).heldTile = Integer.parseInt(string);
                    j++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
