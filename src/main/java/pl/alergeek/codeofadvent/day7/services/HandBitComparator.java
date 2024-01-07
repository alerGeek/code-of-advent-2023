package pl.alergeek.codeofadvent.day7.services;

import pl.alergeek.codeofadvent.day7.model.Hand;
import pl.alergeek.codeofadvent.day7.model.HandBid;
import pl.alergeek.codeofadvent.day7.model.types.CardLabel;
import pl.alergeek.codeofadvent.day7.model.types.HandType;

import java.util.Comparator;
import java.util.List;

public class HandBitComparator {
    private HandBitComparator() {
    }

    public static Comparator<HandBid> handBidComparator() {
        return (o1, o2) -> compare(o1.hand(), o2.hand());
    }

    private static int compare(Hand handSorted1, Hand handSorted2) {
        List<CardLabel> cards1 = handSorted1.cards();
        List<CardLabel> cards2 = handSorted2.cards();

        int compareType = Comparator.comparing(Hand::type, Comparator.comparingInt(HandType::getStrength))
                .compare(handSorted1, handSorted2);
        if (compareType != 0) {
            return compareType;
        }

        int result = 1;
        for (int i = 0; i < cards1.size(); i++) {
            int strength1 = cards1.get(i).getStrength();
            int strength2 = cards2.get(i).getStrength();
            if (strength1 == strength2) {
                continue;
            }

            if (strength1 > strength2) {
                return result;
            } else {
                return -result;
            }
        }
        return result;
    }
}