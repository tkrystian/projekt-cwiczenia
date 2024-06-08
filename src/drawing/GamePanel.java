package drawing;

import collisions.Grid;
import enemies.Enemies;
import map.BrickWall;
import map.BrickWalls;
import map.Stone;
import map.Stones;
import player.Bomb;
import player.Player;
import stats.GameStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseWheelListener {
    private Timer timer;
    private Player player;
    private Bomb bomb;
    private Enemies enemies;
    private GameStats gameStats = new GameStats();

    private final Menu menu;
    private final GameOverScreen gameOverScreen;
    private GameRenderer gameRenderer;

    private int selectedOptionInMenu = 0;
    private String playerName = "Gracz";
    private Grid grid;

    public GamePanel() {
        setPreferredSize(new Dimension(GameRenderer.width, GameRenderer.height + GameRenderer.TILE_SIZE));
        addKeyListener(this);
        addMouseWheelListener(this);
        setFocusable(true);

        menu = new Menu();
        gameOverScreen = new GameOverScreen();

        menu.loadTitleScreen();
        gameOverScreen.loadGameOverImage();
    }


    private void initializeGame() {
        try {
            gameStats = new GameStats();
            grid = new Grid();
            gameRenderer = new GameRenderer(grid, gameStats);
            new BrickWalls(grid);
            new Stones(grid);
            enemies = new Enemies(grid, gameStats);
            player = new Player(gameStats, grid, enemies);
        } catch (IOException e) {
            System.out.println("Błąd plików gry");
        }
        gameStats.setPlayerName(playerName);
        bomb = player.getBomb();
        setBackground(new Color(1, 149, 1));
        int DELAY = 20;
        timer = new Timer(DELAY, this);
    }


    private void drawGame() {
        initializeGame();
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameStats.isGameOver()) {
            gameOverScreen.drawGameOverScreen(g, this, gameStats);
        } else {
            if (selectedOptionInMenu == -1) {
                gameRenderer.drawGame(g, this, player, bomb, enemies);
                gameRenderer.drawScoreboard(g);
            } else {
                menu.drawMenu(g, this, selectedOptionInMenu);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (player != null) {
            player.tick();
            enemies.tick();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameStats.isGameOver()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                timer.stop();
                selectedOptionInMenu = 0;
                resetGame();
                repaint();
            }
        } else if (selectedOptionInMenu == -1) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                timer.stop();
                selectedOptionInMenu = 0;
                resetGame();
                repaint();
            } else if (player != null) {
                player.keyPressed(e);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (selectedOptionInMenu) {
                case 0:
                    selectedOptionInMenu = -1;
                    drawGame();
                    break;
                case 1:
                    playerName = JOptionPane.showInputDialog(this, "Podaj nazwę gracza");
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } else {
            selectOnTitle(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (player != null) {
            player.keyReleased(e);
        }
    }

    private void resetGame() {
        player = null;
        enemies = null;
        bomb = null;
        grid = new Grid();
        gameRenderer.setGrid(grid);
        gameStats.setGameOver(false);
    }


    private void selectOnTitle(int keyCode) {
        if (keyCode == KeyEvent.VK_UP && selectedOptionInMenu > 0) {
            selectedOptionInMenu--;
            repaint();
        }
        if (keyCode == KeyEvent.VK_DOWN && selectedOptionInMenu < 2) {
            selectedOptionInMenu++;
            repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() <0 && selectedOptionInMenu > 0) {
            selectedOptionInMenu--;
            repaint();
        }
        if (e.getWheelRotation() >0 && selectedOptionInMenu < 2) {
            selectedOptionInMenu++;
            repaint();
        }
    }
}
