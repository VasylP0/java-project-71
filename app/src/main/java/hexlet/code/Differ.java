package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

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

        List<Map<String, Object>> diffResult = computeDiff(data1, data2);

        return Formatter.format(diffResult, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static List<Map<String, Object>> computeDiff(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            Map<String, Object> diffEntry = new LinkedHashMap<>();

            boolean keyInData1 = data1.containsKey(key);
            boolean keyInData2 = data2.containsKey(key);

            if (keyInData1 && keyInData2) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);

                if (Objects.equals(value1, value2)) {
                    diffEntry.put("key", key);
                    diffEntry.put("status", "unchanged");
                    diffEntry.put("value", value1);
                } else {
                    diffEntry.put("key", key);
                    diffEntry.put("status", "changed");
                    diffEntry.put("value1", value1);
                    diffEntry.put("value2", value2);
                }
            } else if (keyInData1) {
                diffEntry.put("key", key);
                diffEntry.put("status", "removed");
                diffEntry.put("value", data1.get(key));
            } else {
                diffEntry.put("key", key);
                diffEntry.put("status", "added");
                diffEntry.put("value", data2.get(key));
            }

            diffList.add(diffEntry);
        }

        return diffList;
    }

    private static String getFileExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf(".");
        return (lastIndex == -1 || lastIndex == filePath.length() - 1) ? "" : filePath.substring(lastIndex + 1);
    }

    private static boolean isValidFormat(String format) {
        return format.equalsIgnoreCase("json") || format.equalsIgnoreCase("yml") || format.equalsIgnoreCase("yaml");
    }
}
