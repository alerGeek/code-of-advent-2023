package pl.alergeek.codeofadvent.day2.model;

import java.util.Arrays;

import static pl.alergeek.codeofadvent.day2.model.Game.*;

public record CubesSet(int reds, int greens, int blues) {

    public static CubesSet from(String line) {
        String[] colorsInfo = line.trim().split(",");
        final int[] blue = {0};
        final int[] green = {0};
        final int[] red = {0};
        Arrays.stream(colorsInfo).forEach(color -> {
            blue[0] = color.contains("blue") ? Integer.parseInt(color.replace("blue", "").trim()) : blue[0];
            red[0] = color.contains("red") ? Integer.parseInt(color.replace("red", "").trim()) : red[0];
            green[0] = color.contains("green") ? Integer.parseInt(color.replace("green", "").trim()) : green[0];
        });
        return new CubesSet(red[0], green[0], blue[0]);
    }

    public static boolean validate(CubesSet cubes) {
        return BLUE_MAX_AMOUNT >= cubes.blues() &&
               GREEN_MAX_AMOUNT >= cubes.greens() &&
               RED_MAX_AMOUNT >= cubes.reds();
    }
}