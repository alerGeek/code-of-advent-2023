package pl.alergeek.codeofadvent.day2.model;

import java.util.Arrays;
import java.util.List;

public record Game(String id, List<CubesSet> setList) {
    public static final int RED_MAX_AMOUNT = 12;
    public static final int GREEN_MAX_AMOUNT = 13;
    public static final int BLUE_MAX_AMOUNT = 14;

    public static Game from(String line) {
        int gameSeparatorIndex = line.indexOf(":");
        String id = line.substring(0, gameSeparatorIndex).trim();
        String setsString = line.replace(id + ": ", "");
        List<CubesSet> sets = Arrays.stream(setsString.split(";"))
                .map(CubesSet::from)
                .toList();
        return new Game(id, sets);
    }

    public static boolean validate(Game game) {
        return game.setList().stream().allMatch(
                CubesSet::validate);
    }

    public static int calculatePower(List<CubesSet> cubesSets) {
        int blue = cubesSets.stream().mapToInt(CubesSet::blues).max().orElse(0);
        int red = cubesSets.stream().mapToInt(CubesSet::reds).max().orElse(0);
        int green = cubesSets.stream().mapToInt(CubesSet::greens).max().orElse(0);
        return blue * red * green;
    }
}
