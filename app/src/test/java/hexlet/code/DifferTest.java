package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void testJsonFormat() throws Exception {
        // Define file paths
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";
        String format = "json";
        String expectedJsonPath = "expectedJsonResult.json";

        // Ensure files exist
        assert Files.exists(Path.of(filePath1)) : "File 1 does not exist!";
        assert Files.exists(Path.of(filePath2)) : "File 2 does not exist!";
        assert Files.exists(Path.of(DifferTest.class.getClassLoader().getResource(expectedJsonPath).toURI())) : "Expected JSON result file does not exist!";

        // Read the content of file1.json and file2.json
        String file1Content = new String(Files.readAllBytes(Path.of(filePath1)));
        String file2Content = new String(Files.readAllBytes(Path.of(filePath2)));

        // Generate the result
        String result = Differ.generate(filePath1, filePath2, format);

        // Read the expected JSON file
        String expectedJson = new String(Files.readAllBytes(
                Path.of(DifferTest.class.getClassLoader().getResource(expectedJsonPath).toURI())));

        // Parse and compare the JSON content
        ObjectMapper mapper = new ObjectMapper();
        var resultJsonNode = mapper.readTree(result);
        var expectedJsonNode = mapper.readTree(expectedJson);

        System.out.println("Generated Result: " + resultJsonNode.toPrettyString());
        System.out.println("Expected Result: " + expectedJsonNode.toPrettyString());

        assertThat(resultJsonNode).isEqualTo(expectedJsonNode);
    }
}
