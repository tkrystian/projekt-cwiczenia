package drawing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
    private BufferedImage titleScreenImage;
    private BufferedImage selectorBomb;

    public void loadTitleScreen() {
        try {
            titleScreenImage = ImageIO.read(new File("src/images/title_screen.png"));
            selectorBomb = ImageIO.read(new File("src/images/bomb.png"));
        } catch (IOException e) {
            System.out.println("Błąd grafiki tła");
        }
    }

    public void drawMenu(Graphics g, JPanel panel, int selectedOptionInMenu) {
        g.drawImage(titleScreenImage, 0, 0, GameRenderer.width, GameRenderer.height + GameRenderer.TILE_SIZE, panel);

        int menuItemY = GameRenderer.height * 3 / 4;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.BOLD, GameRenderer.TILE_SIZE));
        g.drawString("Nowa gra", GameRenderer.TILE_SIZE * 3, menuItemY - GameRenderer.TILE_SIZE);
        g.drawString("Zmień nazwę", GameRenderer.TILE_SIZE * 3, menuItemY);
        g.drawString("Wyjście", GameRenderer.TILE_SIZE * 3, menuItemY + GameRenderer.TILE_SIZE);
        g.drawImage(selectorBomb,
                GameRenderer.TILE_SIZE * 2,
                (menuItemY - GameRenderer.TILE_SIZE * 2 + (selectedOptionInMenu * GameRenderer.TILE_SIZE)),
                panel);
    }
}
