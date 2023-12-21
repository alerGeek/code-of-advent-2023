package pl.alergeek.codeofadvent.day3.services;

public class TypeCharChecker {
    private TypeCharChecker() {}
    public static boolean isPeriodCharacter(Character character) {
        return character == '.';
    }

    public static boolean isDigit(Character character) {
        return Character.isDigit(character);
    }

    public static boolean isSymbol(Character character) {
        return !isDigit(character) && !isPeriodCharacter(character);
    }
}
