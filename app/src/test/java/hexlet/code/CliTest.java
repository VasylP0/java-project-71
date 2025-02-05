package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CliTest {

    @Test
    void cliGenerateTest() throws Exception {
        String file1 = "src/test/resources/fixtures/file1.json";
        String file2 = "src/test/resources/fixtures/file2.json";
        String expectedOutput = Files.readString(Path.of("src/test/resources/fixtures/expectedStylish.json")).trim();

        // Run Differ and capture output
        String actualOutput = Differ.generate(file1, file2, "stylish").trim();

        // Debugging Output
        System.out.println("📢 Debug: CLI Output:");
        System.out.println("Expected Output:\n" + expectedOutput);
        System.out.println("Actual Output:\n" + actualOutput);

        // Assert Correctness
        assertEquals(expectedOutput, actualOutput);
    }
}
