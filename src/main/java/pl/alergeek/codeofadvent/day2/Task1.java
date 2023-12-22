package pl.alergeek.codeofadvent.day2;

import pl.alergeek.codeofadvent.day2.model.Game;
import pl.alergeek.model.Task;

import java.util.List;

public class Task1 implements Task {

    @Override
    public String solve(List<String> input) {
        int sum = input.stream()
                .map(Game::from)
                .filter(Game::validate)
                .mapToInt(game -> Integer.parseInt(game.id().replaceAll("[^\\d]", "")))
                .sum();
        return String.valueOf(sum);
    }

}
