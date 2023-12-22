package pl.alergeek.codeofadvent.day3;

import pl.alergeek.codeofadvent.day3.model.EngineSchematic;
import pl.alergeek.model.Task;

import java.util.List;

public class Task1 implements Task {
    private EngineSchematic engineSchematic;

    @Override
    public String solve(List<String> input) {
        engineSchematic = EngineSchematic.from(input);
        int sum = engineSchematic.sum();
        return String.valueOf(sum);
    }
}
