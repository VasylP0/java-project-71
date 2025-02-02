package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String filePath) throws Exception {
        if (filePath.endsWith(".json")) {
            return new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() {});
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new YAMLMapper().readValue(content, new TypeReference<Map<String, Object>>() {});
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}
