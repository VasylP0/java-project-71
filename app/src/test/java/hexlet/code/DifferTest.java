package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Path;

class DifferTest {

    @Test
    void testJsonFormatOutput() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");
        String format = "json";

        String expectedJson = """
            [
                {"key":"chars2","oldValue":["d","e","f"],"newValue":false,"status":"updated"},
                {"key":"checked","oldValue":false,"newValue":true,"status":"updated"},
                {"key":"default","oldValue":null,"newValue":["value1","value2"],"status":"updated"},
                {"key":"id","oldValue":45,"newValue":null,"status":"updated"},
                {"key":"key1","oldValue":"value1","newValue":null,"status":"removed"},
                {"key":"key2","oldValue":null,"newValue":"value2","status":"added"},
                {"key":"numbers2","oldValue":[2,3,4,5],"newValue":[22,33,44,55],"status":"updated"},
                {"key":"numbers3","oldValue":[3,4,5],"newValue":null,"status":"removed"},
                {"key":"numbers4","oldValue":null,"newValue":[4,5,6],"status":"added"},
                {"key":"obj1","oldValue":null,"newValue":{"nestedKey":"value","isNested":true},"status":"added"},
                {"key":"setting1","oldValue":"Some value","newValue":"Another value","status":"updated"},
                {"key":"setting2","oldValue":200,"newValue":300,"status":"updated"},
                {"key":"setting3","oldValue":true,"newValue":"none","status":"updated"}
            ]
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), format);
        assertThat(result).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void testPlainFormatOutput() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");
        String format = "plain";

        String expectedPlain = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), format);
        assertThat(result).isEqualTo(expectedPlain);
    }

    @Test
    void testStylishFormatOutput() throws Exception {
        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");
        String format = "stylish";

        String expectedStylish = """
            {
            - chars2: [d,e,f]
            + chars2: false
            - checked: false
            + checked: true
            - default: null
            + default: [value1,value2]
            - id: 45
            + id: null
            - key1: 'value1'
            + key2: 'value2'
            - numbers2: [2,3,4,5]
            + numbers2: [22,33,44,55]
            - numbers3: [3,4,5]
            + numbers4: [4,5,6]
            + obj1: {nestedKey: value,isNested: true}
            - setting1: 'Some value'
            + setting1: 'Another value'
            - setting2: 200
            + setting2: 300
            - setting3: true
            + setting3: 'none'
            }
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), format);
        assertThat(result).isEqualToIgnoringWhitespace(expectedStylish);
    }
}
