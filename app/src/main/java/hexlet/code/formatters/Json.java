package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {

    public static String formatJson(List<Map<String, Object>> file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Group differences by status (ADDED, REMOVED, UPDATED, UNCHANGED)
        Map<String, List<Map<String, Object>>> groupedDifferences = file.stream()
                .collect(Collectors.groupingBy(diff -> diff.get("status").toString().toUpperCase()));

        ObjectNode jsonOutput = mapper.valueToTree(groupedDifferences);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonOutput);
    }
}
