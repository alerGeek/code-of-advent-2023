package pl.alergeek.codeofadvent.day5.services;

import pl.alergeek.codeofadvent.day5.model.Almanac;
import pl.alergeek.codeofadvent.day5.model.RangeInfo;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class AlmanacParser {
    private AlmanacParser() {
    }

    public static Almanac parse(String lines) {
        String[] inputStrings = Arrays.stream(lines.split("\n\n"))
                .toArray(String[]::new);
        String seedToSoilLines = inputStrings[0];
        String soilToFertilizerLines = inputStrings[1];
        String fertilizerToWaterLines = inputStrings[2];
        String waterToLightLines = inputStrings[3];
        String lightToTemperatureLines = inputStrings[4];
        String temperatureToHumidityLines = inputStrings[5];
        String humidityToLocationLines = inputStrings[6];

        Map<String, RangeInfo> seedToSoilMap = createRangeMaps(seedToSoilLines);
        Map<String, RangeInfo> soilToFertilizer = createRangeMaps(soilToFertilizerLines);
        Map<String, RangeInfo> fertilizerToWaterMap = createRangeMaps(fertilizerToWaterLines);
        Map<String, RangeInfo> waterToLightMap = createRangeMaps(waterToLightLines);
        Map<String, RangeInfo> lightToTemperatureMap = createRangeMaps(lightToTemperatureLines);
        Map<String, RangeInfo> temperatureToHumidityMap = createRangeMaps(temperatureToHumidityLines);
        Map<String, RangeInfo> humidityToLocationMap = createRangeMaps(humidityToLocationLines);
        return new Almanac(seedToSoilMap, soilToFertilizer, fertilizerToWaterMap, waterToLightMap, lightToTemperatureMap, temperatureToHumidityMap, humidityToLocationMap);
    }

    private static Map<String, RangeInfo> createRangeMaps(String input) {
        String[] split = input.split("\n");
        return Arrays.stream(split)
                .skip(1)
                .parallel()
                .map(line -> {
                            String[] lineAsArr = line.split(" ");
                            RangeInfo rangeInfo = new RangeInfo(
                                    Long.parseLong(lineAsArr[0]),
                                    Long.parseLong(lineAsArr[0]) + Long.parseLong(lineAsArr[2]),
                                    Long.parseLong(lineAsArr[1]),
                                    Long.parseLong(lineAsArr[1]) + Long.parseLong(lineAsArr[2]));
                            return Map.entry("%s-%s".formatted(Long.parseLong(lineAsArr[1]), Long.parseLong(lineAsArr[1]) + Long.parseLong(lineAsArr[2])),
                                    rangeInfo);
                        }
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
