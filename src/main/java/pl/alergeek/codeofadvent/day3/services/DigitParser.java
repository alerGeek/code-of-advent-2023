package pl.alergeek.codeofadvent.day3.services;

import pl.alergeek.codeofadvent.day3.model.DigitPart;
import pl.alergeek.codeofadvent.day3.model.NumberRegion;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DigitParser {
    private Set<DigitPart> digits = new HashSet<>();

    public Set<DigitPart> from(List<String> lines) {
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            char[] charArray = line.toCharArray();
            for (int charIndex = 0; charIndex < charArray.length; charIndex++) {
                char character = charArray[charIndex];
                if (TypeCharChecker.isDigit(character)) {
                    Set<DigitPart> digitsFromLine = fromLine(line, lineIndex, charIndex, character);
                    digits.addAll(digitsFromLine);
                }
            }
        }
        return this.digits;
    }

    public static Set<DigitPart> fromLine(String line, int lineIndex, int charIndex, char c) {
        Set<DigitPart> digits = new HashSet<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String matched = matcher.group(0);
            int regionStart = matcher.start();
            int regionEnd = matcher.end();
            NumberRegion numberRegion = NumberRegion.from(regionStart, regionEnd, matched, lineIndex);
            DigitPart digitPart;
            if (charIndex >= numberRegion.startIndex() && charIndex <= numberRegion.endIndex()) {
                digitPart = new DigitPart(new Point(charIndex, lineIndex), c, numberRegion);
            } else {
                digitPart = new DigitPart(new Point(charIndex, lineIndex), c, null);
            }
            digits.add(digitPart);
        }
        return digits;
    }
}
