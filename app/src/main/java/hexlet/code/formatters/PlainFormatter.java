package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> diff : differences) {
            String key = (String) diff.get("key");
            String type = (String) diff.get("type");

            switch (type) {
                case "added" -> result.append("Property '").append(key).append("' was added with value: ")
                        .append(diff.get("value")).append("\n");
                case "removed" -> result.append("Property '").append(key).append("' was removed\n");
                case "changed" -> result.append("Property '").append(key).append("' was updated. From ")
                        .append(diff.get("oldValue")).append(" to ").append(diff.get("newValue")).append("\n");
            }
        }

        return result.toString().trim();
    }
}
