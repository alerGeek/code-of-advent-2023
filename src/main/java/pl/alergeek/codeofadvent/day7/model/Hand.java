package pl.alergeek.codeofadvent.day7.model;

import pl.alergeek.codeofadvent.day7.model.types.CardLabel;
import pl.alergeek.codeofadvent.day7.model.types.HandType;

import java.util.List;

public record Hand(List<CardLabel> cards, HandType type) {
    public Hand(List<CardLabel> cards) {
        this(cards, HandType.findHandType(cards));
    }
}
