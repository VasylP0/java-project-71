package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.io.IOException;
import java.util.Map;

class ParserTest {

    @Test
    void testParseJson() throws IOException {
        String json = "{\"key\": \"value\"}";
        Map<String, Object> result = Parser.parse(json, "json");
        assertEquals("value", result.get("key"));
    }

    @Test
    void testParseYaml() throws IOException {
        String yaml = "key: value";
        Map<String, Object> result = Parser.parse(yaml, "yaml");
        assertEquals("value", result.get("key"));
    }

    @Test
    void testUnsupportedFormatThrowsException() {
        String content = "key: value";

        UnsupportedOperationException thrown = assertThrows(
                UnsupportedOperationException.class,
                () -> Parser.parse(content, "txt")
        );

        assertEquals("Unsupported format: txt", thrown.getMessage());
    }
}
