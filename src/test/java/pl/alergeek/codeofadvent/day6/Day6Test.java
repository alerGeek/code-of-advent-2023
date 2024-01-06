package pl.alergeek.codeofadvent.day6;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.alergeek.codeofadvent.day6.model.RaceRecord;
import pl.alergeek.codeofadvent.day6.model.TimeSheet;
import pl.alergeek.codeofadvent.day6.services.RaceRecordService;
import pl.alergeek.codeofadvent.day6.services.TimesheetMultiplyRacesParser;
import pl.alergeek.codeofadvent.day6.services.TimesheetSingleRaceParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.alergeek.codeofadvent.day6.services.ToyBoatService.calculateDistance;

public class Day6Test {
    public static final String FILENAME_TASK = "/day6-test.txt";

    @Nested
    class ParserTest {
        @Test
        void shouldParseStringIntoTimeSheet() {
            String input = """
                    Time:      7  15   30
                    Distance:  9  40  200
                    """;
            TimeSheet timesheet = new TimesheetMultiplyRacesParser().parse(input.lines().toList());
            assertThat(timesheet.times()).isEqualTo(new long[]{7, 15, 30});
            assertThat(timesheet.distances()).isEqualTo(new long[]{9, 40, 200});
            assertThat(timesheet.records()).asList()
                    .hasSize(3)
                    .containsAll(List.of(new RaceRecord(7L, 9L),
                            new RaceRecord(15L, 40L),
                            new RaceRecord(30L, 200L)));
        }
    }

    @Nested
    class Task1Test {
        @Test
        void shouldRaceDistanceEqualZeroWhenButtonNoPressed() {
            long pressTime = 0;
            long distance = calculateDistance(new RaceRecord(7, 9), pressTime);
            assertThat(distance).isZero();
        }

        @Test
        void shouldRaceDistanceEqualZeroWhenButtonPressedAllTime() {
            long pressTime = 7;
            long distance = calculateDistance(new RaceRecord(7, 9), pressTime);
            assertThat(distance).isZero();
        }

        @Test
        void shouldCalculateDistanceWhenButtonPressed() {
            long pressTime = 1;
            long distance = calculateDistance(new RaceRecord(7, 9), pressTime);
            assertThat(distance).isEqualTo(6);
            long distance1 = calculateDistance(new RaceRecord(15, 40), pressTime);
            assertThat(distance1).isEqualTo(14);
            long distance2 = calculateDistance(new RaceRecord(30, 200), pressTime);
            assertThat(distance2).isEqualTo(29);

            long pressTime1 = 3;
            long distance3 = calculateDistance(new RaceRecord(7, 9), pressTime1);
            assertThat(distance3).isEqualTo(12);
            long distance4 = calculateDistance(new RaceRecord(15, 40), pressTime1);
            assertThat(distance4).isEqualTo(36);
            long distance5 = calculateDistance(new RaceRecord(30, 200), pressTime1);
            assertThat(distance5).isEqualTo(81);
        }

        @Test
        void shouldMarginEqualsToMultiplyPossibleWinsCount() {
            String input = """
                    Time:      7  15   30
                    Distance:  9  40  200
                    """;
            TimeSheet timesheet = new TimesheetMultiplyRacesParser().parse(input.lines().toList());
            assertThat(RaceRecordService.calculateMargin(timesheet.records())).isEqualTo(4 * 8 * 9);
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldMarginEqualsToMultiplyPossibleWinsCount() {
            String input = """
                    Time:      7  15   30
                    Distance:  9  40  200
                    """;
            TimeSheet timesheet = new TimesheetSingleRaceParser().parse(input.lines().toList());
            assertThat(RaceRecordService.calculateMargin(timesheet.records())).isEqualTo(71503);
        }
    }
}
