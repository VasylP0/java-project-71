package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true, // Enables -h and --help options automatically
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Override
    public void run() {
        // Default action if no arguments are provided
        System.out.println("Hello, App class is running!");
    }

    public static void main(String[] args) {
        // Initialize Picocli and execute the command
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
