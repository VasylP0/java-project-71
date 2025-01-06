package hexlet.code;

import java.util.List;
import hexlet.code.formatters.Formatter;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var data1 = Parser.parse(filePath1);
        var data2 = Parser.parse(filePath2);

        var diff = DiffBuilder.build(data1, data2);

        System.out.println("Generated Diff: " + diff);

        return Formatter.format(diff, formatName);
    }
}
