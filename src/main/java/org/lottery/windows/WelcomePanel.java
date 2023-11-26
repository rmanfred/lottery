package org.lottery.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.lottery.utils.ToloConstants.HEIGHT_CUSTOM;
import static org.lottery.utils.ToloConstants.WIDTH_CUSTOM;

public class WelcomePanel extends JPanel implements ActionListener {
    private final int WIDTH_1 = WIDTH_CUSTOM/2;
    private final int HEIGHT_1 = HEIGHT_CUSTOM - 150;
    Image imageMain;
    Image imageBackground;
    Timer timer;
    int xVelocity = 1;
    int yVelocity = 1;
    int x = 0;
    int y = 0;

    public WelcomePanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH_1, HEIGHT_1));
        imageMain = new ImageIcon("src/main/images/spong.jpeg").getImage();
        imageBackground = new ImageIcon("src/main/images/play.jpeg").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), null);
        graphics2D.drawImage(imageMain, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x >= WIDTH_CUSTOM/2 + 50 - imageMain.getWidth(null) || x < 0) {
            xVelocity *= -1;
        }
        x += xVelocity;
        if (y >= HEIGHT_CUSTOM - imageMain.getHeight(null) || y < 0) {
            yVelocity *= -1;
        }
        y += yVelocity;
        repaint();
    }
}
