package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diff) { // ❌ Remove 'static'
        return "Stylish formatted output"; // Replace with actual logic
    }
}
