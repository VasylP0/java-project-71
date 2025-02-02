package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class DiffGenerator {

    public static List<DiffNode> generate(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffNode> diffNodes = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

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
