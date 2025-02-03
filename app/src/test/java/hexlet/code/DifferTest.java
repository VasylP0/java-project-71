package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    @ParameterizedTest
    @CsvSource({
            "src/test/resources/file1.json, src/test/resources/file2.json, stylish, "
                    + "src/test/resources/expectedStylish.json",
            "src/test/resources/file1.yaml, src/test/resources/file2.yaml, plain, "
                    + "src/test/resources/expectedPlain.txt",
            "src/test/resources/file1.txt, src/test/resources/file2.txt, json, "
                    + "src/test/resources/expectedJson.json"
    })
    void testGenerateWithDifferentFormats(
            String file1Path, String file2Path, String format, String expectedPath) throws Exception {
        // Read expected output
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedPath)));

        // Generate actual output
        String actualOutput = Differ.generate(file1Path, file2Path, format);

        // Assert that output matches expected output
        assertThat(actualOutput).isEqualToIgnoringWhitespace(expectedOutput);
    }

    @Test
    void testGenerateWithNonExistentFile() {
        Exception exception = assertThrows(IOException.class, () -> {
            Differ.generate("src/test/resources/missing.json", "src/test/resources/file2.json", "stylish");
        });

        // Check that the exception message indicates a missing file
        assertThat(exception.getMessage()).contains("No such file");
    }
}
