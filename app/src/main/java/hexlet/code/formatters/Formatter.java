package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;

public class Formatter {

    public static String format(List<DiffNode> diffNodes, String formatName) {
        switch (formatName) {
            case "json":
                return JsonFormatter.format(diffNodes);
            default:
                throw new IllegalArgumentException("Unknown format: " + formatName);
        }
    }
}