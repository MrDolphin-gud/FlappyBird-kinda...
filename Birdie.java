import javax.swing.*;
import java.awt.*;

public class Birdie extends Thread {
    JPanel bird;
    JPanel desk;
    Window window;

    public Birdie(JPanel bird, JPanel desk, Window window) {
        this.bird = bird;
        this.desk = desk;
        this.window = window;
    }

    public void run() {
        while (!window.gameOver) {
            try {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Rectangle bounds = bird.getBounds();
            int Y = bounds.y + 10;
            if ((Y >= 0 && Y <= desk.getHeight() - bounds.height)) {
                bird.setBounds(bounds.x, Y, bounds.width, bounds.height);
                desk.repaint();
            } else {
                window.endGame();
            }
        }
    }
}
