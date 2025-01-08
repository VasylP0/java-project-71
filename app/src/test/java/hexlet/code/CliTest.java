package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CliTest {

    @Test
    void testHelpOption() {
        CommandLine cmd = new CommandLine(new App());
        int exitCode = cmd.execute("-h");

        assertEquals(CommandLine.ExitCode.OK, exitCode);
    }

    @Test
    void testVersionOption() {
        CommandLine cmd = new CommandLine(new App());
        int exitCode = cmd.execute("-V");

        assertEquals(CommandLine.ExitCode.OK, exitCode);
    }

    @Test
    void testGenerateDiff() throws Exception {
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        App app = new App();
        String[] args = {filePath1, filePath2, "-f", "stylish"};
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute(args);

        assertEquals(0, exitCode);
    }
}
