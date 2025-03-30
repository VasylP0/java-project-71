package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> data, String format) throws Exception {
        if (format == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }

        switch (format.toLowerCase()) {
            case "stylish":
                return Stylish.formatStylish(data);
            case "plain":
                return Plain.formatPlain(data);
            case "json":
                return Json.formatJson(data);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
