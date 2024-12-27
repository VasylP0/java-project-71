package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true, // Enables -h and -V options
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public void run() {
        try {
            // Reading and parsing JSON files
            Map<String, Object> data1 = readJsonFile(filepath1);
            Map<String, Object> data2 = readJsonFile(filepath2);

            // Print parsed data for debugging
            System.out.println("File 1 Data: " + data1);
            System.out.println("File 2 Data: " + data2);

            // Placeholder for comparison logic
            System.out.printf("Comparing files: %s and %s%n", filepath1, filepath2);
            System.out.printf("Selected format: %s%n", format);

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions at the top level
        }
    }

    public static Map<String, Object> readJsonFile(String filePath) throws Exception {
        System.out.println("Reading file: " + filePath); // Debugging statement
        String content = Files.readString(Paths.get(filePath));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, Map.class);
    }

    public static void main(String[] args) {
        int exitCode = new picocli.CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
