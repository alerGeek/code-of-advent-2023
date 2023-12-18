package pl.alergeek.model.implementation;

import pl.alergeek.model.Day;
import pl.alergeek.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static pl.alergeek.model.implementation.Day2.Task1.*;


public class Day2 implements Day {
    private static final String FILENAME = "/day2.txt";
    private final Task task1 = new Task1();
    private final Task task2 = new Task2();

    @Override
    public String task1() {
        return task1.solve();
    }

    @Override
    public String task2() {
        return task2.solve();
    }

    public static class Task1 implements Task {

        private InputStream file;

        public static final int BLUE_MAX_AMOUNT = 14;
        public static final int GREEN_MAX_AMOUNT = 13;
        public static final int RED_MAX_AMOUNT = 12;

        public Task1() {
            this.file = getClass().getResourceAsStream(FILENAME);
        }

        @Override
        public String solve() {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file))) {
                int sum = bufferedReader.lines()
                        .map(Game::from)
                        .filter(Game::validate)
                        .mapToInt(game -> Integer.parseInt(game.id().replaceAll("[^\\d]", "")))
                        .sum();
                return String.valueOf(sum);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void setFile(InputStream file) {
            this.file = file;
        }
    }

    public static class Task2 implements Task {

        private InputStream file;

        public Task2() {
            this.file = getClass().getResourceAsStream(FILENAME);
        }

        @Override
        public String solve() {
            return "";
        }

        @Override
        public void setFile(InputStream file) {
            this.file = file;
        }
    }
}

record Game(String id, List<GameSet> setList) {
    public static Game from(String line) {
        int gameSeparatorIndex = line.indexOf(":");
        String id = line.substring(0, gameSeparatorIndex).trim();
        String setsString = line.replace(id + ": ", "");
        List<GameSet> sets = Arrays.stream(setsString.split(";"))
                .map(GameSet::from)
                .toList();
        return new Game(id, sets);
    }

    static boolean validate(Game game) {
        boolean result =
//                game.setList.size() <= 3 &&
                game.setList().stream().allMatch(set -> {
                    boolean redOK = set.reds() <= RED_MAX_AMOUNT;
                    boolean greenOK = set.greens() <= GREEN_MAX_AMOUNT;
                    boolean blueOK = set.blues() <= BLUE_MAX_AMOUNT;
                    return redOK && greenOK && blueOK;
                });
        System.out.printf("%s is %s%n", game.id, result);
        return result;
    }
}

record GameSet(int reds, int greens, int blues) {
    public static GameSet from(String line) {
        String[] colorsInfo = line.trim().split(",");
        final int[] blue = {0};
        final int[] green = {0};
        final int[] red = {0};
        Arrays.stream(colorsInfo).forEach(color -> {
            blue[0] = color.contains("blue") ? Integer.parseInt(color.replace("blue", "").trim()) : blue[0];
            red[0] = color.contains("red") ? Integer.parseInt(color.replace("red", "").trim()) : red[0];
            green[0] = color.contains("green") ? Integer.parseInt(color.replace("green", "").trim()) : green[0];
        });
        return new GameSet(red[0], green[0], blue[0]);
    }

    static boolean validateSet(GameSet set) {
        boolean result = BLUE_MAX_AMOUNT >= set.blues() &&
                GREEN_MAX_AMOUNT >= set.greens() &&
                RED_MAX_AMOUNT >= set.reds();
//        System.out.printf("%s is %s%n", set.toString(), result);
        return result;
    }
}