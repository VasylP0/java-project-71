package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String format(List<DiffNode> diffNodes, String formatName) {
        switch (formatName) {
            case "plain":
                return PlainFormatter.format(diffNodes);
            case "json":
                return JsonFormatter.format(diffNodes);
            case "stylish":
            default:
                return StylishFormatter.format(diffNodes);
        }
    }
}
