package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.join;
import static java.lang.String.valueOf;

public class PlaintextToHtmlConverter {
    public String toHtml() throws Exception {
        String text = read();
        return basicHtmlEncode(text);
    }

    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get("sample.txt")));
    }

    private String basicHtmlEncode(String source) {
        List<String> result = new ArrayList<>();
        List<String> convertedLine = new ArrayList<>();

        for (int i=0; i < source.length(); i++) {
            switch (source.charAt(i)) {
                case '<':
                    convertedLine.add("&lt;");
                    break;
                case '>':
                    convertedLine.add("&gt;");
                    break;
                case '&':
                    convertedLine.add("&amp;");
                    break;
                case '\n':
                    result.add(join("", convertedLine));
                    convertedLine = new ArrayList<>();
                    break;
                default:
                    convertedLine.add( valueOf(source.charAt(i)) );
            }
        }
        result.add(join("", convertedLine));
        return join("<br />", result);
    }
}
