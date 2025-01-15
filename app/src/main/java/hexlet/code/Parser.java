package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        if (filePath.endsWith(".json")) {
            return new ObjectMapper().readValue(content, Map.class);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory()).readValue(content, Map.class);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}
