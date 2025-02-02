package hexlet.code;
import java.util.List;
import java.util.Map;
import hexlet.code.formatters.FormatterFactory;
import hexlet.code.DiffBuilder;
public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        List<Map<String, Object>> differences = DiffBuilder.build(filepath1, filepath2);

        // Debugging: Print differences before formatting
        System.out.println("Raw Differences: " + differences);

        // Retrieve the correct formatter
        return FormatterFactory.getFormatter(format).format(differences);
    }
}

