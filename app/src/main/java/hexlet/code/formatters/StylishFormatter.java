package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diff) {
        StringJoiner result = new StringJoiner("\n", "{\n", "\n}");
        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");
            switch (type) {
                case "added" -> result.add("  + " + key + ": " + entry.get("value"));
                case "removed" -> result.add("  - " + key + ": " + entry.get("value"));
                case "changed" -> {
                    result.add("  - " + key + ": " + entry.get("oldValue"));
                    result.add("  + " + key + ": " + entry.get("newValue"));
                }
                case "unchanged" -> result.add("    " + key + ": " + entry.get("value"));
            }
        }
        return result.toString();
    }
}
