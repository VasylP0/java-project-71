package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public class StylishFormatter implements Formatter {

    @Override
    public String format(List<DiffNode> diffNodes, String format) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            String status = node.getStatus();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();

            switch (status) {
                case "added" -> result.append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "removed" -> result.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                case "updated" -> {
                    result.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                    result.append("  + ").append(key).append(": ").append(newValue).append("\n");
                }
                case "unchanged" -> result.append("    ").append(key).append(": ").append(oldValue).append("\n");
                default -> {
                }
            }
        }

        result.append("}");
        return result.toString();
    }
}
