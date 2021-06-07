package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpcaseFilterTest {
    private DocumentFilter filter;
    protected DocumentFilter.FilterBypass bypass;
    protected AbstractDocument document;

    @BeforeEach
    protected void setUp() {
        bypass = createBypass();
        document = (AbstractDocument) bypass.getDocument();
        filter = new UpcaseFilter();
    }

    @Test
    public void testInsert() throws BadLocationException {
        filter.insertString(bypass, 0, "abc", null);
        assertEquals("ABC", documentText());

        filter.insertString(bypass, 1, "def", null);
        assertEquals("ADEFBC", documentText());
    }

    @Test
    public void testReplace() throws BadLocationException {
        filter.insertString(bypass, 0, "XYZ", null);
        filter.replace(bypass, 1, 2, "tc", null);
        assertEquals("XTC", documentText());
        filter.replace(bypass, 0, 3, "p8A", null);
        assertEquals("P8A", documentText());
    }

    protected String documentText() throws BadLocationException {
        return document.getText(0, document.getLength());
    }

    protected DocumentFilter.FilterBypass createBypass() {
        return new DocumentFilter.FilterBypass() {
            private AbstractDocument document = new PlainDocument();

            public Document getDocument() {
                return document;
            }

            public void insertString(int offset, String string, AttributeSet attr) {
                try {
                    document.insertString(offset, string, attr);
                } catch (BadLocationException e) {
                }
            }

            public void remove(int offset, int length) {
            }

            public void replace(int offset, int length, String string, AttributeSet attrs) {
                try {
                    document.replace(offset, length, string, attrs);
                } catch (BadLocationException e) { }
            }
        };
    }
}