package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import hexlet.code.formatters.Formatter;

public class Differ {

    public static String generate(Path filePath1, Path filePath2, String format) throws Exception {
        var data1 = Parser.parse(Files.readString(filePath1));
        var data2 = Parser.parse(Files.readString(filePath2));

        var diff = DiffBuilder.build(data1, data2);

        System.out.println("Generated Diff: " + diff);

        return Formatter.format(diff, format);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        return generate(Path.of(filePath1), Path.of(filePath2), format);
    }
}
