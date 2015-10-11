import static java.lang.System.out;

import java.util.List;

public class SaxColors {
    static List<Color> colors;
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            out.println("Usage: java SaxColors xml-file-to-process");
            System.exit(1);
        }

        Fetcher f = new Fetcher(args[0]);
        colors = f.fetch();

        if (colors != null) {
            for (Color c : colors) {
                out.println("Color: |" + c.toString() + "|");
            }
        } else {
            out.println("parser returned null list of colors.");
        }
    }
}
