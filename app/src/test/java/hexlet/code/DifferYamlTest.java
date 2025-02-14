package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Files;
import java.nio.file.Paths;

class DifferYamlTest {

    private static final String FIXTURES_PATH = "src/test/resources/fixtures/";

    @Test
    void testGenerateYaml() throws Exception {
        String file1 = FIXTURES_PATH + "file1.yml";
        String file2 = FIXTURES_PATH + "file2.yml";

        String expected = new String(Files.readAllBytes(Paths.get(FIXTURES_PATH + "expected_output.txt")));
        assertEquals(expected.trim(), Differ.generate(file1, file2).trim());
    }
}
