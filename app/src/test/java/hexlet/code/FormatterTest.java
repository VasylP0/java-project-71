package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FormatterTest {

    @Test
    void testJsonFormatter() {
        JsonFormatter jsonFormatter = new JsonFormatter();
        List<DiffNode> diffNodes = List.of(
                new DiffNode("key1", "value1", "value2", "updated")
        );

        String result = jsonFormatter.format(diffNodes, "json");
        String expected = "[{\"key\":\"key1\",\"status\":\"updated\",\"oldValue\":\"value1\",\"newValue\":\"value2\"}]";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testPlainFormatter() {
        PlainFormatter plainFormatter = new PlainFormatter();
        List<DiffNode> diffNodes = List.of(
                new DiffNode("key1", "value1", "value2", "updated")
        );

        String result = plainFormatter.format(diffNodes, "plain");
        String expected = "Property 'key1' was updated. From 'value1' to 'value2'";
        assertThat(result).isEqualTo(expected);
    }
}
