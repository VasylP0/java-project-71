package hexlet.code;

import java.util.*;

public class Comparator {
    public static List<Map<String, Object>> compare(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();

        for (String key : allKeys) {
            Map<String, Object> diffEntry = new HashMap<>();
            diffEntry.put("key", key);

            if (!map1.containsKey(key)) {
                diffEntry.put("type", "added");
                diffEntry.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                diffEntry.put("type", "removed");
                diffEntry.put("value", map1.get(key));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                diffEntry.put("type", "changed");
                diffEntry.put("oldValue", map1.get(key));
                diffEntry.put("newValue", map2.get(key));
            } else {
                diffEntry.put("type", "unchanged");
                diffEntry.put("value", map1.get(key));
            }

            diffList.add(diffEntry);
        }
        return diffList;
    }
}
