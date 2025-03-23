package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Runnable {

    @Parameters(index = "0", paramLabel = "file1", description = "Path to the first file")
    private String filePath1;

    @Parameters(index = "1", paramLabel = "file2", description = "Path to the second file")
    private String filePath2;

    @Option(names = { "-f", "--format" }, defaultValue = "stylish", paramLabel = "format",
            description = "Output format [default: ${DEFAULT-VALUE}]")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
