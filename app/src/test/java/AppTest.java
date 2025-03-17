import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @Test
    void testFileLoading() throws IOException {
        // Load files safely
        Path path1 = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file1.json")).getPath());
        Path path2 = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file2.json")).getPath());

        // Read content
        String file1 = Files.readString(path1);
        String file2 = Files.readString(path2);

        // Ensure they are loaded correctly
        assertNotNull(file1);
        assertNotNull(file2);
        assertNotEquals(file1, file2); // Keeping this, but now it makes sense
    }
}
