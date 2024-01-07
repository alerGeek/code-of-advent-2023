package pl.alergeek.codeofadvent.day7.services;

import pl.alergeek.codeofadvent.day7.model.Hand;
import pl.alergeek.codeofadvent.day7.model.HandBid;
import pl.alergeek.codeofadvent.day7.model.types.CardLabel;

import java.util.List;

public class HandBidParser {
    public List<HandBid> parse(List<String> input) {
        return input.stream()
                .map(line -> {
                    String[] split = line.split(" ");
                    List<CardLabel> cards = split[0].chars()
                            .mapToObj(c -> (char) c)
                            .map(CardLabel::findBy)
                            .toList();
                    Hand hand = new Hand(cards);
                    int bid = Integer.parseInt(split[1]);
                    return new HandBid(hand, bid);
                })
                .sorted(HandBitComparator.handBidComparator())
                .toList();
    }

    public List<HandBid> parseWithJoker(List<String> input) {
        return input.stream()
                .map(line -> {
                    String[] split = line.split(" ");
                    List<CardLabel> cards = split[0].chars()
                            .mapToObj(c -> (char) c)
                            .map(CardLabel::findBy)
                            .map(JokerProcessor::mapJToJoker)
                            .toList();
                    Hand hand = new Hand(cards);
                    int bid = Integer.parseInt(split[1]);
                    return new HandBid(hand, bid);
                })
                .map(JokerProcessor::processJokerRules)
                .sorted(HandBitComparator.handBidComparator())
                .toList();
    }
}
