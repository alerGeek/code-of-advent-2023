package pl.alergeek.codeofadvent.day3.model;

import pl.alergeek.codeofadvent.day3.services.DigitParser;
import pl.alergeek.codeofadvent.day3.services.SymbolParser;

import java.util.*;
import java.util.function.Predicate;

import static pl.alergeek.codeofadvent.day3.services.PositionRelationChecker.isNextTo;

public class EngineSchematic {
    private final Set<DigitPart> digits;
    private final Set<SymbolPart> symbols;
    private final Map<SymbolPart, Set<NumberRegion>> symbolNumber = new HashMap<>();

    public EngineSchematic(Set<DigitPart> digits, Set<SymbolPart> symbols) {
        this.digits = digits;
        this.symbols = symbols;
    }

    public Set<DigitPart> getDigits() {
        return digits;
    }

    public Set<SymbolPart> getSymbols() {
        return symbols;
    }

    public int sum() {
        createSymbolNumberMap();
        return symbolNumber.values().stream()
                .map(numberRegions -> numberRegions
                        .stream()
                        .map(NumberRegion::number)
                        .mapToInt(Integer::parseInt)
                        .sum())
                .reduce(0, Integer::sum);
    }

    private void createSymbolNumberMap() {
        symbols.forEach(symbol -> this.digits.stream()
                .filter(isDigitWithNumberRegionAndNextToSymbol(symbol))
                .map(DigitPart::numberRegion)
                .forEach(numberRegion -> {
                    if (symbolNumber.containsKey(symbol)) {
                        addToExistingSetForSymbol(symbol, numberRegion);
                    } else {
                        addToNewSetForSymbol(symbol, numberRegion);
                    }
                })
        );
    }

    private void addToNewSetForSymbol(SymbolPart symbol, NumberRegion numberRegion) {
        Set<NumberRegion> set = new HashSet<>();
        set.add(numberRegion);
        symbolNumber.putIfAbsent(symbol, set);
    }

    private void addToExistingSetForSymbol(SymbolPart symbol, NumberRegion numberRegion) {
        var existingSet = symbolNumber.get(symbol);
        existingSet.add(numberRegion);
        symbolNumber.put(symbol, existingSet);
    }

    private static Predicate<DigitPart> isDigitWithNumberRegionAndNextToSymbol(SymbolPart symbol) {
        return digit -> isNextTo(symbol, digit) && digit.numberRegion() != null;
    }

    public int sumGearRation() {
        createSymbolNumberMap();
        return this.symbolNumber.entrySet().stream()
                .filter(isGearSymbol())
                .map(Map.Entry::getValue)
                .map(numberRegions -> numberRegions.stream()
                        .map(NumberRegion::number)
                        .mapToInt(Integer::parseInt)
                        .reduce(1, (left, right) -> {
                            if (numberRegions.size() == 1) {
                                return 0;
                            } else {
                                return left * right;
                            }
                        }))
                .reduce(0, Integer::sum);
    }

    private static Predicate<Map.Entry<SymbolPart, Set<NumberRegion>>> isGearSymbol() {
        return entry -> entry.getKey().getCharacter().equals('*');
    }

    public static EngineSchematic from(List<String> lines) {
        Set<DigitPart> digitParts = new DigitParser().from(lines);
        Set<SymbolPart> symbolParts = new SymbolParser().from(lines);
        return new EngineSchematic(digitParts, symbolParts);
    }
}
