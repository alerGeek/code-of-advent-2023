package pl.alergeek.codeofadvent.day1;

import pl.alergeek.model.Task;

import java.util.List;

public class Task1 implements Task {

    @Override
    public String solve(List<String> input) {
        int result = input.stream()
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
