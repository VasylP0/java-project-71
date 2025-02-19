
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import hexlet.code.Differ;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedJson;
    private static String expectedStylish;
    private static String expectedPlain;

    @BeforeAll
    public static void readResult() throws Exception {

        expectedJson = readString(Paths.get("src/test/resources/result.json")).trim();
        expectedStylish = readString(Paths.get("src/test/resources/resultStylish")).trim();
        expectedPlain = readString(Paths.get("src/test/resources/resultPlain")).trim();

    }
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }
    @Test
    public void testToJsonInJson() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString(), "json");
        assertEquals(expectedJson, actual);
    }
    @Test
    public void testToYamlInJson() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.yaml").toString(),
                getFixturePath("file2.yaml").toString(), "json");
        assertEquals(expectedJson, actual);
    }
    @Test
    public void testToJsonInStylish() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString(),  "stylish");
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void testToYamlInStylish() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.yaml").toString(),
                getFixturePath("file2.yaml").toString(), "stylish");
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void testToJsonInPlain() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString(), "plain");
        assertEquals(expectedPlain, actual);
    }
    @Test
    public void testToYamlInPlain() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.yaml").toString(),
                getFixturePath("file2.yaml").toString(), "plain");
        assertEquals(expectedPlain, actual);
    }
    @Test
    public void testToJson() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString());
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void testToYaml() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.yaml").toString(),
                getFixturePath("file2.yaml").toString());
        assertEquals(expectedStylish, actual);
    }
}
