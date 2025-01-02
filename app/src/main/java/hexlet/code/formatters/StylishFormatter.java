package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import hexlet.code.DiffNode; // Ensure this matches the package where DiffNode is located.

public class StylishFormatter {
    public static String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffNode node : diffNodes) {
            switch (node.getStatus()) {
                case "added":
                    result.append("  + ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getNewValue()))
                            .append("\n");
                    break;
                case "removed":
                    result.append("  - ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getOldValue()))
                            .append("\n");
                    break;
                case "updated":
                    result.append("  - ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getOldValue()))
                            .append("\n");
                    result.append("  + ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getNewValue()))
                            .append("\n");
                    break;
                case "unchanged":
                    result.append("    ")
                            .append(node.getKey())
                            .append(": ")
                            .append(formatValue(node.getOldValue()))
                            .append("\n");
                    break;
            }
        }
        result.append("}");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null"; // Explicitly handle null values
        }
        if (value instanceof List || value instanceof Map) {
            return value.toString().replace(", ", ",").replace("=", ": ");
        }
        if (value instanceof String) {
            return "'" + value + "'"; // Add quotes around strings
        }
        return value.toString();
    }
}
