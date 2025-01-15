package hexlet.code;
import hexlet.code.formatters.DiffGenerator;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);

        List<DiffNode> diff = DiffGenerator.generate(data1, data2);
        Formatter formatter = FormatterFactory.getFormatter(format);

        return formatter.format(diff, format);
    }
}
