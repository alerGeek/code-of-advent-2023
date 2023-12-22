package pl.alergeek.codeofadvent.day3.model;

import java.awt.*;

public record DigitPart(Point point,
                        Character character,
                        NumberRegion numberRegion) implements EnginePart {
    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public Character getCharacter() {
        return this.character;
    }
}
