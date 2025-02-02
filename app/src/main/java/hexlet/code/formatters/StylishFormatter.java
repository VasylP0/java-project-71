package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> diff : differences) {
            String key = (String) diff.get("key");
            String type = (String) diff.get("type");

            switch (type) {
                case "added" -> result.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(diff.get("value"))
                        .append("\n");
                case "removed" -> result.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(diff.get("value"))
                        .append("\n");
                case "changed" -> {
                    result.append("  - ")
                            .append(key)
                            .append(": ")
                            .append(diff.get("oldValue"))
                            .append("\n");
                    result.append("  + ")
                            .append(key)
                            .append(": ")
                            .append(diff.get("newValue"))
                            .append("\n");
                }
                case "unchanged" -> result.append("    ")
                        .append(key)
                        .append(": ")
                        .append(diff.get("value"))
                        .append("\n");
            }
        }

        return result.append("}").toString();
    }
}
