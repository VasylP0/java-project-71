package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");

            switch (type) {
                case "added":
                    result.append(String.format("Property '%s' was added with value: %s%n", key, formatValue(entry.get("value"))));
                    break;
                case "removed":
                    result.append(String.format("Property '%s' was removed%n", key));
                    break;
                case "changed":
                    result.append(String.format("Property '%s' was updated. From %s to %s%n",
                            key, formatValue(entry.get("oldValue")), formatValue(entry.get("newValue"))));
                    break;
                default:
                    break;
            }
        }
        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
