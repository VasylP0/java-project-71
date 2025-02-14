package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Files;
import java.nio.file.Paths;

class DifferTest {
    private static final String FIXTURES_PATH = "src/test/resources/";

    @Test
    void testGenerate() throws Exception {
        String file1 = FIXTURES_PATH + "file1.json";
        String file2 = FIXTURES_PATH + "file2.json";

        String expected = new String(Files.readAllBytes(Paths.get(FIXTURES_PATH + "expected_output.txt")));
        assertEquals(expected.trim(), Differ.generate(file1, file2).trim());
    }
}
