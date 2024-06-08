package drawing;

import stats.GameStats;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverScreen {
    private BufferedImage gameOverImage;

    public void loadGameOverImage() {
        try {
            gameOverImage = ImageIO.read(new File("src/images/game_over.png"));
        } catch (IOException e) {
            System.out.println("Błąd grafiki game over");
        }
    }

    public void drawGameOverScreen(Graphics g, JPanel panel, GameStats gameStats) {
        g.drawImage(gameOverImage, 0, 0, GameRenderer.width, GameRenderer.height + GameRenderer.TILE_SIZE, panel);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.BOLD, GameRenderer.TILE_SIZE));
        g.drawString(
                "Gracz " + gameStats.getPlayerName() + " osiągnął wynik: " + gameStats.getPoints(),
                GameRenderer.TILE_SIZE, (GameRenderer.height * 3/4)-GameRenderer.TILE_SIZE);
        g.drawString(
                "Naciśnij Enter aby wrócić do menu",
                GameRenderer.TILE_SIZE, (GameRenderer.height * 3/4));
    }
}
