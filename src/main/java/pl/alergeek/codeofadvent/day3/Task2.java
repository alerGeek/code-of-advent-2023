package pl.alergeek.codeofadvent.day3;

import pl.alergeek.codeofadvent.day3.model.DigitPart;
import pl.alergeek.codeofadvent.day3.model.EngineSchematic;
import pl.alergeek.codeofadvent.day3.model.SymbolPart;
import pl.alergeek.model.Task;

import java.util.List;
import java.util.Set;

public class Task2 implements Task {
    private EngineSchematic engineSchematic;

    @Override
    public String solve(List<String> input) {
        engineSchematic = EngineSchematic.from(input);

        int sum = engineSchematic.sumGearRation();
        return String.valueOf(sum);
    }
}
