package pl.alergeek;

import picocli.CommandLine;

@CommandLine.Command(
        name = "help",
        description = "Show help of all commands.",
        helpCommand = true,
        mixinStandardHelpOptions = true)
public class HelpCommand implements Runnable {
    @Override
    public void run() {
        new CommandLine(new AppCommand()).execute("--help");
    }
}
