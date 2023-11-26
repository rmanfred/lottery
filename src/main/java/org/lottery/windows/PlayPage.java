package org.lottery.windows;

import org.lottery.exceptions.NotCorrectNumbersException;
import org.lottery.exceptions.NotEnoughMoneyException;
import org.lottery.exceptions.NotUniqueSetOfNumbers;
import org.lottery.utils.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.lottery.utils.ToloConstants.*;

public class PlayPage extends JFrame {

    public PlayPage(UserInfo userInfo, Point location) {
        this.setLocation(location);
        setSize(WIDTH_CUSTOM, HEIGHT_CUSTOM);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 162, 232));

        JLabel headerLabel = new JLabel("<html><div style='color: #FEE12B; font-size: 18px; font-weight: bold;'>" +
                "Bikini Bottom Lottery</div></html>");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Number Panel
        JPanel numPanel = new JPanel(new FlowLayout());
        numPanel.setBackground(new Color(147, 112, 219));
        numPanel.setPreferredSize(new Dimension(WIDTH_CUSTOM, HEIGHT_CUSTOM - 100));

        JLabel chooseNumLabel = new JLabel("Enter numbers between 1 and 20:");
        numPanel.add(chooseNumLabel);

        JTextField num1 = new JTextField(2);
        JTextField num2 = new JTextField(2);
        JTextField num3 = new JTextField(2);
        JTextField num4 = new JTextField(2);

        numPanel.add(num1);
        numPanel.add(num2);
        numPanel.add(num3);
        numPanel.add(num4);

        // Current Amount Panel
        JPanel currentAmountPanel = new JPanel();
        currentAmountPanel.setBackground(new Color(0, 162, 232));
        currentAmountPanel.setBounds(30, HEIGHT_CUSTOM / 2, WIDTH_CUSTOM, 100);
        JLabel currentAmount = new JLabel("Current amount of money: " + userInfo.getCurrentAmount() + CURRENCY);
        currentAmountPanel.add(currentAmount);

        JButton mainMenu = new JButton("Main Menu");
        currentAmountPanel.add(mainMenu);

        mainMenu.addActionListener(e -> {
            new WelcomePage(userInfo, getLocation());
            dispose();
        });

        JCheckBox checkBox = new JCheckBox();
        checkBox.setText("SuperBet");
        numPanel.add(checkBox);

        JLabel magicNumLab = new JLabel("Magic num:");
        numPanel.add(magicNumLab);
        JTextField superText = new JTextField(2);
        numPanel.add(superText);

        JLabel chooseMoneyLabel = new JLabel("Enter amount of money you want to play this draw:");
        numPanel.add(chooseMoneyLabel);
        JTextField numMoney = new JTextField(5);
        numPanel.add(numMoney);

        mainPanel.add(numPanel, BorderLayout.WEST);
        mainPanel.add(currentAmountPanel, BorderLayout.SOUTH);

        JButton buttonDraw = new JButton("Draw");
        numPanel.add(buttonDraw);


        ImageIcon meme = new ImageIcon("src/main/images/meme4.jpeg");
        ImageIcon meme1 = new ImageIcon("src/main/images/meme5.jpeg");
        ImageIcon meme2 = new ImageIcon("src/main/images/meme6.jpeg");
        ImageIcon meme3 = new ImageIcon("src/main/images/meme1.jpeg");


        buttonDraw.addActionListener(e -> {
            Set<Integer> setOfNums = new HashSet<>();
            int errorCode = new Random().nextInt(ERROR_MSG.length);

            try {
                int n1 = Integer.parseInt(num1.getText());
                int n2 = Integer.parseInt(num2.getText());
                int n3 = Integer.parseInt(num3.getText());
                int n4 = Integer.parseInt(num4.getText());

                int n5 = Integer.MIN_VALUE;

                if (n1 < 1 || n1 > 20 || n2 < 1 || n2 > 20 || n3 < 1 || n3 > 20 || n4 < 1 || n4 > 20) {
                    throw new NotCorrectNumbersException();
                }

                setOfNums.add(n1);
                setOfNums.add(n2);
                setOfNums.add(n3);
                setOfNums.add(n4);

                if (setOfNums.size() < 4) {
                    throw new NotUniqueSetOfNumbers();
                }

                if (checkBox.isSelected()) {
                    n5 = Integer.parseInt(superText.getText());
                    if (n5 < 1 || n5 > 10) {
                        throw new NotCorrectNumbersException();
                    }
                    if (setOfNums.contains(n5)) {
                        throw new NotUniqueSetOfNumbers();
                    }
                }

                double moneyEntered = Double.parseDouble(numMoney.getText());
                if (moneyEntered > userInfo.getCurrentAmount()) {
                    throw new NotEnoughMoneyException();
                }
                double cAmount = userInfo.getCurrentAmount();
                cAmount -= moneyEntered;

                double gainedMoney = getGain(setOfNums, n5, moneyEntered);
                userInfo.setCurrentAmount(cAmount + gainedMoney);

                JOptionPane.showMessageDialog(null, "You earned: " + gainedMoney + CURRENCY, ERROR_MSG[errorCode], JOptionPane.INFORMATION_MESSAGE);

                new PlayPage(userInfo, getLocation());
                dispose();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Not valid format of numbers entered", ERROR_MSG[errorCode], JOptionPane.ERROR_MESSAGE, meme);
            } catch (NotCorrectNumbersException notCorrectNumbersException) {
                JOptionPane.showMessageDialog(null, "Please, enter numbers from 1 to 20", ERROR_MSG[errorCode], JOptionPane.ERROR_MESSAGE, meme1);
            } catch (NotUniqueSetOfNumbers notUniqueSetOfNumbers) {
                JOptionPane.showMessageDialog(null, "Please, enter distinct numbers", ERROR_MSG[errorCode], JOptionPane.ERROR_MESSAGE, meme2);
            } catch (NotEnoughMoneyException notEnoughMoneyException) {
                JOptionPane.showMessageDialog(null, "Amount of money is not sufficient to play the game", ERROR_MSG[errorCode], JOptionPane.ERROR_MESSAGE, meme3);
            }
        });

        add(mainPanel);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private double getGain(Set<Integer> chosenNumbers, int luckyNum, double bet) {
        double gain = 0;
        Set<Integer> randomNums = new HashSet<>();

        while (randomNums.size() < 4) {
            randomNums.add(new Random().nextInt(20) + 1);
        }

        Set<Integer> commonNumbers = new HashSet<>(randomNums);
        commonNumbers.retainAll(chosenNumbers);

        if (commonNumbers.size() == 3) {
            gain = randomNums.contains(luckyNum) ? bet * 25 : bet * 5;
        } else if (commonNumbers.size() == 4) {
            gain = randomNums.contains(luckyNum) ? bet * 250 : bet * 50;
        }

        return gain;
    }
}
