import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import hexlet.code.Differ;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedJson;
    private static String expectedStylish;
    private static String expectedPlain;

    @BeforeAll
    public static void readResult() throws Exception {
        expectedJson = Files.readString(getFixturePath("result.json")).trim();
        expectedStylish = Files.readString(getFixturePath("resultStylish")).trim();
        expectedPlain = Files.readString(getFixturePath("resultPlain")).trim();
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
    }

    private static String normalizeJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
        return mapper.writeValueAsString(list); // Ensures consistent formatting
    }

    @Test
    public void testToJsonInJson() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString(), "json");
        assertEquals(normalizeJson(expectedJson), normalizeJson(actual));
    }

    @Test
    public void testToYamlInJson() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.yml").toString(),
                getFixturePath("file2.yml").toString(), "json");
        assertEquals(normalizeJson(expectedJson), normalizeJson(actual));
    }

    @Test
    public void testToJsonInStylish() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString(), "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testToYamlInStylish() throws Exception {
        var actual = Differ.generate(getFixturePath("file1.yml").toString(),
                getFixturePath("file2.yml").toString(), "stylish");
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
        var actual = Differ.generate(getFixturePath("file1.yml").toString(),
                getFixturePath("file2.yml").toString(), "plain");
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
        var actual = Differ.generate(getFixturePath("file1.yml").toString(),
                getFixturePath("file2.yml").toString());
        assertEquals(expectedStylish, actual);
    }
}
