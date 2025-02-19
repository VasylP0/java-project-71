
package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String fileFormat1 = getFileType(filepath1);
        String fileFormat2 = getFileType(filepath2);

        //распарсили
        Map<String, Object> file1 = Parser.parse(content1, fileFormat1);
        Map<String, Object> file2 = Parser.parse(content2, fileFormat2);

        List<Map<String, Object>> compareResult = Contrast.compare(file1, file2);
        return Formatter.formatterForm(compareResult, format);
    }
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    public static String readFile(String filePath) throws Exception {
        //Читаем содержимое файла
        var path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }
    private static String getFileType(String filePath) {
        // возвращает расширение файла, split разделяем файл по точке
        String[] expansion = filePath.split("\\.");

        return expansion[expansion.length - 1];
    }
}
