package hexlet.code.formatters;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        return switch (format) {
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            default -> new StylishFormatter();
        };
    }
}
