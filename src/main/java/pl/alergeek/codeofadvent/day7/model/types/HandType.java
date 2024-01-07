package pl.alergeek.codeofadvent.day7.model.types;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum HandType {
    FIVE(7),
    FOUR(6),
    FULL(5),
    THREE(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1),
    NOT_IMPLEMENTED(0);

    private final int strength;

    HandType(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public static HandType findHandType(List<CardLabel> cards) {
        Map<String, Long> cardCountMap = mapToCardNameCountMap(cards);
        List<Integer> values = cardCountMap.values().stream()
                .mapToInt(Long::intValue)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toList();
        if (isFive(values)) {
            return FIVE;
        } else if (isFour(values)) {
            return FOUR;
        } else if (isFull(values)) {
            return FULL;
        } else if (isThree(values)) {
            return THREE;
        } else if (isTwoPair(values)) {
            return TWO_PAIR;
        } else if (isPair(values)) {
            return ONE_PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    public HandType levelUp() {
        if (this.equals(HIGH_CARD)) {
            return ONE_PAIR;
        }
        if (this.equals(ONE_PAIR)) {
            return THREE;
        }
        if (this.equals(TWO_PAIR)) {
            return FULL;
        }
        if (this.equals(THREE) || this.equals(FULL)) {
            return FOUR;
        }
        if (this.equals(FOUR)) {
            return FIVE;
        }
        if (this.equals(FIVE)) {
            return FIVE;
        }
        return NOT_IMPLEMENTED;
    }

    private static boolean isFive(List<Integer> values) {
        return values.stream().findFirst().orElse(-1).equals(5);
    }

    private static boolean isFour(List<Integer> values) {
        return values.stream().findFirst().orElse(-1).equals(4);
    }

    private static boolean isFull(List<Integer> values) {
        return isThree(values) && isPair(values);
    }

    private static boolean isThree(List<Integer> values) {
        return values.stream().filter(aLong -> aLong.equals(3)).count() == 1;
    }

    private static boolean isTwoPair(List<Integer> values) {
        return values.stream().filter(aLong -> aLong.equals(2)).count() == 2;
    }

    private static boolean isPair(List<Integer> values) {
        return values.stream().filter(aLong -> aLong.equals(2)).count() == 1;
    }

    public static Map<String, Long> mapToCardNameCountMap(List<CardLabel> cards) {
        return cards.stream().sorted()
                .collect(Collectors.groupingBy(CardLabel::name, Collectors.counting()));
    }
}
