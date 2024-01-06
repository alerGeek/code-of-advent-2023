package pl.alergeek.codeofadvent.day6.model;

import java.util.List;

public record TimeSheet(long[] times, long[] distances, List<RaceRecord> records) {
}
