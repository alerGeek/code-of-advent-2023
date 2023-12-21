package pl.alergeek.codeofadvent.day3;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    public static final String FILENAME_TASK = "/day3-task1-test.txt";

    @Nested
    class Task1Test {
        @Test
        void shouldSumNumbersNextToSymbolFromOfficialInput() {
            Day3 day = new Day3(FILENAME_TASK);

            String solve = day.task1();
            int expected = 4361;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldSumNumbersWhenTheirSymbolsOnTopLine() {
            Day3 day = new Day3();
            String solve = day.task1("""
                    *......*...*
                    .467..114.2.
                    """);
            int expected = 467 + 114 + 2;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldSumNumbersWhenTheirSymbolsOnBottomLine() {
            Day3 day = new Day3();
            String solve = day.task1("""
                    .467..114.2.
                    *......*...*
                    """);

            int expected = 467 + 114 + 2;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldSumNumbersWhenTheirSymbolsInSameLine() {
            Day3 day = new Day3();
            String solve = day.task1("""
                    .467*..*114.*2
                    ..............
                    """);

            int expected = 467 + 114 + 2;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldReturnZeroWhenNoSymbolTouchOnBottom() {
            Day3 day = new Day3();
            String solve = day.task1("""
                    ..467....114.2
                    .......*......
                    """);

            int expected = 0;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldReturnZeroWhenNoSymbolTouchOnTop() {
            Day3 day = new Day3();
            String solve = day.task1("""
                    .......*......
                    ..467....114.2
                    """);

            int expected = 0;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldReturnZeroWhenNoSymbolTouchOnSameLine() {
            Day3 day = new Day3();
            String solve = day.task1("""
                    ..467..*..114.2
                    """);

            int expected = 0;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldMultiplyThenSumNumbersNextToStarSymbolFromOfficialInput() {
            Day3 day = new Day3(FILENAME_TASK);

            String solve = day.task2();
            assertThat(solve).isEqualTo(String.valueOf(467835));
        }

        @Test
        void shouldMultiplyThenSumNumberNextToStarSymbol() {
            Day3 day = new Day3();
            String solve = day.task2("""
                    467..114..
                    ...*......
                    ..35..633.
                    """);
            assertThat(solve).isEqualTo(String.valueOf(467 * 35));
        }

        @Test
        void shouldNotMultiplyWhenSingleNumberNextToStarSymbol() {
            Day3 day = new Day3();
            String solve = day.task2("""
                    467*..114.
                    ..........
                    ..35..633.
                    """);
            assertThat(solve).isEqualTo(String.valueOf(0));
        }

        @Test
        void shouldWronglyMultiplyThenSumNumbersNextToStarSymbolFromOfficialInput() {
            Day3 day = new Day3(FILENAME_TASK);

            String solve = day.task2();
            assertThat(solve).isNotEqualTo(String.valueOf(0));
        }
    }
}