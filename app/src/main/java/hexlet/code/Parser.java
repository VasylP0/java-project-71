package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        ObjectMapper objectMapper = getObjectMapper(filePath);
        return objectMapper.readValue(content, Map.class);
    }

    private static ObjectMapper getObjectMapper(String filePath) {
        return filePath.endsWith(".yaml") || filePath.endsWith(".yml")
                ? new ObjectMapper(new YAMLFactory())
                : new ObjectMapper();
    }
}
