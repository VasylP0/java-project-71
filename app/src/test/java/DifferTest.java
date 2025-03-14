package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    void testToJsonInJson() throws Exception {
        // Get absolute paths for test files
        String file1 = Paths.get("src/test/resources/file1.json").toAbsolutePath().toString();
        String file2 = Paths.get("src/test/resources/file2.json").toAbsolutePath().toString();

        // Call generate method
        String actual = Differ.generate(file1, file2);

        // Load expected output from file
        Path expectedPath = Paths.get("src/test/resources/result.json").toAbsolutePath();
        String expected = Files.readString(expectedPath);

        // Convert JSON strings to JsonNode for comparison
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(expected);
        JsonNode actualJson = mapper.readTree(actual);

        // Assert JSON structure equality
        assertEquals(expectedJson, actualJson);
    }
}
