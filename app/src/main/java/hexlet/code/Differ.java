package hexlet.code;

import hexlet.code.formatters.FormatterFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String file1Content = Files.readString(Paths.get(filepath1));
        String file2Content = Files.readString(Paths.get(filepath2));

        String fileType1 = Parser.getFileType(filepath1);
        String fileType2 = Parser.getFileType(filepath2);

        Map<String, Object> data1 = Parser.parse(file1Content, fileType1);
        Map<String, Object> data2 = Parser.parse(file2Content, fileType2);

        List<Map<String, Object>> diff = DiffGenerator.genDiff(data1, data2);

        return FormatterFactory.getFormatter(format).format(diff);
    }
}
