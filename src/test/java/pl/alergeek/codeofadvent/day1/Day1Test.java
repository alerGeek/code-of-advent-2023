package pl.alergeek.codeofadvent.day1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    public static final String FILENAME_TASK1 = "/day1-task1-test.txt";
    public static final String FILENAME_TASK2 = "/day1-task2-test.txt";

    @Nested
    class Task1Test {
        @Test
        void shouldPassWithOfficialInput_task1() {
            Day1 day = new Day1(FILENAME_TASK1);

            String solve = day.task1();
            int expected = 12 + 38 + 15 + 77;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldFailWithWrongResults_task1() {
            Day1 day1 = new Day1(FILENAME_TASK1);

            String solve = day1.task1();
            int expected = 12 + 38 + 5 + 77;
            assertThat(solve).isNotEqualTo(String.valueOf(expected));
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldPassWithOfficialResults_test2() {
            Day1 day1 = new Day1(FILENAME_TASK2);

            String solve = day1.task2();
            assertThat(solve).isEqualTo(String.valueOf(281 + 86 + 78));

        }

        @Test
        void shouldFailWithWrongResults_test2() {
            Day1 day1 = new Day1(FILENAME_TASK2);

            String solve = day1.task2();
            assertThat(solve).isNotEqualTo(String.valueOf(2810));
        }
    }
}