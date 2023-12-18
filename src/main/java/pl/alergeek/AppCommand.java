package pl.alergeek;

import picocli.CommandLine;

import java.util.logging.Logger;

import static picocli.CommandLine.Command;

@Command(
        name = "Application main command",
        header = "Advent of Code CLI",
        description = "Application for running Code Of Advent 2023 Challenge tasks.",
        mixinStandardHelpOptions = true,
        footerHeading = "%nCopyright\t",
        footer = "alerGeek, Advent of Code 2023",
        subcommandsRepeatable = true,
        optionListHeading = "%nDefault options:%n",
        commandListHeading = "%nSubCommands are: %n",
        subcommands = {
                HelpCommand.class,
                RunCommand.class,
                ListCommand.class}
)
public class AppCommand {
    private final Logger logger = Logger.getLogger("AppCommand.class");

    public static void main(String[] args) {
        new CommandLine(new AppCommand()).execute(args);
    }
}