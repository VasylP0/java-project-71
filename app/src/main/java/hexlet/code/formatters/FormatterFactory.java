package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        switch (format.toLowerCase()) {
            case "json":
                return new JsonFormatter();
            case "plain":
                return new PlainFormatter();
            case "stylish":
            default:
                return new StylishFormatter();
        }
    }
}
