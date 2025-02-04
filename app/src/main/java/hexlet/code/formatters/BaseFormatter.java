package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class BaseFormatter {
    public static String format(List<Map<String, Object>> diff, String formatType) {
        return FormatterFactory.getFormatter(formatType).format(diff);
    }
}
