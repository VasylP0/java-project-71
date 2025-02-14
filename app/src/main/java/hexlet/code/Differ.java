package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> data1 = Parser.parse(Files.readString(Paths.get(filepath1)));
        Map<String, Object> data2 = Parser.parse(Files.readString(Paths.get(filepath2)));

        return generateDiff(data1, data2);
    }

    private static String generateDiff(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : data1.keySet()) {
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (!data1.get(key).equals(data2.get(key))) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        }

        for (String key : data2.keySet()) {
            if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
