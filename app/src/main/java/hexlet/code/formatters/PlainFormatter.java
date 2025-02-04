package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class PlainFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diff) {
        StringJoiner result = new StringJoiner("\n");
        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");
            switch (type) {
                case "added" -> result.add("Property '" + key + "' was added with value: " + entry.get("value"));
                case "removed" -> result.add("Property '" + key + "' was removed");
                case "changed" -> result.add("Property '" + key + "' was updated. From " +
                        entry.get("oldValue") + " to " + entry.get("newValue"));
            }
        }
        return result.toString();
    }
}
