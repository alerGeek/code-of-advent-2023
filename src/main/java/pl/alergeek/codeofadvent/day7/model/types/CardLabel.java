package pl.alergeek.codeofadvent.day7.model.types;

import java.util.Arrays;

public enum CardLabel {
    CARD_2('2', 1),
    CARD_3('3', 2),
    CARD_4('4', 3),
    CARD_5('5', 4),
    CARD_6('6', 5),
    CARD_7('7', 6),
    CARD_8('8', 7),
    CARD_9('9', 8),
    CARD_T('T', 9),
    CARD_J('J', 10),
    CARD_JOKER('J', 0),
    CARD_Q('Q', 11),
    CARD_K('K', 12),
    CARD_A('A', 13);

    private final char symbol;
    private final int strength;

    CardLabel(char symbol, int strength) {
        this.symbol = symbol;
        this.strength = strength;
    }

    public int getStrength() {
        return this.strength;
    }

    public static CardLabel findBy(char symbol) {
        return Arrays.stream(values())
                .filter(cardLabel -> symbol == cardLabel.symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Card with this symbol has not be found."));
    }
}
