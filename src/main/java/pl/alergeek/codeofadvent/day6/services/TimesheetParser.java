package pl.alergeek.codeofadvent.day6.services;

import pl.alergeek.codeofadvent.day6.model.TimeSheet;

import java.util.List;

public interface TimesheetParser {

    TimeSheet parse(List<String> input);

    long[] parseTimes(List<String> input);

    long[] parseDistance(List<String> input);
}
