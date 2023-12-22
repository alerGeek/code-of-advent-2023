package pl.alergeek.codeofadvent.day3.model;

public record NumberRegion(int startIndex, int endIndex, String number, int lineIndex) {
    public static NumberRegion from(int startIndex, int endIndex, String number, int lineIndex) {
        return new NumberRegion(startIndex, endIndex, number, lineIndex);
    }
}
