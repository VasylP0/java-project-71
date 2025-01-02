package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);

        List<DiffNode> diff = DiffBuilder.build(data1, data2);

        return Formatter.format(diff, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        // Default to "stylish" format
        return generate(filePath1, filePath2, "stylish");
    }
}
