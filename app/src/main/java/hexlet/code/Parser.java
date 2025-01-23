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
        ObjectMapper objectMapper = getObjectMapper(filePath);
        return objectMapper.readValue(
                content,
                objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class)
        );
    }

    private static ObjectMapper getObjectMapper(String filePath) {
        if (filePath.endsWith(".json")) {
            return new ObjectMapper();
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}
