package pl.alergeek.codeofadvent.day5;

import pl.alergeek.codeofadvent.day5.model.Almanac;
import pl.alergeek.codeofadvent.day5.services.AlmanacParser;
import pl.alergeek.codeofadvent.day5.services.SeedListParser;
import pl.alergeek.model.Task;

import java.util.List;

import static pl.alergeek.codeofadvent.day5.services.LocationFinder.findLocationId;

public class Task1 implements Task {

    @Override
    public String solve(List<String> input) {
        final List<Long> seeds = SeedListParser.parseSeeds(input.get(0));
        final Almanac almanac = AlmanacParser.parse(String.join("\n", input));

        long minLocation = seeds.stream()
                .mapToLong(seedId -> findLocationId(almanac, seedId))
                .min()
                .orElse(-1L);
        return String.valueOf(minLocation);
    }
}
