
package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatStylish(List<Map<String, Object>> file) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> map: file) {
            Object value = map.get("value");
            String key = (String) map.get("key");
            String status = map.get("status").toString();
            switch (status) {
                case "added":
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                    break;
                case "changed":
                    result.append("  - ").append(key).append(": ").append(map.get("value1")).append("\n");
                    result.append("  + ").append(key).append(": ").append(map.get("value2")).append("\n");
                    break;
                case "unchanged":
                    result.append("    ").append(key).append(": ").append(value).append("\n");
                    break;
                case "removed":
                    result.append("  - ").append(key).append(": ").append(value).append("\n");
                default:
                    break;
            }
        }
        result.append("}");
        return result.toString();
    }
}
