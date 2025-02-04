package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diff) {
        return diff.stream()
                .map(this::formatLine)
                .filter(line -> !line.isEmpty()) // Remove empty lines
                .collect(Collectors.joining("\n"));
    }

    private String formatLine(Map<String, Object> change) {
        String key = change.get("key").toString();
        String type = change.get("type").toString();

        switch (type) {
            case "added":
                return "Property '" + key + "' was added with value: " + formatValue(change.get("value"));
            case "removed":
                return "Property '" + key + "' was removed";
            case "changed":
                return "Property '" + key + "' was updated. From "
                        + formatValue(change.get("oldValue"))
                        + " to "
                        + formatValue(change.get("newValue"));
            default:
                return ""; // Ignore unchanged properties
        }
    }

    private String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value instanceof String ? "'" + value + "'" : value.toString();
    }
}
