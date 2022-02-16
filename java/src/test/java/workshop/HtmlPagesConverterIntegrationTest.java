package workshop;

import org.junit.Test;
import workshop.html.PlaintextToHtmlConverter;

import static org.junit.Assert.assertEquals;

public class HtmlPagesConverterIntegrationTest {
    @Test
    public void convertFromActualFile() throws Exception {
        PlaintextToHtmlConverter converter = new PlaintextToHtmlConverter();
        assertEquals("abc<br />&lt;hello&gt;", converter.toHtml());
    }
}
