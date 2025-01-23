package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGenerateStylishFormat() throws Exception {
        String file1Path = "src/test/resources/fixtures/file1.json";
        String file2Path = "src/test/resources/fixtures/file2.json";
        String format = "stylish";

        String actualOutput = Differ.generate(file1Path, file2Path, format);
        String expectedOutput = """
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
        - key1: value1
        + key2: value2
          numbers1: [1, 2, 3, 4]
        - numbers2: [2, 3, 4, 5]
        + numbers2: [22, 33, 44, 55]
        - numbers3: [3, 4, 5]
        + numbers4: [4, 5, 6]
        + obj1: {nestedKey=value, isNested=true}
        - setting1: Some value
        + setting1: Another value
        - setting2: 200
        + setting2: 300
        - setting3: true
        + setting3: none
        }
        """;

        assertThat(normalizeOutput(actualOutput)).isEqualTo(normalizeOutput(expectedOutput));
    }

    @Test
    void testGenerateWithJsonFormat() throws Exception {
        String file1Path = "src/test/resources/fixtures/file1.json";
        String file2Path = "src/test/resources/fixtures/file2.json";
        String format = "json";

        String actualOutput = Differ.generate(file1Path, file2Path, format);

        String expectedOutput = """
        [
          {
            "key": "chars2",
            "oldValue": ["d", "e", "f"],
            "newValue": false,
            "status": "updated"
          },
          {
            "key": "checked",
            "oldValue": false,
            "newValue": true,
            "status": "updated"
          }
        ]
        """;

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        Map<String, Object> actualJson = objectMapper.readValue(
                actualOutput,
                typeFactory.constructMapType(Map.class, String.class, Object.class)
        );
        Map<String, Object> expectedJson = objectMapper.readValue(
                expectedOutput,
                typeFactory.constructMapType(Map.class, String.class, Object.class)
        );

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    private String normalizeOutput(String output) {
        return output
                .replaceAll("\\s+", " ")
                .replaceAll(": ", ":")
                .replaceAll(", ", ",")
                .trim();
    }
}
