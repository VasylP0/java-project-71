package hexlet.code;

import java.util.*;

public class DiffBuilder {

    public static List<DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        List<DiffNode> diffNodes = new ArrayList<>();
        var allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            Object value1 = data1.getOrDefault(key, null);
            Object value2 = data2.getOrDefault(key, null);

            if (!data1.containsKey(key)) {
                diffNodes.add(new DiffNode(key, null, value2, "added"));
            } else if (!data2.containsKey(key)) {
                diffNodes.add(new DiffNode(key, value1, null, "removed"));
            } else if (!Objects.equals(value1, value2)) {
                diffNodes.add(new DiffNode(key, value1, value2, "updated"));
            } else {
                diffNodes.add(new DiffNode(key, value1, value2, "unchanged"));
            }
        }

        return diffNodes;
    }
}