package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class DiffBuilder {
    public static List<Map<String, Object>> build(String filepath1, String filepath2) throws Exception {
        // Read and parse both files
        String content1 = new String(Files.readAllBytes(Paths.get(filepath1)));
        String content2 = new String(Files.readAllBytes(Paths.get(filepath2)));

        // Debugging: Print file contents
        System.out.println("File 1 Content: " + content1);
        System.out.println("File 2 Content: " + content2);

        // Parse files into maps
        Map<String, Object> map1 = Parser.parse(content1, filepath1);
        Map<String, Object> map2 = Parser.parse(content2, filepath2);

        // Debugging: Print parsed maps
        System.out.println("Parsed File 1: " + map1);
        System.out.println("Parsed File 2: " + map2);

        return Comparator.compare(map1, map2);
    }
}
