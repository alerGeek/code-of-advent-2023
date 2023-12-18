package pl.alergeek.model.implementation;

import pl.alergeek.model.Day;
import pl.alergeek.model.Task;

import java.util.Arrays;
import java.util.List;

import static pl.alergeek.model.implementation.Day2.Task1.*;


public class Day2 implements Day {
    private final String filename;
    private final Task task1;
    private final Task task2;

    public Day2(String filename) {
        this.filename = filename;
        task1 = new Task1();
        task2 = new Task2();
    }

    // Used by RunCommand.class as part of triggering code using reflection.
    public Day2() {
        this.filename = "/day2.txt";
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

    public class Task1 implements Task {

        public static final int RED_MAX_AMOUNT = 12;
        public static final int GREEN_MAX_AMOUNT = 13;
        public static final int BLUE_MAX_AMOUNT = 14;

        @Override
        public String solve(List<String> input) {
            int sum = input.stream()
                    .map(Game::from)
                    .filter(Game::validate)
                    .mapToInt(game -> Integer.parseInt(game.id().replaceAll("[^\\d]", "")))
                    .sum();
            return String.valueOf(sum);
        }

    }

    public class Task2 implements Task {
        @Override
        public String solve(List<String> input) {
            int powerSum = input.stream()
                    .map(Game::from)
                    .map(Game::setList)
                    .mapToInt(Game::calculatePower)
                    .sum();
            return String.valueOf(powerSum);
        }
    }

    private record Game(String id, List<CubesSet> setList) {
        public static Game from(String line) {
            int gameSeparatorIndex = line.indexOf(":");
            String id = line.substring(0, gameSeparatorIndex).trim();
            String setsString = line.replace(id + ": ", "");
            List<CubesSet> sets = Arrays.stream(setsString.split(";"))
                    .map(CubesSet::from)
                    .toList();
            return new Game(id, sets);
        }

        static boolean validate(Game game) {
            return game.setList().stream().allMatch(CubesSet::validate);
        }

        static int calculatePower(List<CubesSet> cubesSets) {
            int blue = cubesSets.stream().mapToInt(CubesSet::blues).max().orElse(0);
            int red = cubesSets.stream().mapToInt(CubesSet::reds).max().orElse(0);
            int green = cubesSets.stream().mapToInt(CubesSet::greens).max().orElse(0);
            return blue * red * green;
        }
    }

    private record CubesSet(int reds, int greens, int blues) {

        public static CubesSet from(String line) {
            String[] colorsInfo = line.trim().split(",");
            final int[] blue = {0};
            final int[] green = {0};
            final int[] red = {0};
            Arrays.stream(colorsInfo).forEach(color -> {
                blue[0] = color.contains("blue") ? Integer.parseInt(color.replace("blue", "").trim()) : blue[0];
                red[0] = color.contains("red") ? Integer.parseInt(color.replace("red", "").trim()) : red[0];
                green[0] = color.contains("green") ? Integer.parseInt(color.replace("green", "").trim()) : green[0];
            });
            return new CubesSet(red[0], green[0], blue[0]);
        }

        static boolean validate(CubesSet cubes) {
            return BLUE_MAX_AMOUNT >= cubes.blues() &&
                    GREEN_MAX_AMOUNT >= cubes.greens() &&
                    RED_MAX_AMOUNT >= cubes.reds();
        }
    }
}