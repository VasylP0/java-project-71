package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diff) {
        try {
            return new ObjectMapper().writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error formatting JSON", e);
        }
    }
}
