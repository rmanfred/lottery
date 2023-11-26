package org.lottery;

import org.lottery.windows.WelcomePage;
import org.lottery.utils.UserInfo;

import java.awt.*;

/**
 * Tolo game test main method
 */
public class Tolo {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        Point point = new Point(250, 250);
        new WelcomePage(userInfo, point);
    }
}
