package org.lottery.windows;

import org.lottery.utils.UserInfo;

import javax.swing.*;

import java.awt.*;
import java.util.Random;

import static org.lottery.utils.ToloConstants.*;

public class RulesPage extends JFrame {

    public RulesPage(UserInfo userInfo, Point location) {
        this.setLocation(location);
        setSize(WIDTH_CUSTOM, HEIGHT_CUSTOM);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("<html><div style='color: #FEE12B; font-size: 18px; font-weight: bold;'>" +
                "Bikini Bottom Lottery Rules</div></html>");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(headerLabel, BorderLayout.NORTH);
        panel.setBackground(new Color(0, 162, 232));

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        rulesTextArea.setBackground(new Color(147, 112, 219));

        rulesTextArea.setText(RULES);

        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        Random r = new Random();

        ImageIcon meme = new ImageIcon("src/main/images/meme3.jpeg");

        JButton playNowButton = new JButton("<html><div style='color: #00A2E8; font-size: 18px; font-weight: bold;'>" +
                "Play Now</div></html>");
        playNowButton.addActionListener(e -> {
            String money = JOptionPane.showInputDialog(null, "Currently you have: " + userInfo.getCurrentAmount() + CURRENCY +
                    "\nEnter amount you wanna add: ");
            int errorCode = r.nextInt(ERROR_MSG.length);
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
        panel.add(playNowButton, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
