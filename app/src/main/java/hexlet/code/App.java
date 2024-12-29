package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "Path to the first file")
    private String filepath1;

    @Parameters(index = "1", description = "Path to the second file")
    private String filepath2;

    @Override
    public Integer call() {
        try {
            // Generate and print the diff
            String diff = Differ.generate(filepath1, filepath2, "stylish");
            System.out.println(diff);
            return 0; // Exit code 0 indicates success
        } catch (Exception e) {
            e.printStackTrace();
            return 1; // Exit code 1 indicates failure
        }
    }

    public static void main(String[] args) {
        int exitCode = new picocli.CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
