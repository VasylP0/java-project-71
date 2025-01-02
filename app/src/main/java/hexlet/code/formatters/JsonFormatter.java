package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;

import java.util.List;

public class JsonFormatter {

    public static String format(List<DiffNode> diffNodes) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffNodes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing to JSON", e);
        }
    }
}
