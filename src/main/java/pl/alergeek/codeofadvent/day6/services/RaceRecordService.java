package pl.alergeek.codeofadvent.day6.services;

import pl.alergeek.codeofadvent.day6.model.RaceRecord;

import java.util.List;

public class RaceRecordService {
    private RaceRecordService() {
    }

    public static long calculatePossibleWins(RaceRecord race) {
        int count = 0;
        for (int pressTime = 0; pressTime <= race.duration(); pressTime++) {
            if (ToyBoatService.calculateDistance(race, pressTime) > race.distance()) {
                count += 1;
            }
        }
        return count;
    }

    public static long calculateMargin(List<RaceRecord> races) {
        return races.stream()
                .map(RaceRecordService::calculatePossibleWins)
                .reduce(1L, Math::multiplyExact);
    }
}
