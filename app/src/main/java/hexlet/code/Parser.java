package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String fileType) throws Exception {
        ObjectMapper objectMapper = switch (fileType) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new Exception("Unsupported file type: " + fileType);
        };
        return objectMapper.readValue(content, new TypeReference<>() {});
    }

    public static String getFileType(String filepath) {
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }
}
