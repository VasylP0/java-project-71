import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AppTest {
    @Test
    void test() throws IOException {
        String file1 = Files.readString(Paths.get("build", "resources", "test", "file1.json"));
        String file2 = Files.readString(Paths.get("build", "resources", "test", "file2.json"));
        assertNotEquals(file1, file2);
    }
}
