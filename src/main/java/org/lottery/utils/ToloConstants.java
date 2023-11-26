package org.lottery.utils;

public class ToloConstants {
    public static final int WIDTH_CUSTOM = 650;
    public static final int HEIGHT_CUSTOM = 400;

    public static final String RULES = """
                Welcome to Bikini Bottom Lottery!
                
                Game Rules:
                
                1. Bet Game:
                   - Choose 4 numbers between 1 and 20.
                   - Place a bet and wait for the draw.
                   - Payout:
                     - 3 correct numbers: 5 times the bet amount.
                     - 4 correct numbers: 50 times the bet amount.
                
                2. Super Bet Game:
                   - Choose 4 numbers between 1 and 20 and a lucky number between 1 and 10.
                   - Place a bet and wait for the draw.
                   - Payout:
                     - No lucky number: Same as Bet Game payout.
                     - With lucky number: 5 times the Bet Game payout.
                """;

    public static final String[] ERROR_MSG = new String[]{"I'm ready, I'm ready!", "Is mayonnaise an instrument?",
            "Firmly grasp it in your hand.", "I wumbo, you wumbo, he-she-me wumbo.", "I'm not a Krusty Krab.",
            "The inner machinations of my mind are an enigma", "No, this is Patrick!", "Ravioli, Ravioli, give me the Formuoli.",
            "I'm ugly and I'm proud."};

    public static final String CURRENCY = "BKN$";
}
