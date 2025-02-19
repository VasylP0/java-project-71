
package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatPlain(List<Map<String, Object>> file) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> map : file) {
            Object value = map.get("value");
            String key = (String) map.get("key");
            String status = map.get("status").toString();
            switch (status) {
                case "added":
                    result.append("Property '")
                            .append(key).append("' was added with value: ").append(property(value)).append("\n");
                    break;
                case "changed":
                    result.append("Property '")
                            .append(key).append("' was updated. From ")
                            .append(property(map.get("value1")))
                            .append(" to ").append(property(map.get("value2"))).append("\n");
                    break;
                case "unchanged":
                    break;
                case "removed":
                    result.append("Property '").append(key).append("' was removed").append("\n");
                default:
                    break;
            }
        }
        return result.toString().trim();
    }
    private static String property(Object value) {
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
