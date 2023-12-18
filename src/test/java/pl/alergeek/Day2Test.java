package pl.alergeek;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.alergeek.model.implementation.Day2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Day2Test {

    public static final String FILENAME_TASK = "/day2-task1-test.txt";

    @Nested
    class Task1Test {
        @Test
        void shouldPassWithOfficialResults_test1() {
            Day2 day = new Day2(FILENAME_TASK);

            String solve = day.task1();
            int expected = 8;
            assertEquals(String.valueOf(expected), solve);
        }

        @Test
        void shouldFailWithWrongResults_test1() {
            Day2 day = new Day2(FILENAME_TASK);

            String solve = day.task1();
            int expected = 10;
            assertNotEquals(String.valueOf(expected), solve);
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldPassWithOfficialResults_test2() {
            Day2 day = new Day2(FILENAME_TASK);

            String solve = day.task2();
            assertEquals(String.valueOf(2286), solve);
        }

        @Test
        void shouldFailWithWrongResults_test2() {
            Day2 day = new Day2(FILENAME_TASK);

            String solve = day.task2();
            assertNotEquals(String.valueOf(22860), solve);
        }
    }
}