package pl.alergeek.model.implementation;

import pl.alergeek.model.Day;
import pl.alergeek.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public class Task1 implements Task {

        @Override
        public String solve(List<String> input) {
            List<String> lines = FileReader.readFile(filename);
            int result = lines.stream()
                    .mapToInt(line -> {
                        line = line.replaceAll("[^\\d]", "");
                        if (!line.isEmpty()) {
                            char[] charArray = line.toCharArray();
                            char firstDigit = charArray[0];
                            char lastDigit = charArray[charArray.length - 1];
                            return Integer.parseInt("%s%s".formatted(firstDigit, lastDigit));
                        }
                        return 0;
                    })
                    .sum();
            return String.valueOf(result);
        }
    }

    public class Task2 implements Task {
        @Override
        public String solve(List<String> input) {
            int result = input.stream()
                    .mapToInt(line -> {
                        final StringBuilder sb = new StringBuilder(line);
                        StringBuilder regexBuilder = new StringBuilder("one|two|three|four|five|six|seven|eight|nine");

                        String regex = regexBuilder.toString();
                        String firstDigit = findFirstDigit("%s%s".formatted("[0-9]|", regex), sb, false);

                        StringBuilder inputReversed = sb.reverse();
                        String reversedRegex = regexBuilder.reverse().toString();
                        String lastDigit = findFirstDigit("%s%s".formatted("[0-9]|", reversedRegex), inputReversed, true);
                        return Integer.parseInt("%s%s".formatted(firstDigit, lastDigit));
                    })
                    .sum();
            return String.valueOf(result);
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
    }
}
