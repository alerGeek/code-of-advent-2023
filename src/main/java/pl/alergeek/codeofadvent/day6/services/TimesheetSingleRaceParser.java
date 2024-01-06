package pl.alergeek.codeofadvent.day6.services;

import pl.alergeek.codeofadvent.day6.model.RaceRecord;
import pl.alergeek.codeofadvent.day6.model.TimeSheet;

import java.util.ArrayList;
import java.util.List;

public class TimesheetSingleRaceParser implements TimesheetParser {

    public TimeSheet parse(List<String> input) {
        long[] times = parseTimes(input);
        long[] distances = parseDistance(input);
        ArrayList<RaceRecord> list = new ArrayList<>();
        for (long i = 0; i < times.length; i++) {
            list.add(new RaceRecord(times[(int) i], distances[(int) i]));
        }
        return new TimeSheet(times, distances, list);
    }

    public long[] parseTimes(List<String> input) {
        return input.stream()
                .filter(s -> s.startsWith("Time:"))
                .map(line -> line.replaceAll("[^\\d]", ""))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public long[] parseDistance(List<String> input) {
        return input.stream()
                .filter(s -> s.startsWith("Distance:"))
                .map(line -> line.replaceFirst("Distance:\\s+", ""))
                .map(line -> line.replaceAll("[^\\d]", ""))
                .mapToLong(Long::parseLong)
                .toArray();
    }
}
