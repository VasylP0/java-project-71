package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private Path getFilePath(String filename) throws Exception {
        var url = getClass().getClassLoader().getResource(filename);
        if (url == null) {
            throw new IllegalArgumentException("Test file not found: " + filename);
        }
        return Path.of(url.toURI());
    }

    private void assertJsonOutput(String file1, String file2, String expected, String format) throws Exception {
        Path file1Path = getFilePath(file1);
        Path file2Path = getFilePath(file2);
        Path expectedPath = getFilePath(expected);

        String expectedContent = Files.readString(expectedPath);
        String actual = Differ.generate(file1Path.toString(), file2Path.toString(), format);

        System.out.println("Generated Output for format: " + format + "\n" + actual);

        JsonNode expectedJson = mapper.readTree(expectedContent);
        JsonNode actualJson = mapper.readTree(actual);

        assertEquals(expectedJson, actualJson, "Output does not match expected JSON.");
    }

    @Test
    void testJsonToJsonOutput() throws Exception {
        assertJsonOutput("file1.json", "file2.json", "result.json", "json");
    }

    @Test
    void testYamlToYamlOutput() throws Exception {
        assertJsonOutput("file1.yml", "file2.yml", "result.json", "json");
    }

    @Test
    void testJsonToYamlOutput() throws Exception {
        assertJsonOutput("file1.json", "file2.yml", "result.json", "json");
    }

    @Test
    void testYamlToJsonOutput() throws Exception {
        assertJsonOutput("file1.yml", "file2.json", "result.json", "json");
    }

    @Test
    void testPlainOutput() throws Exception {
        Path file1Path = getFilePath("file1.json");
        Path file2Path = getFilePath("file2.json");
        Path expectedPath = getFilePath("resultPlain");

        String expectedContent = Files.readString(expectedPath);
        String actual = Differ.generate(file1Path.toString(), file2Path.toString(), "plain");

        System.out.println("Generated Plain Output:\n" + actual);

        assertEquals(expectedContent.trim(), actual.trim(), "Output does not match expected Plain format.");
    }

    @Test
    void testStylishOutput() throws Exception {
        Path file1Path = getFilePath("file1.json");
        Path file2Path = getFilePath("file2.json");
        Path expectedPath = getFilePath("resultStylish");

        String expectedContent = Files.readString(expectedPath);
        String actual = Differ.generate(file1Path.toString(), file2Path.toString());

        System.out.println("Generated Stylish Output:\n" + actual);

        assertEquals(expectedContent.trim(), actual.trim(), "Output does not match expected Stylish format.");
    }

    @Test
    void testJsonToPlainOutput() throws Exception {
        Path file1Path = getFilePath("file1.json");
        Path file2Path = getFilePath("file2.json");
        Path expectedPath = getFilePath("resultPlain");

        String expectedContent = Files.readString(expectedPath);
        String actual = Differ.generate(file1Path.toString(), file2Path.toString(), "plain");

        System.out.println("Generated Plain Output:\n" + actual);

        assertEquals(expectedContent.trim(), actual.trim(), "Output does not match expected Plain format.");
    }

    @Test
    void testYamlToPlainOutput() throws Exception {
        Path file1Path = getFilePath("file1.yml");
        Path file2Path = getFilePath("file2.yml");
        Path expectedPath = getFilePath("resultPlain");

        String expectedContent = Files.readString(expectedPath);
        String actual = Differ.generate(file1Path.toString(), file2Path.toString(), "plain");

        System.out.println("Generated Plain Output:\n" + actual);

        assertEquals(expectedContent.trim(), actual.trim(), "Output does not match expected Plain format.");
    }
}
