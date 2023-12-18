package pl.alergeek;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.alergeek.model.implementation.Day1;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Day1Test {

    public static final String FILENAME_TASK1 = "/day1-task1-test.txt";
    public static final String FILENAME_TASK2 = "/day1-task2-test.txt";

    private static Day1.Task1 task1;
    private static Day1.Task2 task2;

    @BeforeAll
    public static void setup() {
        task1 = new Day1.Task1();
        task2 = new Day1.Task2();
    }

    @Test
    void shouldPassWithOfficialResults_test1() {
        InputStream file = getClass().getResourceAsStream(FILENAME_TASK1);
        task1.setFile(file);

        String solve = task1.solve();
        int expected = 12 + 38 + 15 + 77;
        assertEquals(String.valueOf(expected), solve);
    }

    @Test
    void shouldFailWithWrongResults_test1() {
        InputStream file = getClass().getResourceAsStream(FILENAME_TASK1);
        task1.setFile(file);

        String solve = task1.solve();
        int expected = 12 + 38 + 5 + 77;
        assertNotEquals(String.valueOf(expected), solve);
    }

    @Test
    void shouldPassWithOfficialResults_test2() {
        InputStream file = getClass().getResourceAsStream(FILENAME_TASK2);
        task2.setFile(file);

        String solve = task2.solve();
        assertEquals(String.valueOf(281 + 86 + 78), solve);
    }

    @Test
    void shouldFailWithWrongResults_test2() {
        InputStream file = getClass().getResourceAsStream(FILENAME_TASK2);
        task2.setFile(file);

        String solve = task2.solve();
        assertNotEquals(String.valueOf(2810), solve);
    }
}