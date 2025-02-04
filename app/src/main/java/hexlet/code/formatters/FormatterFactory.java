package hexlet.code.formatters;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        return switch (format) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            default -> new StylishFormatter();
        };
    }
}
