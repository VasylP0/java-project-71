package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

class DifferTest {

    @Test
    void testStylishFormatOutput() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");
        String format = "stylish";

        String expectedStylish = """
        {
          chars1: [a, b, c]
        - chars2: [d, e, f]
        + chars2: false
        - checked: false
        + checked: true
        - default: null
        + default: [value1, value2]
        - id: 45
        + id: null
        - key1: 'value1'
        + key2: 'value2'
          numbers1: [1, 2, 3, 4]
        - numbers2: [2, 3, 4, 5]
        + numbers2: [22, 33, 44, 55]
        - numbers3: [3, 4, 5]
        + numbers4: [4, 5, 6]
        + obj1: {nestedKey: value, isNested: true}
        - setting1: 'Some value'
        + setting1: 'Another value'
        - setting2: 200
        + setting2: 300
        - setting3: true
        + setting3: 'none'
        }
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), format);

        // Normalize outputs
        String normalizedResult = normalizeOutput(result);
        String normalizedExpected = normalizeOutput(expectedStylish);

        // Debug logs for clarity
        System.out.println("Expected Output (Normalized):\n" + normalizedExpected);
        System.out.println("Actual Output (Normalized):\n" + normalizedResult);

        assertThat(normalizedResult).isEqualTo(normalizedExpected);
    }

    private String normalizeOutput(String output) {
        return output
                .replaceAll("\\s*,\\s*", ", ") // Normalize spaces around commas
                .replaceAll("\\s*:\\s*", ": ") // Normalize spaces around colons
                .replaceAll("\\[\\s*", "[")   // Remove spaces after opening brackets
                .replaceAll("\\s*\\]", "]")   // Remove spaces before closing brackets
                .replaceAll("\\{\\s*", "{")   // Remove spaces after opening braces
                .replaceAll("\\s*\\}", "}")   // Remove spaces before closing braces
                .replaceAll("\\s+", " ")      // Replace multiple spaces with a single space
                .strip();                     // Trim leading and trailing spaces
    }
}
