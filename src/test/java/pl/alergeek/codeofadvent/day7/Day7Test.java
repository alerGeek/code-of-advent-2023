package pl.alergeek.codeofadvent.day7;

import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.alergeek.codeofadvent.day7.model.Hand;
import pl.alergeek.codeofadvent.day7.model.HandBid;
import pl.alergeek.codeofadvent.day7.services.HandBidParser;
import pl.alergeek.codeofadvent.day7.services.HandBitComparator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.alergeek.codeofadvent.day7.model.types.CardLabel.*;
import static pl.alergeek.codeofadvent.day7.model.types.HandType.*;

public class Day7Test {
    public static final String FILENAME_TASK = "/day7-test.txt";
    private final Hand HAND_ONE_PAIR_3 = new Hand(List.of(CARD_3, CARD_2, CARD_T, CARD_3, CARD_K));
    private final Hand HAND_THREE_5 = new Hand(List.of(CARD_T, CARD_5, CARD_5, CARD_J, CARD_5));
    private final Hand HAND_TWO_PAIR_K7 = new Hand(List.of(CARD_K, CARD_K, CARD_6, CARD_7, CARD_7));
    private final Hand HAND_TWO_PAIR_JT = new Hand(List.of(CARD_K, CARD_T, CARD_J, CARD_J, CARD_T));
    private final Hand HAND_THREE_Q = new Hand(List.of(CARD_Q, CARD_Q, CARD_Q, CARD_J, CARD_A));
    private static final Hand HAND_TWO_PAIR_JOKER_T = new Hand(List.of(CARD_K, CARD_T, CARD_JOKER, CARD_JOKER, CARD_T), FOUR);
    private final Hand HAND_THREE_5_JOKER = new Hand(List.of(CARD_T, CARD_5, CARD_5, CARD_JOKER, CARD_5), FOUR);
    private static final Hand HAND_THREE_Q_JOKER = new Hand(List.of(CARD_Q, CARD_Q, CARD_Q, CARD_JOKER, CARD_A), FOUR);

    @Nested
    class ParserTest {
        @Test
        void shouldParseStringIntoSortedHandBids() {
            String input = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                    """;
            List<HandBid> handBid = new HandBidParser().parse(input.lines().toList());
            assertThat(handBid).asList().hasSize(5);

            ListAssert<HandBid> assertSorting = assertThat(handBid)
                    .isSortedAccordingTo(HandBitComparator.handBidComparator());
            assertSorting.element(0).isEqualTo(new HandBid(HAND_ONE_PAIR_3, 765));
            assertSorting.element(1).isEqualTo(new HandBid(HAND_TWO_PAIR_JT, 220));
            assertSorting.element(2).isEqualTo(new HandBid(HAND_TWO_PAIR_K7, 28));
            assertSorting.element(3).isEqualTo(new HandBid(HAND_THREE_5, 684));
            assertSorting.element(4).isEqualTo(new HandBid(HAND_THREE_Q, 483));
        }

        @Test
        void shouldParseStringWithJokerIntoSortedHandBids() {
            String input = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                    """;
            List<HandBid> handBid = new HandBidParser().parseWithJoker(input.lines().toList());
            assertThat(handBid).asList().hasSize(5);

            ListAssert<HandBid> assertSorting = assertThat(handBid)
                    .isSortedAccordingTo(HandBitComparator.handBidComparator());
            assertSorting.element(0).isEqualTo(new HandBid(HAND_ONE_PAIR_3, 765));
            assertSorting.element(1).isEqualTo(new HandBid(HAND_TWO_PAIR_K7, 28));
            assertSorting.element(2).isEqualTo(new HandBid(HAND_THREE_5_JOKER, 684));
            assertSorting.element(3).isEqualTo(new HandBid(HAND_THREE_Q_JOKER, 483));
            assertSorting.element(4).isEqualTo(new HandBid(HAND_TWO_PAIR_JOKER_T, 220));
        }

