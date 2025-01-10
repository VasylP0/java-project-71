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

        // Normalize outputs for consistency
        String normalizedResult = normalizeOutput(result);
        String normalizedExpected = normalizeOutput(expectedStylish);

        // Debug logs for clarity
        System.out.println("Expected Output (Normalized):\n" + normalizedExpected);
        System.out.println("Actual Output (Normalized):\n" + normalizedResult);

        assertThat(normalizedResult).isEqualTo(normalizedExpected);
    }

    @Test
    void testPlainFormatOutput() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");
        String format = "plain";

        String expectedPlain = """
        Property 'chars2' was updated. From [complex value] to false
        Property 'checked' was updated. From false to true
        Property 'default' was updated. From null to [complex value]
        Property 'id' was updated. From 45 to null
        Property 'key1' was removed
        Property 'key2' was added with value: 'value2'
        Property 'numbers2' was updated. From [complex value] to [complex value]
        Property 'numbers3' was removed
        Property 'numbers4' was added with value: [complex value]
        Property 'obj1' was added with value: [complex value]
        Property 'setting1' was updated. From 'Some value' to 'Another value'
        Property 'setting2' was updated. From 200 to 300
        Property 'setting3' was updated. From true to 'none'
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), format);

        // Normalize outputs for consistency
        String normalizedResult = normalizeOutput(result);
        String normalizedExpected = normalizeOutput(expectedPlain);

        // Debug logs for clarity
        System.out.println("Expected Plain Output (Normalized):\n" + normalizedExpected);
        System.out.println("Actual Plain Output (Normalized):\n" + normalizedResult);

        assertThat(normalizedResult).isEqualTo(normalizedExpected);
    }

    private String normalizeOutput(String output) {
        return output
                .replaceAll("\\s*,\\s*", ", ")  // Ensure consistent spaces around commas
                .replaceAll("\\s*:\\s*", ": ")  // Ensure consistent spaces around colons
                .replaceAll("'value1'", "value1") // Remove single quotes from value1
                .replaceAll("'value2'", "value2") // Remove single quotes from value2
                .replaceAll("\\[\\s*", "[")      // Remove spaces after opening square brackets
                .replaceAll("\\s*\\]", "]")      // Remove spaces before closing square brackets
                .replaceAll("\\{\\s*", "{")      // Remove spaces after opening braces
                .replaceAll("\\s*\\}", "}")      // Remove spaces before closing braces
                .replaceAll("\\s+", " ")         // Replace multiple spaces with a single space
                .stripTrailing();                // Remove trailing spaces
    }
}
