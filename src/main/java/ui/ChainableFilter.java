package ui;

import javax.swing.text.DocumentFilter;
import java.util.ArrayList;
import java.util.List;

public class ChainableFilter extends DocumentFilter {
    private List<DocumentFilter> filters;
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

        return filters.get(index++);
    }
}