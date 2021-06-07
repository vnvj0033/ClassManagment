package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class UpcaseFilter extends ChainableFilter {
    public void insertString(
            DocumentFilter.FilterBypass bypass,
            int offset,
            String text,
            AttributeSet attr) throws BadLocationException {
        bypass.insertString(offset, text.toUpperCase(), attr);
    }

    public void replace(
            DocumentFilter.FilterBypass bypass,
            int offset,
            int length,
            String text,
            AttributeSet attr) throws BadLocationException {
        bypass.replace(offset, length, text.toUpperCase(), attr);
    }
}