package pl.alergeek.codeofadvent.day4;

import pl.alergeek.codeofadvent.day4.model.Card;
import pl.alergeek.codeofadvent.day4.services.CardParser;
import pl.alergeek.codeofadvent.day4.services.CardService;
import pl.alergeek.model.Task;

import java.util.List;

public class Task1 implements Task {
    final CardService cardService = new CardService();

    @Override
    public String solve(List<String> input) {
        List<Card> cards = CardParser.parse(input);
        int sum = cards.stream()
                .mapToInt(cardService::calculateCardPoints)
                .sum();

        return String.valueOf(sum);
    }
}
