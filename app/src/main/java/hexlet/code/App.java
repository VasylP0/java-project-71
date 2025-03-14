package hexlet.code;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -jar app.jar <file1> <file2>");
            return;
        }

        String filepath1 = args[0];
        String filepath2 = args[1];

        // Removed third "format" argument to match the correct method signature
        System.out.println(Differ.generate(filepath1, filepath2));
    }
}
