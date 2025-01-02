package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void testJsonFormat() throws Exception {
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        // Debugging: Log file paths
        System.out.println("File Path 1 Exists: " + Files.exists(Path.of(filePath1)));
        System.out.println("File Path 2 Exists: " + Files.exists(Path.of(filePath2)));

        String result = Differ.generate(filePath1, filePath2, "json");

        // Debugging: Log the actual JSON result
        System.out.println("Generated JSON:\n" + result);

        String expectedJson = """
        [
          {
            "key" : "chars2",
            "oldValue" : [ "d", "e", "f" ],
            "newValue" : false,
            "status" : "updated"
          },
          {
            "key" : "checked",
            "oldValue" : false,
            "newValue" : true,
            "status" : "updated"
          },
          {
            "key" : "default",
            "oldValue" : null,
            "newValue" : [ "value1", "value2" ],
            "status" : "updated"
          },
          {
            "key" : "id",
            "oldValue" : 45,
            "newValue" : null,
            "status" : "updated"
          },
          {
            "key" : "key1",
            "oldValue" : "value1",
            "newValue" : null,
            "status" : "removed"
          },
          {
            "key" : "key2",
            "oldValue" : null,
            "newValue" : "value2",
            "status" : "added"
          },
          {
            "key" : "numbers2",
            "oldValue" : [ 2, 3, 4, 5 ],
            "newValue" : [ 22, 33, 44, 55 ],
            "status" : "updated"
          },
          {
            "key" : "numbers3",
            "oldValue" : [ 3, 4, 5 ],
            "newValue" : null,
            "status" : "removed"
          },
          {
            "key" : "numbers4",
            "oldValue" : null,
            "newValue" : [ 4, 5, 6 ],
            "status" : "added"
          },
          {
            "key" : "obj1",
            "oldValue" : null,
            "newValue" : {
              "nestedKey" : "value",
              "isNested" : true
            },
            "status" : "added"
          },
          {
            "key" : "setting1",
            "oldValue" : "Some value",
            "newValue" : "Another value",
            "status" : "updated"
          },
          {
            "key" : "setting2",
            "oldValue" : 200,
            "newValue" : 300,
            "status" : "updated"
          },
          {
            "key" : "setting3",
            "oldValue" : true,
            "newValue" : "none",
            "status" : "updated"
          }
        ]""";

        assertThat(result).isEqualToIgnoringWhitespace(expectedJson);
    }
}