        @Test
        void shouldCorrectlyProcessJokerRules() {
            List<HandBid> element1 = new HandBidParser().parseWithJoker("AQJAQ 765".lines().toList());
            assertThat(element1).containsExactly(new HandBid(
                    new Hand(List.of(CARD_A, CARD_Q, CARD_JOKER, CARD_A, CARD_Q), FULL), 765));

            List<HandBid> element2 = new HandBidParser().parseWithJoker("JJJJJ 765".lines().toList());
            assertThat(element2).containsExactly(new HandBid(
                    new Hand(List.of(CARD_JOKER, CARD_JOKER, CARD_JOKER, CARD_JOKER, CARD_JOKER), FIVE), 765));


            List<HandBid> element3 = new HandBidParser().parseWithJoker("AAAAJ 765".lines().toList());
            assertThat(element3).containsExactly(new HandBid(
                    new Hand(List.of(CARD_A, CARD_A, CARD_A, CARD_A, CARD_JOKER), FIVE), 765));


            List<HandBid> element4 = new HandBidParser().parseWithJoker("AAAJJ 765".lines().toList());
            assertThat(element4).containsExactly(new HandBid(
                    new Hand(List.of(CARD_A, CARD_A, CARD_A, CARD_JOKER, CARD_JOKER), FIVE), 765));

            List<HandBid> element5 = new HandBidParser().parseWithJoker("AAJJJ 765".lines().toList());
            assertThat(element5).containsExactly(new HandBid(
                    new Hand(List.of(CARD_A, CARD_A, CARD_JOKER, CARD_JOKER, CARD_JOKER), FIVE), 765));

            List<HandBid> element6 = new HandBidParser().parseWithJoker("AJJJJ 765".lines().toList());
            assertThat(element6).containsExactly(new HandBid(
                    new Hand(List.of(CARD_A, CARD_JOKER, CARD_JOKER, CARD_JOKER, CARD_JOKER), FIVE), 765));

            List<HandBid> element7 = new HandBidParser().parseWithJoker("AAAJQ 765".lines().toList());
            assertThat(element7).containsExactly(new HandBid(
                    new Hand(List.of(CARD_A, CARD_A, CARD_A, CARD_JOKER, CARD_Q), FOUR), 765));

        }
    }

    @Nested
    class Task1Test {

        @Test
        void shouldCalculateTotalWinningAsFromOfficialInput() {
            String input = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                    """;
            Day7 day7 = new Day7();
            String total = day7.task1(input);
            assertThat(total).isEqualTo("6440");
        }

        @Test
        void shouldCalculateRanksWithOrder() {
            String input = """
                    QQQQQ 97
                    JJJJJ 98
                    KKKKK 99
                    AAAAA 100
                    TTTTT 96
                    """;


            Day7 day7 = new Day7();
            String total = day7.task1(input);
            assertThat(total).isEqualTo(String.valueOf(96 + 2 * 98 + 3 * 97 + 4 * 99 + 5 * 100));
        }

        @Test
        void shouldCalculateTotalWinningAsFromOfficialInputFile() {
            Day7 day7 = new Day7(FILENAME_TASK);
            String total = day7.task1();
            assertThat(total).isEqualTo("6440");
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldCalculateTotalWinningAsFromOfficialInput() {
            String input = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                    """;

            Day7 day7 = new Day7();
            String total = day7.task2(input);
            assertThat(total).isEqualTo("5905");

            String element1 = "32T3K 765";
            assertThat(day7.task2(element1)).isEqualTo(String.valueOf(765));
            String element2 = """
                    32T3K 765
                    T55J5 684
                    """;
            assertThat(day7.task2(element2)).isEqualTo(String.valueOf(765
                                                                      + 2 * 684));
            String element3 = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    """;
            assertThat(day7.task2(element3)).isEqualTo(String.valueOf(765
                                                                      + 2 * 28
                                                                      + 3 * 684));
            String element4 = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    """;
            assertThat(day7.task2(element4)).isEqualTo(String.valueOf(765
                                                                      + 2 * 28
                                                                      + 3 * 684
                                                                      + 4 * 220));
            String element5 = """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                    """;
            assertThat(day7.task2(element5)).isEqualTo(String.valueOf(765
                                                                      + 2 * 28
                                                                      + 3 * 684
                                                                      + 4 * 483
                                                                      + 5 * 220));
        }
    }
}
