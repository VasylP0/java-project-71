package hexlet.code;

import java.util.*;

public class DiffGenerator {

    public static List<Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> diffList = new ArrayList<>();

        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            Map<String, Object> diffEntry = new HashMap<>();
            diffEntry.put("key", key);

            if (!data1.containsKey(key)) {
                diffEntry.put("type", "added");
                diffEntry.put("value", data2.get(key));
            } else if (!data2.containsKey(key)) {
                diffEntry.put("type", "removed");
                diffEntry.put("value", data1.get(key));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                diffEntry.put("type", "changed");
                diffEntry.put("oldValue", data1.get(key));
                diffEntry.put("newValue", data2.get(key));
            } else {
                diffEntry.put("type", "unchanged");
                diffEntry.put("value", data1.get(key));
            }

            diffList.add(diffEntry);
        }
        return diffList;
    }
}
