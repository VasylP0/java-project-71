package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diff) {
        return diff.stream()
                .map(this::formatLine)
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private String formatLine(Map<String, Object> change) {
        String key = change.get("key").toString();
        String type = change.get("type").toString();
        Object value = change.get("value");

        return switch (type) {
            case "added" -> "  + " + key + ": " + value;
            case "removed" -> "  - " + key + ": " + value;
            case "changed" -> "  - " + key + ": " + change.get("oldValue") + "\n  + " + key + ": " + change.get("newValue");
            default -> "  " + key + ": " + value;
        };
    }
}
