package pl.alergeek.codeofadvent.day5.model;

import java.util.Map;

public record Almanac(
        Map<String, RangeInfo> seedToSoil,
        Map<String, RangeInfo> soilToFertilizer,
        Map<String, RangeInfo> fertilizerToWater,
        Map<String, RangeInfo> waterToLight,
        Map<String, RangeInfo> lightToTemperature,
        Map<String, RangeInfo> temperatureToHumidity,
        Map<String, RangeInfo> humidityToLocation
) {
}
