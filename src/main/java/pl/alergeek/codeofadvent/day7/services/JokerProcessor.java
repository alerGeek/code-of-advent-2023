package pl.alergeek.codeofadvent.day7.services;

import pl.alergeek.codeofadvent.day7.model.Hand;
import pl.alergeek.codeofadvent.day7.model.HandBid;
import pl.alergeek.codeofadvent.day7.model.types.CardLabel;
import pl.alergeek.codeofadvent.day7.model.types.HandType;

import java.util.List;

import static pl.alergeek.codeofadvent.day7.model.types.CardLabel.CARD_J;
import static pl.alergeek.codeofadvent.day7.model.types.CardLabel.CARD_JOKER;
import static pl.alergeek.codeofadvent.day7.model.types.HandType.findHandType;

public class JokerProcessor {
    private JokerProcessor() {
    }

    static CardLabel mapJToJoker(CardLabel cardLabel) {
        if (cardLabel == CARD_J) {
            return CARD_JOKER;
        }
        return cardLabel;
    }

    static HandBid processJokerRules(HandBid handBid) {
        int jokerCount = getJokerCount(handBid);
        if (jokerCount == 0) {
            return handBid;
        }
        Hand updatedHand = updateHandType(handBid.hand(), jokerCount);
        return new HandBid(updatedHand, handBid.bid());
    }

    private static Hand updateHandType(Hand hand, int jokerCount) {
        List<CardLabel> cards = hand.cards();
        List<CardLabel> cardsWithoutJokers = cards.stream()
                .filter(cardLabel -> !cardLabel.equals(CARD_JOKER))
                .toList();
        HandType updatedType = findHandType(cardsWithoutJokers);
        while (jokerCount > 0) {
            updatedType = updatedType.levelUp();
            jokerCount = jokerCount - 1;
        }
        return new Hand(cards, updatedType);
    }

    private static int getJokerCount(HandBid handBid) {
        return (int) handBid.hand().cards().stream()
                .filter(cardLabel -> cardLabel.equals(CardLabel.CARD_JOKER))
                .count();
    }
}
