package pl.alergeek.codeofadvent.day3.model;

import java.awt.*;

public record SymbolPart(Point point, Character character) implements EnginePart {

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public Character getCharacter() {
        return this.character;
    }
}
