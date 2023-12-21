package pl.alergeek.codeofadvent.day3.services;

import pl.alergeek.codeofadvent.day3.model.EnginePart;

public class PositionRelationChecker {
    private PositionRelationChecker() {}
    public static boolean isNextTo(EnginePart enginePart1, EnginePart enginePart2) {
        return isOnRightTo(enginePart1, enginePart2) || isOnLeftTo(enginePart1, enginePart2)
               || isOnTopRightTo(enginePart1, enginePart2) || isOnTopTo(enginePart1, enginePart2) || isOnTopLeftTo(enginePart1, enginePart2)
               || isOnBottomRightTo(enginePart1, enginePart2) || isOnBottomTo(enginePart1, enginePart2) || isOnBottomLeftTo(enginePart1, enginePart2);
    }

    private static boolean isOnLeftTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inSameLineTo(enginePart1, enginePart2) && enginePart1.getPoint().x - enginePart2.getPoint().x == -1;
    }

    private static boolean isOnRightTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inSameLineTo(enginePart1, enginePart2) && enginePart1.getPoint().x - enginePart2.getPoint().x == 1;
    }

    private static boolean isOnTopTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inLineUpTo(enginePart1, enginePart2) && enginePart1.getPoint().x == enginePart2.getPoint().x;
    }

    private static boolean isOnBottomTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inLineDownTo(enginePart1, enginePart2) && enginePart1.getPoint().x == enginePart2.getPoint().x;
    }

    private static boolean isOnTopLeftTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inLineUpTo(enginePart1, enginePart2) && enginePart1.getPoint().x - enginePart2.getPoint().x == -1;
    }

    private static boolean isOnTopRightTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inLineUpTo(enginePart1, enginePart2) && enginePart1.getPoint().x - enginePart2.getPoint().x == 1;
    }

    private static boolean isOnBottomLeftTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inLineDownTo(enginePart1, enginePart2) && enginePart1.getPoint().x - enginePart2.getPoint().x == -1;
    }

    private static boolean isOnBottomRightTo(EnginePart enginePart1, EnginePart enginePart2) {
        return inLineDownTo(enginePart1, enginePart2) && enginePart1.getPoint().x - enginePart2.getPoint().x == 1;
    }

    private static boolean inSameLineTo(EnginePart enginePart1, EnginePart enginePart2) {
        return enginePart1.getPoint().y == enginePart2.getPoint().y;
    }

    private static boolean inLineUpTo(EnginePart enginePart1, EnginePart enginePart2) {
        return enginePart1.getPoint().y == enginePart2.getPoint().y - 1;
    }

    private static boolean inLineDownTo(EnginePart enginePart1, EnginePart enginePart2) {
        return enginePart1.getPoint().y == enginePart2.getPoint().y + 1;
    }
}
