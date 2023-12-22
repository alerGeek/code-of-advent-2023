package pl.alergeek.codeofadvent.day3.services;

import pl.alergeek.codeofadvent.day3.model.SymbolPart;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymbolParser {
    private final Set<SymbolPart> symbols = new HashSet<>();

    public Set<SymbolPart> from(List<String> lines) {
        String[] linesArr = lines.toArray(String[]::new);
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = linesArr[lineIndex];
            char[] charArray = line.toCharArray();
            for (int charIndex = 0; charIndex < charArray.length; charIndex++) {
                char currentChar = charArray[charIndex];
                if (TypeCharChecker.isSymbol(currentChar)) {
                    SymbolPart symbolPart = new SymbolPart(new Point(charIndex, lineIndex), currentChar);
                    this.symbols.add(symbolPart);
                }
            }
        }
        return this.symbols;
    }
}
