package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class CliTest {

    @ParameterizedTest
    @CsvSource({
            "'src/test/resources/file1.json', 'src/test/resources/file2.json', stylish",
            "'src/test/resources/file1.yml', 'src/test/resources/file2.yml', stylish"
    })
    void cliGenerateTest(String file1, String file2, String format) {
        String[] args = {file1, file2, "-f", format};

        // Capture the system output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Simulate CLI execution
            App.main(args);
        } finally {
            System.setOut(originalOut); // Restore the original System.out
        }

        String actualOutput = outputStream.toString().trim();
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

        assertThat(normalizeOutput(actualOutput)).isEqualTo(normalizeOutput(expectedOutput));
    }

    private String normalizeOutput(String output) {
        return output
                .replaceAll("\\s*,\\s*", ", ")
                .replaceAll("\\s*:\\s*", ": ")
                .replaceAll("\\[\\s*", "[")
                .replaceAll("\\s*\\]", "]")
                .replaceAll("\\{\\s*", "{")
                .replaceAll("\\s*\\}", "}")
                .replaceAll("\\s+", " ")
                .strip();
    }
}
