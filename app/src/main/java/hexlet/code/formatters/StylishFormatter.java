package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");

            switch (type) {
                case "added" -> result.append(String.format("  + %s: %s%n", key, entry.get("value")));
                case "removed" -> result.append(String.format("  - %s: %s%n", key, entry.get("value")));
                case "changed" -> {
                    result.append(String.format("  - %s: %s%n", key, entry.get("oldValue")));
                    result.append(String.format("  + %s: %s%n", key, entry.get("newValue")));
                }
                default -> result.append(String.format("    %s: %s%n", key, entry.get("value")));
            }
        }

        result.append("}");
        return result.toString();
    }
}
