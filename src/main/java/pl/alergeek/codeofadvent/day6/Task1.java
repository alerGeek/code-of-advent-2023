package pl.alergeek.codeofadvent.day6;

import pl.alergeek.codeofadvent.day6.model.TimeSheet;
import pl.alergeek.codeofadvent.day6.services.RaceRecordService;
import pl.alergeek.codeofadvent.day6.services.TimesheetMultiplyRacesParser;
import pl.alergeek.model.Task;

import java.util.List;

public class Task1 implements Task {

    @Override
    public String solve(List<String> input) {
        TimeSheet timeSheet = new TimesheetMultiplyRacesParser().parse(input);
        long margin = RaceRecordService.calculateMargin(timeSheet.records());
        return String.valueOf(margin);
    }
}
