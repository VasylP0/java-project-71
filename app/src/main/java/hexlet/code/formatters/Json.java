package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

public class Json {
    public static String formatJson(List<Map<String, Object>> file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON

        // Sort JSON keys to match expected output
        List<ObjectNode> sortedList = file.stream()
                .map(mapper::valueToTree)
                .map(node -> (ObjectNode) node)
                .map(node -> node.retain("key", "status", "value", "value1", "value2"))
                .toList();

        return mapper.writeValueAsString(sortedList);
    }
}
