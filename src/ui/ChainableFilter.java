package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.ArrayList;
import java.util.List;

public class ChainableFilter extends DocumentFilter {
    private List<DocumentFilter> filters;
    private AttributeSet attr;
    private int index = 0;

    public ChainableFilter() {
        filters = new ArrayList<>(25);
    }


    public void setNext(DocumentFilter filter) {
        filters.add(filter);
    }

    public DocumentFilter getNext() {
        if (index >= filters.size())
            return null;

        return filters.get(++index);
    }

    public void removeFilter(DocumentFilter filter) {
        filters.remove(filter);
    }

    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        for (DocumentFilter filter : filters)
            filter.insertString(fb, offset, string, attr);
    }

    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
        for (DocumentFilter filter : filters) {
            filter.remove(fb, offset, length);
        }
    }

    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        for (DocumentFilter filter : filters)
            filter.replace(fb, offset, length, text, attrs);
    }



}