package pl.alergeek.codeofadvent.day5;

import pl.alergeek.codeofadvent.day5.model.Almanac;
import pl.alergeek.codeofadvent.day5.model.SeedsInfo;
import pl.alergeek.codeofadvent.day5.services.AlmanacParser;
import pl.alergeek.codeofadvent.day5.services.SeedListParser;
import pl.alergeek.model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.LongStream;

import static pl.alergeek.codeofadvent.day5.services.LocationFinder.findLocationId;

public class Task2 implements Task {

    @Override
    public String solve(List<String> input) {
        SeedsInfo seedsInfo = parseToSeeds(input);
        Almanac almanac = parseToAlmanac(input);
        long minLocation = findMinLocation(seedsInfo, almanac);
        return String.valueOf(minLocation);
    }

    private static long findMinLocation(SeedsInfo seedsInfo, Almanac almanac) {
        AtomicLong finished = new AtomicLong(0);
        AtomicReference<Float> percentage = new AtomicReference<>(0.0f);
        System.out.println("Find seeds location ids.");
        return seedsInfo.seeds()
                .map(seedId -> {
                    showProgress(seedsInfo.seedsCount(), finished, percentage);
                    return findLocationId(almanac, seedId);
                })
                .min()
                .orElse(-1L);
    }

    private static Almanac parseToAlmanac(List<String> input) {
        List<String> mapsLines = input.stream().skip(2).toList();
        return AlmanacParser.parse(String.join("\n", mapsLines));
    }

    private static SeedsInfo parseToSeeds(List<String> input) {
        String seedsLine = input.get(0);
        final LongStream seeds = SeedListParser.parseSeedsWithRanges(seedsLine);
        final long seedsCount = SeedListParser.parseSeedsWithRanges(seedsLine).count();
        return new SeedsInfo(seeds, seedsCount);
    }

    private static void showProgress(long all, AtomicLong finished, AtomicReference<Float> percentage) {
        finished.incrementAndGet();
        float diff = (finished.get() * 100 / Float.parseFloat(String.valueOf(all))) - percentage.get();
        if (diff >= 1) {
            percentage.set(finished.get() * 100 / Float.parseFloat(String.valueOf(all)));
            System.out.printf("Finished [%s]%n", percentage);
        }
    }
}
