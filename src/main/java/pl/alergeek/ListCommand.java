package pl.alergeek;

import org.reflections.Reflections;
import picocli.CommandLine;
import pl.alergeek.model.Day;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static picocli.CommandLine.Command;

@Command(name = "list",
        aliases = {"ls"},
        mixinStandardHelpOptions = true,
        description = "One of available command of Code of Advent application.\n" +
                "Command list all existing in project days and it's tasks. Command may filter tasks by it's state.",
        header = "Command 'list'",
        optionListHeading = "%nOptions:%n",
        footerHeading = "%nCopyright\t",
        footer = "%nDeveloped by alerGeek")
public class ListCommand implements Runnable {
    private final Logger logger = Logger.getLogger("ListCommand.class");
    @CommandLine.Option(
            names = {"-a", "--all"},
            required = false)
    private boolean all;

    private Set<String> days;

    @Override
    public void run() {
        this.days = findImplementedDays().stream()
                .map(Class::getSimpleName)
                .sorted()
                .collect(Collectors.toSet());

        System.out.println("Implemented classes:");
        this.days.forEach(System.out::println);
    }

    protected static Set<Class<? extends Day>> findImplementedDays() {
        Reflections reflections = new Reflections("pl.alergeek");
        Set<Class<? extends Day>> allClasses = reflections.getSubTypesOf(Day.class);
        return new HashSet<>(allClasses);
    }
}