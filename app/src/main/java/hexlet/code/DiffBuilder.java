package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiffBuilder {

    public static List<DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        List<DiffNode> diffNodes = new ArrayList<>();

        // Find all keys from both maps
        var allKeys = new java.util.TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        // Process each key
        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data1.containsKey(key)) {
                // Key was added
                diffNodes.add(new DiffNode(key, null, value2, "added"));
            } else if (!data2.containsKey(key)) {
                // Key was removed
                diffNodes.add(new DiffNode(key, value1, null, "removed"));
            } else if (value1 == null && value2 == null) {
                // Both values are null (technically unchanged, but rare case)
                diffNodes.add(new DiffNode(key, null, null, "unchanged"));
            } else if (value1 == null || value2 == null || !value1.equals(value2)) {
                // Key was updated or one of the values is null
                diffNodes.add(new DiffNode(key, value1, value2, "updated"));
            } else {
                // Key is unchanged
                diffNodes.add(new DiffNode(key, value1, value2, "unchanged"));
            }
        }

        return diffNodes;
    }
}
