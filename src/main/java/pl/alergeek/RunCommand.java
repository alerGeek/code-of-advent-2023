package pl.alergeek;

import pl.alergeek.model.Day;

import java.util.Set;
import java.util.logging.Logger;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;
import static pl.alergeek.ListCommand.findImplementedDays;

@Command(
        name = "run",
        description = "Run tasks from day od advent code."
)
public class RunCommand implements Runnable {
    private final Logger logger = Logger.getLogger("RunCommand.class");
    @Option(names = {"-d", "--day"},
            description = "Specify day of challenge",
            defaultValue = "1"
    )
    String day;

    @Option(names = {"-h", "--help"},
            description = "Show this help",
            help = true,
            usageHelp = true
    )
    boolean help;

    @Override
    public void run() {
        Set<Class<? extends Day>> classes = findImplementedDays();
        Class<? extends Day> dayClass = classes.stream()
                .filter(aClass -> aClass.getSimpleName().equalsIgnoreCase(day))
                .findFirst()
                .orElseThrow();

        try {
            Day dayObj = dayClass.newInstance();
            String task1 = dayObj.task1();
            String task2 = dayObj.task2();

            System.out.println("Result of task 1:\t" + task1);
            System.out.println("Result of task 2:\t" + task2);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}