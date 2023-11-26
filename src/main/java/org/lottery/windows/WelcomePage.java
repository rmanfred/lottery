package org.lottery.windows;

import org.lottery.utils.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static org.lottery.utils.ToloConstants.*;

public class WelcomePage extends JFrame {

    public WelcomePage(UserInfo userInfo, Point location) {
        setLocation(location);
        setSize(WIDTH_CUSTOM, HEIGHT_CUSTOM);

        JLabel labelWelcome = new JLabel("<html><div style='color: #FEE12B; font-size: 18px; font-weight: bold;'>" +
                "Welcome to Bikini Bottom Lottery! </div></html>");
        labelWelcome.setBounds(130, 140, 230, 80);

        JButton buttonRules = new JButton("Rules");
        buttonRules.setBounds(150, 250, 80, 40);

        buttonRules.addActionListener(e -> {
            new RulesPage(userInfo, this.getLocation());
            dispose();
        });

        JButton buttonPlay = new JButton("Play");
        buttonPlay.setBounds(250, 250, 80, 40);

        ImageIcon meme = new ImageIcon("src/main/images/meme.jpeg");

        buttonPlay.addActionListener(e -> {
            String money = JOptionPane.showInputDialog(null, "Currently you have: " + userInfo.getCurrentAmount() + CURRENCY +
                    "\nEnter amount you wanna add: ");
            int errorCode = new Random().nextInt(ERROR_MSG.length);

            if (money == null || money.isBlank()) {
                JOptionPane.showMessageDialog(null, "Not valid input for money", ERROR_MSG[errorCode], JOptionPane.ERROR_MESSAGE, meme);
            } else {
                try {
                    double moneyToAdd = Double.parseDouble(money);
                    if (userInfo.getInitialAmount() == 0) {
                        userInfo.setInitialAmount(moneyToAdd);
                    }
                    userInfo.setCurrentAmount(userInfo.getCurrentAmount() + moneyToAdd);
                    new PlayPage(userInfo, this.getLocation());
                    dispose();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Not valid input for money", ERROR_MSG[errorCode], JOptionPane.ERROR_MESSAGE, meme);
                }
            }
        });

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setPreferredSize(new Dimension(WIDTH_CUSTOM/2 + 50, HEIGHT_CUSTOM));
        panelMenu.setBackground(new Color(0, 162, 232));

        JPanel panelAnimation = new WelcomePanel();
        panelAnimation.setLayout(new FlowLayout());
        panelAnimation.setPreferredSize(new Dimension(WIDTH_CUSTOM/2 + 50, HEIGHT_CUSTOM));
        panelAnimation.setBackground(new Color(0, 162, 232));

        panelMenu.add(labelWelcome);
        panelMenu.add(buttonRules);
        panelMenu.add(buttonPlay);

        add(panelMenu, BorderLayout.EAST);
        add(panelAnimation, BorderLayout.WEST);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
