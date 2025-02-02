package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class FormatterTest {

    @Test
    void testJsonFormatter() {
        JsonFormatter jsonFormatter = new JsonFormatter();
        List<Map<String, Object>> diffNodes = List.of(
                Map.of("key", "host", "type", "changed", "oldValue", "hexlet.io", "newValue", "localhost")
        );

        String result = jsonFormatter.format(diffNodes);
        assertThat(result).contains("\"key\" : \"host\"");
    }

    @Test
    void testPlainFormatter() {
        PlainFormatter plainFormatter = new PlainFormatter();
        List<Map<String, Object>> diffNodes = List.of(
                Map.of("key", "debug", "type", "added", "value", true)
        );

        String result = plainFormatter.format(diffNodes);
        assertThat(result).contains("Property 'debug' was added with value: true");
    }
}
