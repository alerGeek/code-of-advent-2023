package pl.alergeek;

import org.reflections.Reflections;
import picocli.CommandLine;
import pl.alergeek.model.Day;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static picocli.CommandLine.Command;

@Command(name = "list",
        aliases = {"ls"},
        description = "One of available command of Code of Advent application.\n" +
                "Command list all existing in project days and it's tasks. Command may filter tasks by it's state.",
        header = "Advent of Code CLI",
        mixinStandardHelpOptions = true,
        footerHeading = "%nCopyright\t",
        footer = "alerGeek, Advent of Code 2023",
        subcommandsRepeatable = true,
        optionListHeading = "%nOptions:%n",
        commandListHeading = "%nSubCommands are: %n"
)
public class ListCommand implements Runnable {
    @CommandLine.Option(
            names = {"-a", "--all"},
            defaultValue = "true")
    private boolean all;

    private Set<String> days;

    @Override
    public void run() {
        this.days = findImplementedDays().stream()
                .map(Class::getSimpleName)
                .collect(Collectors.toSet());

        System.out.println("Implemented classes:");
        if (this.all) {
            this.days.forEach(System.out::println);
        }
    }

    protected static Set<Class<? extends Day>> findImplementedDays() {
        Reflections reflections = new Reflections("pl.alergeek");
        Set<Class<? extends Day>> allClasses = reflections.getSubTypesOf(Day.class);
        return new HashSet<>(allClasses);
    }
}