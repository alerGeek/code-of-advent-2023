package pl.alergeek.model.implementation;

import pl.alergeek.model.Day;
import pl.alergeek.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day1 implements Day {
    private static final String FILENAME = "/day1.txt";
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

        public Task1() {
            this.file = getClass().getResourceAsStream(FILENAME);
        }

        @Override
        public String solve() {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.file))) {
                int result = bufferedReader.lines()
                        .mapToInt(line -> {
                            line = line.replaceAll("[^\\d]", "");
                            char[] charArray = line.toCharArray();
                            char firstDigit = charArray[0];
                            char lastDigit = charArray[charArray.length - 1];
                            return Integer.parseInt("%s%s".formatted(firstDigit, lastDigit));
                        })
                        .sum();
                return String.valueOf(result);
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
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.file))) {
                int result = bufferedReader.lines()
                        .mapToInt(line -> {
                            final StringBuilder input = new StringBuilder(line);
                            StringBuilder regexBuilder = new StringBuilder("one|two|three|four|five|six|seven|eight|nine");

                            String regex = regexBuilder.toString();
                            String firstDigit = findFirstDigit("%s%s".formatted("[0-9]|", regex), input, false);

                            StringBuilder inputReversed = input.reverse();
                            String reversedRegex = regexBuilder.reverse().toString();
                            String lastDigit = findFirstDigit("%s%s".formatted("[0-9]|", reversedRegex), inputReversed, true);
                            return Integer.parseInt("%s%s".formatted(firstDigit, lastDigit));
                        })
                        .sum();
                return String.valueOf(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private String findFirstDigit(String regex, StringBuilder input, boolean isReversed) {
            Map<String, String> digitsWordsMap = createDigitWordsMap();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String firstDigit = matcher.group(0);
                if (firstDigit.matches("\\d")) {
                    return firstDigit;
                }
                if (isReversed) {
                    firstDigit = new StringBuilder(firstDigit).reverse().toString();
                }
                return digitsWordsMap.get(firstDigit);
            }
            return "";
        }

        private Map<String, String> createDigitWordsMap() {
            Map<String, String> digitsWordsMap = new HashMap<>();
            digitsWordsMap.put("one", "1");
            digitsWordsMap.put("two", "2");
            digitsWordsMap.put("three", "3");
            digitsWordsMap.put("four", "4");
            digitsWordsMap.put("five", "5");
            digitsWordsMap.put("six", "6");
            digitsWordsMap.put("seven", "7");
            digitsWordsMap.put("eight", "8");
            digitsWordsMap.put("nine", "9");
            return digitsWordsMap;
        }

        @Override
        public void setFile(InputStream file) {
            this.file = file;
        }
    }
}
