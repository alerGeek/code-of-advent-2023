package pl.alergeek.codeofadvent.day4.services;

import pl.alergeek.codeofadvent.day4.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepository {
    private final List<Card> cardList = new ArrayList<>();

    public List<Card> findAll() {
        return cardList.stream().toList();
    }

    public Optional<Card> findByName(String name) {
        return cardList.stream()
                .filter(card -> card.name().equals(name))
                .findFirst();
    }

    public List<Card> findAllByName(String name) {
        return cardList.stream()
                .filter(card -> card.name().equals(name))
                .toList();
    }

    public int countAllByName(String name) {
        return Math.toIntExact(findAllByName(name).size());
    }

    public Optional<Card> findNext(Optional<Card> card) {
        if (!card.isPresent()) {
            return Optional.empty();
        }
        return cardList.stream()
                .filter(card1 -> card1.name().endsWith(String.valueOf(card.get().getDay() + 1)))
                .findFirst();
    }

    public void duplicate(Optional<Card> card, int times) {
        if (card.isPresent()) {
            Card duplicate = null;
            for (int i = 0; i < times; i++) {
                try {
                    duplicate = (Card) card.get().clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                this.cardList.add(duplicate);
            }
        }
    }

    public void addAll(List<Card> cards) {
        this.cardList.addAll(cards);
    }
}
