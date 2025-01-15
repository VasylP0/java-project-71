package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;
import java.util.Map;

public class PlainFormatter implements Formatter {

    @Override
    public String format(List<DiffNode> diffNodes, String format) {
        StringBuilder result = new StringBuilder();

        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            String status = node.getStatus();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();

            switch (status) {
                case "added" -> result.append("Property '").append(key).append("' was added with value: ")
                        .append(formatValue(newValue)).append("\n");
                case "removed" -> result.append("Property '").append(key).append("' was removed").append("\n");
                case "updated" -> result.append("Property '").append(key).append("' was updated. From ")
                        .append(formatValue(oldValue)).append(" to ").append(formatValue(newValue)).append("\n");
                default -> {
                }
            }
        }

        return result.toString().trim();
    }

    private String formatValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}
