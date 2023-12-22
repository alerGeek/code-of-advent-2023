package pl.alergeek.codeofadvent.day1;

import pl.alergeek.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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