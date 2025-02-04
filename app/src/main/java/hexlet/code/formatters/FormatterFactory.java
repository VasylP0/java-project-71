package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        return switch (format.toLowerCase()) {
            case "json" -> new JsonFormatter();   // Error
            case "plain" -> new PlainFormatter(); // Error
            default -> new StylishFormatter();    // Error
        };
    }
}

