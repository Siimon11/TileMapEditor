package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tile Map Editor");

        MainPanel mainPanel = new MainPanel();
        window.add(mainPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        mainPanel.setupProgram();
        mainPanel.startGameThread();
    }
}
