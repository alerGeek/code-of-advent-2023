package pl.alergeek.codeofadvent.day2;

import pl.alergeek.codeofadvent.day2.model.Game;
import pl.alergeek.model.Task;

import java.util.List;

public class Task2 implements Task {
    @Override
    public String solve(List<String> input) {
        int powerSum = input.stream()
                .map(Game::from)
                .map(Game::setList)
                .mapToInt(Game::calculatePower)
                .sum();
        return String.valueOf(powerSum);
    }
}
