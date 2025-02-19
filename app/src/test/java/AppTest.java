

import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class AppTest {

    @Test
    void test() throws IOException {
        String file1 = readString(Path.of("src/main/resources/file1.json"));
        String file2 = readString(Path.of("src/main/resources/file2.json"));
        assertNotEquals(file1, file2);
    }
}
