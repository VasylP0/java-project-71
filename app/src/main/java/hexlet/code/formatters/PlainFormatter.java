package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder();

        for (DiffNode node : diffNodes) {
            String fullName = node.getKey(); // Assuming `key` is the property name
            String changeType = node.getStatus();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();

            switch (changeType) {
                case "added":
                    result.append("Property '")
                            .append(fullName)
                            .append("' was added with value: ")
                            .append(formatValue(newValue))
                            .append("\n");
                    break;
                case "removed":
                    result.append("Property '")
                            .append(fullName)
                            .append("' was removed")
                            .append("\n");
                    break;
                case "updated":
                    result.append("Property '")
                            .append(fullName)
                            .append("' was updated. From ")
                            .append(formatValue(oldValue))
                            .append(" to ")
                            .append(formatValue(newValue))
                            .append("\n");
                    break;
                case "unchanged":
                    // Do nothing for unchanged properties
                    break;
                default:
                    throw new IllegalArgumentException("Unknown change type: " + changeType);
            }
        }

        return result.toString().trim();
    }

    // This is the formatValue method you're asking about
    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value == null) {
            return "null";
        }
        return String.valueOf(value);
    }
}
