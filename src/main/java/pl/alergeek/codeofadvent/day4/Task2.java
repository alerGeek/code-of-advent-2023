package pl.alergeek.codeofadvent.day4;

import pl.alergeek.codeofadvent.day4.model.Card;
import pl.alergeek.codeofadvent.day4.services.CardParser;
import pl.alergeek.codeofadvent.day4.services.CardRepository;
import pl.alergeek.codeofadvent.day4.services.CardService;
import pl.alergeek.model.Task;

import java.util.List;
import java.util.Optional;

public class Task2 implements Task {
    final CardRepository cardRepository = new CardRepository();
    final CardService cardService = new CardService();

    @Override

    public String solve(List<String> input) {
        List<Card> cards = CardParser.parse(input);
        cardRepository.addAll(cards);

        cardRepository.findAll()
                .forEach(card -> {
                    Integer countCommon = cardService.countCommonNumbers(card);
                    int duplicationNumbers = cardRepository.countAllByName(card.name());
                    Optional<Card> tmpCard = Optional.ofNullable(card);
                    while (countCommon >= 1) {
                        Optional<Card> nextCard = cardRepository.findNext(tmpCard);
                        cardRepository.duplicate(nextCard, duplicationNumbers);
                        tmpCard = nextCard;
                        countCommon -= 1;
                    }
                });
        int sum = cardRepository.findAll().size();
        return String.valueOf(sum);
    }
}
