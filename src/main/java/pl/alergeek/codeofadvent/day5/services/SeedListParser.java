package pl.alergeek.codeofadvent.day5.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class SeedListParser {

    private SeedListParser() {
    }

    public static List<Long> parseSeeds(String seedsLine) {
        seedsLine = prepareInput(seedsLine);
        String[] seeds = seedsLine.split(" ");
        return Arrays.stream(seeds)
                .map(Long::parseLong)
                .toList();
    }

    public static LongStream parseSeedsWithRanges(String seedsLine) {
        seedsLine = prepareInput(seedsLine);
        List<String> seedsWithRange = findSeedsWithRange(seedsLine);
        return seedsWithRange.stream()
                .parallel()
                .map(seed -> {
                    final String[] seedArr = seed.split(" ");
                    System.out.printf("Map from: %s%n", seed);
                    final long fromId = Long.parseLong(seedArr[0]);
                    final long toId = fromId + Long.parseLong(seedArr[1]);
                    return LongStream.range(fromId, toId);
                })
                .flatMapToLong(LongStream::sequential);
    }

    private static String prepareInput(String seedsLine) {
        return seedsLine.replaceFirst("seeds: ", "");
    }

    private static List<String> findSeedsWithRange(String seedsLine) {
        Pattern pattern = Pattern.compile("\\d+ \\d+");
        Matcher matcher = pattern.matcher(seedsLine);
        List<String> seedsWithRange = new ArrayList<>();
        while (matcher.find()) {
            String matches = matcher.group();
            seedsWithRange.add(matches);
        }
        return seedsWithRange;
    }
}
