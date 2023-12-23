package pl.alergeek.codeofadvent.day4.services;

import pl.alergeek.codeofadvent.day4.model.Card;

import java.util.List;
import java.util.stream.Stream;

public class CardParser {

    public static List<Card> parse(List<String> lines) {
        return lines.stream().map(CardParser::parse).toList();
    }

    public static Card parse(String line) {
        String trim = line.trim().replaceAll(" +", " ");
        String[] splitAfterDay = trim.split(": ");
        String day = splitAfterDay[0];
        String numbers = splitAfterDay[1];

        String[] numbersArr = numbers.split(" \\| ");

        List<Integer> winningNumbers = Stream.of(numbersArr[0].split(" ")).mapToInt(Integer::parseInt).boxed().toList();
        List<Integer> yourNumbers = Stream.of(numbersArr[1].split(" ")).mapToInt(Integer::parseInt).boxed().toList();
        return new Card(day, winningNumbers, yourNumbers);
    }
}
