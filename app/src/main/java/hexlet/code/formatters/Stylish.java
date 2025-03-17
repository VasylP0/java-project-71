package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatStylish(List<Map<String, Object>> file) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> map : file) {
            String key = (String) map.getOrDefault("key", "unknown");
            String status = String.valueOf(map.getOrDefault("status", "unknown"));
            Object value = map.get("value");

            // Handle null values for better readability
            String value1 = String.valueOf(map.getOrDefault("value1", "null"));
            String value2 = String.valueOf(map.getOrDefault("value2", "null"));

            switch (status) {
                case "added":
                    result.append("  + ").append(key).append(": ").append(value == null ? "null" : value).append("\n");
                    break;
                case "changed":
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                case "unchanged":
                    result.append("    ").append(key).append(": ").append(value == null ? "null" : value).append("\n");
                    break;
                case "removed":
                    result.append("  - ").append(key).append(": ").append(value == null ? "null" : value).append("\n");
                    break; // 🔹 Fixed missing break
                default:
                    result.append("    ").append(key).append(": unknown_status\n");
                    break;
            }
        }

        result.append("}");
        return result.toString();
    }
}
