package pl.alergeek;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.alergeek.model.implementation.Day2;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Day2Test {

    public static final String FILENAME_TASK1 = "/day2-task1-test.txt";
    public static final String FILENAME_TASK2 = "/day2-task2-test.txt";

    private static Day2.Task1 task1;
    private static Day2.Task2 task2;

    @BeforeAll
    public static void setup() {
        task1 = new Day2.Task1();
        task2 = new Day2.Task2();
    }

    @Test
    void shouldPassWithOfficialResults_test1() {
        InputStream file = getClass().getResourceAsStream(FILENAME_TASK1);
        task1.setFile(file);

        String solve = task1.solve();
        int expected = 8;
        assertEquals(String.valueOf(expected), solve);
    }

    @Test
    void shouldFailWithWrongResults_test1() {
        InputStream file = getClass().getResourceAsStream(FILENAME_TASK1);
        task1.setFile(file);

        String solve = task1.solve();
        int expected = 10;
        assertNotEquals(String.valueOf(expected), solve);
    }

//    @Test
//    void shouldPassWithOfficialResults_test2() {
//        InputStream file = getClass().getResourceAsStream(FILENAME_TASK2);
//        task2.setFile(file);
//
//        String solve = task2.solve();
//        assertEquals(String.valueOf(281 + 86 + 78), solve);
//    }
//
//    @Test
//    void shouldFailWithWrongResults_test2() {
//        InputStream file = getClass().getResourceAsStream(FILENAME_TASK2);
//        task2.setFile(file);
//
//        String solve = task2.solve();
//        assertNotEquals(String.valueOf(2810), solve);
//    }
}