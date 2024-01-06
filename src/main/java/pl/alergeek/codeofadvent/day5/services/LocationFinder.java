package pl.alergeek.codeofadvent.day5.services;


import pl.alergeek.codeofadvent.day5.model.Almanac;
import pl.alergeek.codeofadvent.day5.model.RangeInfo;

import java.util.Map;

public class LocationFinder {
    private LocationFinder() {
    }

    public static long findLocationId(Almanac almanac, long seedId) {
        long soilId = findDestinationId(almanac.seedToSoil(), seedId);
        long fertilizerId = findDestinationId(almanac.soilToFertilizer(), soilId);
        long waterId = findDestinationId(almanac.fertilizerToWater(), fertilizerId);
        long lightId = findDestinationId(almanac.waterToLight(), waterId);
        long temperatureId = findDestinationId(almanac.lightToTemperature(), lightId);
        long humidityId = findDestinationId(almanac.temperatureToHumidity(), temperatureId);
        return findDestinationId(almanac.humidityToLocation(), humidityId);
    }

    private static long findDestinationId(Map<String, RangeInfo> map, long sourceId) {
        return map.entrySet()
                .parallelStream()
                .map(Map.Entry::getValue)
                .filter(entryValue -> containsSourceId(sourceId, entryValue.sourceStart(), entryValue.sourceEnd()))
                .findAny()
                .map(entryValue -> calculateDestinationDiff(sourceId, entryValue.sourceStart(), entryValue.destinationStart()))
                .orElse(sourceId);
    }

    private static boolean containsSourceId(long sourceId, long sourceStart, long sourceEnd) {
        return sourceId >= sourceStart && sourceId < sourceEnd;
    }

    private static long calculateDestinationDiff(long sourceId, long sourceStart, long destinationStart) {
        long diff = sourceId - sourceStart;
        return destinationStart + diff;
    }
}
