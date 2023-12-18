package pl.alergeek;

import picocli.CommandLine;

@CommandLine.Command(
        name = "Application main command",
        helpCommand = true,
        header = "Advent of Code CLI",
        description = "Show help.",
        mixinStandardHelpOptions = true,
        footerHeading = "%nCopyright\t",
        footer = "alerGeek, Advent of Code 2023",
        subcommandsRepeatable = true,
        optionListHeading = "%nDefault options:%n",
        commandListHeading = "%nSubCommands are: %n")

public class HelpCommand implements Runnable {
    @Override
    public void run() {
        new CommandLine(new AppCommand()).execute("--help");
    }
}
