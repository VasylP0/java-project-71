package hexlet.code.differ;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class DiffBuilder {

    public static List<Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
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
}
