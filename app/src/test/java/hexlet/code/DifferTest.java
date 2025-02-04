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
            "src/test/resources/file1.json, src/test/resources/file2.json, stylish, src/test/resources/expectedStylish.json",
            "src/test/resources/file1.yaml, src/test/resources/file2.yaml, plain, src/test/resources/expectedPlain.txt",
            "src/test/resources/file1.txt, src/test/resources/file2.txt, json, src/test/resources/expectedJson.json"
    })
    void testGenerateWithDifferentFormats(
            String file1Path, String file2Path, String format, String expectedPath) throws Exception {
        String expectedOutput = Files.readString(Paths.get(expectedPath)).trim();
        String actualOutput = Differ.generate(file1Path, file2Path, format).trim();
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void testGenerateWithNonExistentFile() {
        Exception exception = assertThrows(IOException.class, () -> {
            Differ.generate("src/test/resources/missing.json", "src/test/resources/file2.json", "stylish");
        });
        assertThat(exception.getMessage()).contains("No such file");
    }
}
