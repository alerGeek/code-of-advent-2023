package pl.alergeek.codeofadvent.day3;

import pl.alergeek.codeofadvent.day3.model.EngineSchematic;
import pl.alergeek.model.Task;

import java.util.List;

public class Task2 implements Task {

    @Override
    public String solve(List<String> input) {
        EngineSchematic engineSchematic = EngineSchematic.from(input);

        int sum = engineSchematic.sumGearRation();
        return String.valueOf(sum);
    }
}
