package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class CliTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    void cliGenerateTest() {
        System.out.println("🚀 STARTING cliGenerateTest...");

        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";
        String format = "stylish";

        assertTrue(new java.io.File(file1).canRead(), "✅ File is readable: " + file1);
        assertTrue(new java.io.File(file2).canRead(), "✅ File is readable: " + file2);

        System.out.println("🛠 Before calling App.main(args)...");

        try {
            App.main(new String[]{file1, file2, "--format", format});
            System.out.println("✅ App.main() completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ App.main() threw an exception: " + e.getMessage());
        }

        System.out.println("📤 Captured Output: \n" + outputStream.toString());
    }
}
