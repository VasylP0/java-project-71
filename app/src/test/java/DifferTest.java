package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DifferTest {
    @Test
    void testToJsonInJson() throws Exception {
        // Load file paths correctly
        Path file1Path = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file1.json")).toURI());
        Path file2Path = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file2.json")).toURI());
        Path expectedPath = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("result.json")).toURI());

        // Read the actual JSON content
        String file1Content = Files.readString(file1Path);
        String file2Content = Files.readString(file2Path);
        String expectedContent = Files.readString(expectedPath);

        // Call generate method with FILE PATHS, not JSON content
        String actual = Differ.generate(file1Path.toString(), file2Path.toString());

        // Convert JSON strings to JsonNode for structured comparison
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(expectedContent);
        JsonNode actualJson = mapper.readTree(actual);

        // Assert JSON equality
        assertEquals(expectedJson, actualJson, "Differ.generate() output does not match expected JSON.");
    }
}
