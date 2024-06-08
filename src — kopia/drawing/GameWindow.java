package drawing;

import javax.swing.*;

public class GameWindow {
    public static void initialize(){
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.addKeyListener(gamePanel);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
