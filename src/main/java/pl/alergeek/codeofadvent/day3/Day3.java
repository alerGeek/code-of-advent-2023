package pl.alergeek.codeofadvent.day3;

import pl.alergeek.model.Day;
import pl.alergeek.model.Task;
import pl.alergeek.model.utils.FileReader;

import java.util.Arrays;


public class Day3 implements Day {
    private final String filename;
    private final Task task1;
    private final Task task2;

    public Day3(String filename) {
        this.filename = filename;
        task1 = new Task1();
        task2 = new Task2();
    }

    // Used by RunCommand.class as part of triggering code using reflection.
    public Day3() {
        this.filename = "/day3.txt";
        task1 = new Task1();
        task2 = new Task2();
    }

    @Override
    public String task1() {
        return task1.solve(FileReader.readFile(filename));
    }

    @Override
    public String task2() {
        return task2.solve(FileReader.readFile(filename));
    }

    public String task1(String input) {
        return task1.solve(Arrays.asList(input.split("\n")));
    }

    public String task2(String input) {
        return task2.solve(Arrays.asList(input.split("\n")));
    }
}

