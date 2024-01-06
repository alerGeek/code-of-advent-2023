package pl.alergeek.codeofadvent.day6.services;

import pl.alergeek.codeofadvent.day6.model.RaceRecord;

public class ToyBoatService {
    private static final int START_SPEED = 0;

    private ToyBoatService() {
    }

    public static long calculateDistance(RaceRecord race, long pressTime) {
        long raceDuration = race.duration();
        pressTime = START_SPEED + pressTime;
        if (pressTime >= raceDuration) {
            return 0;
        } else if (pressTime == 0) {
            return 0;
        } else {
            return (race.duration() - pressTime) * pressTime;
        }
    }
}
