package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> data, String format) throws Exception {
        switch (format.toLowerCase()) {
            case "json":
                return Json.formatJson(data);
            case "plain":
                return Plain.formatPlain(data);
            default:
                return Stylish.formatStylish(data);
        }
    }
}
