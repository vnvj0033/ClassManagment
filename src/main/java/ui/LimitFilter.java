package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimitFilter extends ChainableFilter {
    private int limit;

    public LimitFilter(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void insertString(
            DocumentFilter.FilterBypass bypass,
            int offset,
            String str,
            AttributeSet attrSet) throws BadLocationException {
        replace(bypass, offset, 0, str, attrSet);
    }

    public void replace(
            DocumentFilter.FilterBypass bypass,
            int offset,
            int length,
            String str,
            AttributeSet attrSet) throws BadLocationException {
        int newLength = bypass.getDocument().getLength() - length + str.length();
        if (newLength > limit)
            throw new BadLocationException("New characters exceeds max size of document", offset);
        bypass.replace(offset, length, str, attrSet);
    }
}