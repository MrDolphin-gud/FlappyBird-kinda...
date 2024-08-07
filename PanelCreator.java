import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PanelCreator extends Thread {
    JPanel desk;
    Window window;
    JLabel score;
    public PanelCreator(JPanel desk, Window window, JLabel score) {
        this.desk = desk;
        this.window = window;
        this.score = score;
    }

    public void run() {
        while (!window.gameOver) {
            Random rand = new Random();
            int random=rand.nextInt(200);
            JPanel panelTop = new JPanel();
            JPanel panelBottom = new JPanel();
            panelTop.setBounds(500, 0, 30, random);
            panelBottom.setBounds(500, 250, 30, random+500);
            panelTop.setBackground(Color.green);
            panelBottom.setBackground(Color.green);
            desk.add(panelTop);
            desk.add(panelBottom);
            PanelMovement panelMovement = new PanelMovement(desk, panelTop, panelBottom, window, score);
            panelMovement.start();
            try {
                Thread.sleep(2000); // Delay between obstacles
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
