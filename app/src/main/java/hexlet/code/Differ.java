package hexlet.code;

import hexlet.code.differ.DiffBuilder;
import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import java.util.List;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath();
        Path path2 = Paths.get(filePath2).toAbsolutePath();

        if (!Files.exists(path1) || !Files.exists(path2)) {
            throw new IllegalArgumentException("Error: One or both input files do not exist.");
        }

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        String fileFormat1 = getFileExtension(filePath1);
        String fileFormat2 = getFileExtension(filePath2);

        if (!isValidFormat(fileFormat1) || !isValidFormat(fileFormat2)) {
            throw new IllegalArgumentException("Error: Unsupported file format. Only JSON and YAML are supported.");
        }

        Map<String, Object> data1 = Parser.parse(content1, fileFormat1);
        Map<String, Object> data2 = Parser.parse(content2, fileFormat2);

        List<Map<String, Object>> diffResult = DiffBuilder.build(data1, data2);

        return Formatter.format(diffResult, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static String getFileExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf(".");
        return (lastIndex == -1 || lastIndex == filePath.length() - 1)
                ? ""
                : filePath.substring(lastIndex + 1);
    }

    private static boolean isValidFormat(String format) {
        return format.equalsIgnoreCase("json")
                || format.equalsIgnoreCase("yml")
                || format.equalsIgnoreCase("yaml");
    }
}
