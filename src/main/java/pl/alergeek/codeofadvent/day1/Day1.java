package pl.alergeek.codeofadvent.day1;

import pl.alergeek.model.Day;
import pl.alergeek.model.Task;
import pl.alergeek.model.utils.FileReader;


public class Day1 implements Day {
    private final String filename;
    private final Task task1;
    private final Task task2;

    public Day1(String filename) {
        this.filename = filename;
        task1 = new Task1();
        task2 = new Task2();
    }

    // Used by RunCommand.class as part of triggering code using reflection.
    public Day1() {
        this.filename = "/day1.txt";
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

}
