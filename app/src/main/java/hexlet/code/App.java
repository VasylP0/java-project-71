package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Parameters(index = "0", description = "Path to the first file")
    private String filepath1;

    @Parameters(index = "1", description = "Path to the second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "Output format (default: stylish)")
    private String format;

    @Override
    public void run() {
        try {
            String diff = Differ.generate(filepath1, filepath2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("❌ Error: Failed to generate diff");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int exitCode = new picocli.CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
