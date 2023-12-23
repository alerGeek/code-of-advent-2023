package pl.alergeek.codeofadvent.day4.services;

import pl.alergeek.codeofadvent.day4.model.Card;

import java.util.List;

public class CardService {
    CardRepository cardRepository = new CardRepository();

    public List<Integer> findCommonNumbers(Card card) {
        return card.winningNumbers().stream()
                .filter(winning -> card.yourNumbers().contains(winning))
                .distinct()
                .toList();
    }

    public Integer countCommonNumbers(Card card) {
        return findCommonNumbers(card).size();
    }

    public Integer calculateCardPoints(Card card) {
        List<Integer> commonNumbers = findCommonNumbers(card);
        int size = commonNumbers.size();
        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return 1;
        } else {
            return (int) Math.pow(2, size - 1);
        }
    }
}
