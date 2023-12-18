package pl.alergeek;

import pl.alergeek.model.Day;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;
import static pl.alergeek.ListCommand.findImplementedDays;

@Command(
        name = "run",
        description = "Run tasks from day od advent code."
)
public class RunCommand implements Runnable {
    @Option(names = {"-d", "--day"},
            description = "Specify day of challenge",
            defaultValue = "1"
    )
    String day;

    @Override
    public void run() {
        Set<Class<? extends Day>> classes = findImplementedDays();
        Class<? extends Day> dayClass = classes.stream()
                .filter(aClass -> aClass.getSimpleName().equalsIgnoreCase(day))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        try {
            Day dayObj = dayClass.getConstructor().newInstance();
            String task1 = dayObj.task1();
            String task2 = dayObj.task2();

            System.out.println("Result of task 1:\t" + task1);
            System.out.println("Result of task 2:\t" + task2);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}