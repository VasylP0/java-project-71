
package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class Json {
    public static String formatJson(List file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(file);
    }
}
