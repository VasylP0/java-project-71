package hexlet.code;

import hexlet.code.formatters.FormatterFactory;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Paths;

class DifferTest {

    @Test
    void testGenerateStylishFormat() throws Exception {
        String file1Path = "src/test/resources/file1.json";
        String file2Path = "src/test/resources/file2.json";

        // Read expected output from file (Split long line for Checkstyle compliance)
        String expectedOutput = new String(
                Files.readAllBytes(Paths.get("src/test/resources/expectedJsonResult.json"))
        );

        // Generate actual output
        String actualOutput = Differ.generate(file1Path, file2Path, "stylish");

        // Assertion
        assertThat(actualOutput).isEqualToIgnoringWhitespace(expectedOutput);
    }
}
