package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Paths.get(filePath));

        ObjectMapper objectMapper;
        if (filePath.endsWith(".json")) {
            objectMapper = new ObjectMapper(); // JSON parser
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            objectMapper = new ObjectMapper(new YAMLFactory()); // YAML parser
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }

        return objectMapper.readValue(content, Map.class);
    }
}
