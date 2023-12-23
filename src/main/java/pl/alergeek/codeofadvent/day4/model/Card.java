package pl.alergeek.codeofadvent.day4.model;

import java.util.List;

public record Card(String name, List<Integer> winningNumbers, List<Integer> yourNumbers) implements Cloneable {

    public int getDay() {
        return Integer.parseInt(name.replaceAll("[^\\d]", ""));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
