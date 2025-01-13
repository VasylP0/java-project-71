package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Formatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FormatterTest {

    @Test
    void testFormatterWithJson() throws Exception {
        List<DiffNode> diffNodes = List.of(
                new DiffNode("key1", "oldValue1", "newValue1", "updated"),
                new DiffNode("key2", null, "newValue2", "added"),
                new DiffNode("key3", "oldValue3", null, "removed")
        );

        String result = Formatter.format(diffNodes, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualNode = mapper.readTree(result);

        String expectedJson = """
            [
              {
                "key": "key1",
                "oldValue": "oldValue1",
                "newValue": "newValue1",
                "status": "updated"
              },
              {
                "key": "key2",
                "oldValue": null,
                "newValue": "newValue2",
                "status": "added"
              },
              {
                "key": "key3",
                "oldValue": "oldValue3",
                "newValue": null,
                "status": "removed"
              }
            ]
        """;

        JsonNode expectedNode = mapper.readTree(expectedJson);

        assertThat(actualNode).isEqualTo(expectedNode);
    }
}
