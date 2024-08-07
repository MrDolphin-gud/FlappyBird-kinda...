import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

class Window extends JFrame implements KeyListener {
    JPanel desk;
    JPanel bird;
    JLabel score= new JLabel();
    boolean gameOver = false;

    public Window() {
        super("Flappiewhatever");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500, 500);
        setResizable(false);
        desk = new JPanel();
        bird = new JPanel();
        score.setBounds(50, 50, 100, 50);
        score.setForeground(Color.WHITE);
        score.setText("0");
        desk.setLayout(null);
        desk.setBounds(0, 0, 500, 500);
        desk.setBackground(Color.black);
        bird.setBounds(10, 200, 10, 10);
        bird.setBackground(Color.red);
        desk.add(bird);
        desk.add(score);
        add(desk);
        Birdie birdie = new Birdie(bird, desk, this);
        PanelCreator panelCreator = new PanelCreator(desk, this, score);
        addKeyListener(this);
        setVisible(true);
        birdie.start();
        panelCreator.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void movePanel(JPanel panel, int x, int y) {
        Rectangle bounds = panel.getBounds();
        int Y = bounds.y + y;
        int X = bounds.x + x;
        if ((Y >= 0 && Y <= desk.getHeight() - bounds.height) && (X >= 0 && X <= desk.getWidth() - bounds.width)) {
            panel.setBounds(X, Y, bounds.width, bounds.height);
            desk.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                movePanel(bird, 0, -20);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void endGame() {
        gameOver = true;
        JOptionPane.showMessageDialog(this, "Game Over");
    }
}
public class Main {
    public static void main(String[] args) {
        new Window();
    }
}
