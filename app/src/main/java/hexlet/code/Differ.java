package hexlet.code;

import hexlet.code.formatters.FormatterFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish"); // ✅ Default to "stylish"
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String fileType1 = getFileType(filepath1);
        String fileType2 = getFileType(filepath2);

        Map<String, Object> data1 = Parser.parse(Files.readString(Paths.get(filepath1)), fileType1);
        Map<String, Object> data2 = Parser.parse(Files.readString(Paths.get(filepath2)), fileType2);

        var diff = DiffGenerator.genDiff(data1, data2);
        return FormatterFactory.getFormatter(format).format(diff);
    }

    private static String getFileType(String filepath) {
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }
}
