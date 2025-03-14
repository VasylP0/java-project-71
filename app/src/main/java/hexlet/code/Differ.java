package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Convert file paths to absolute paths
        Path path1 = Paths.get(filePath1).toAbsolutePath();
        Path path2 = Paths.get(filePath2).toAbsolutePath();

        // Read the JSON files
        JsonNode json1 = mapper.readTree(Files.readString(path1));
        JsonNode json2 = mapper.readTree(Files.readString(path2));

        // Compute the differences
        List<Map<String, Object>> diffResult = computeDiff(json1, json2);

        // Convert the diff result back to a JSON string
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffResult);
    }

    private static List<Map<String, Object>> computeDiff(JsonNode json1, JsonNode json2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> allKeys = new TreeSet<>();

        json1.fieldNames().forEachRemaining(allKeys::add);
        json2.fieldNames().forEachRemaining(allKeys::add);

        for (String key : allKeys) {
            boolean keyInJson1 = json1.has(key);
            boolean keyInJson2 = json2.has(key);

            if (keyInJson1 && keyInJson2) {
                if (json1.get(key).equals(json2.get(key))) {
                    diffList.add(Map.of(
                            "key", key,
                            "status", "unchanged",
                            "value", json1.get(key)
                    ));
                } else {
                    diffList.add(Map.of(
                            "key", key,
                            "status", "changed",
                            "value1", json1.get(key),
                            "value2", json2.get(key)
                    ));
                }
            } else if (keyInJson1) {
                diffList.add(Map.of(
                        "key", key,
                        "status", "removed",
                        "value", json1.get(key)
                ));
            } else {
                diffList.add(Map.of(
                        "key", key,
                        "status", "added",
                        "value", json2.get(key)
                ));
            }
        }
        return diffList;
    }
}
