package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content) throws Exception {
        ObjectMapper objectMapper;

        if (content.startsWith("{")) {
            objectMapper = new ObjectMapper(); // JSON parser
        } else {
            objectMapper = new ObjectMapper(new YAMLFactory()); // YAML parser
        }

        return objectMapper.readValue(content, Map.class);
    }
}
