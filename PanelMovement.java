import javax.swing.*;
import java.awt.*;

public class PanelMovement extends Thread {
    JPanel desk;
    JPanel panelTop;
    JPanel panelBottom;
    Window window;
    JLabel score;

    public PanelMovement(JPanel desk, JPanel panelTop, JPanel panelBottom, Window window, JLabel score) {
        this.desk = desk;
        this.panelTop = panelTop;
        this.panelBottom = panelBottom;
        this.window = window;
        this.score = score;
    }

    public void run() {
        while (!window.gameOver && panelTop.getX() > -30) {
            try {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            movePanel(-10);
        }
        if (!window.gameOver) {
            desk.remove(panelTop);
            desk.remove(panelBottom);
            desk.repaint();
            int Score = Integer.parseInt(score.getText());
            Score++;
            score.setText(String.valueOf(Score));
        }
    }

    private void movePanel(int dx) {
        Rectangle rTop = panelTop.getBounds();
        Rectangle rBottom = panelBottom.getBounds();
        int X = rTop.x + dx;
        panelTop.setBounds(X, rTop.y, rTop.width, rTop.height);
        panelBottom.setBounds(X, rBottom.y, rBottom.width, rBottom.height);
        desk.repaint();
        checkCollision();
    }

    private void checkCollision() {
        Rectangle birdBounds = window.bird.getBounds();
        if (birdBounds.intersects(panelTop.getBounds()) || birdBounds.intersects(panelBottom.getBounds())) {
            window.endGame();
        }
    }
}
