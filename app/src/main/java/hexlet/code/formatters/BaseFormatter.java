package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class BaseFormatter {
    public static String switcher(List<Map<String, Object>> diff, String format) {
        return switch (format) {
            case "stylish" -> new StylishFormatter().format(diff);  // Create an instance
            case "plain" -> new PlainFormatter().format(diff);      // Create an instance
            case "json" -> new JsonFormatter().format(diff);        // Create an instance
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
