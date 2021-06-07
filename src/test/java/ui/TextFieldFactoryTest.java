package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextFieldFactoryTest {
    private Field fieldSpec;
    private static final String FIELD_NAME = "fieldName";
    private static final int COLUMNS = 1;

    @BeforeEach
    protected void setUp() {
        fieldSpec = new Field(FIELD_NAME);
        fieldSpec.setColumns(COLUMNS);
    }

    @Test
    public void testCreateSimpleField() {
        final String textValue = "value";
        fieldSpec.setInitialValue(textValue);

        JTextField field = TextFieldFactory.create(fieldSpec);

        assertEquals(COLUMNS, field.getColumns());
        assertEquals(FIELD_NAME, field.getName());
        assertEquals(textValue, field.getText());
    }

    @Test
    public void testLimit() {
        final int limit = 3;
        fieldSpec.setLimit(limit);

        JTextField field = TextFieldFactory.create(fieldSpec);

        AbstractDocument document = (AbstractDocument) field.getDocument();
        ChainableFilter filter = (ChainableFilter) document.getDocumentFilter();
        assertEquals(limit, ((LimitFilter) filter).getLimit());
    }

    @Test
    public void testUpcase() {
        fieldSpec.setUpcaseOnly();

        JTextField field = TextFieldFactory.create(fieldSpec);

        AbstractDocument document = (AbstractDocument) field.getDocument();
        ChainableFilter filter = (ChainableFilter) document.getDocumentFilter();
        assertEquals(UpcaseFilter.class, filter.getClass());
    }

    @Test
    public void testMultipleFilters() {
        fieldSpec.setLimit(3);
        fieldSpec.setUpcaseOnly();

        JTextField field = TextFieldFactory.create(fieldSpec);

        AbstractDocument document = (AbstractDocument) field.getDocument();
        ChainableFilter filter = (ChainableFilter) document.getDocumentFilter();

        Set<Class> filters = new HashSet<>();
        filters.add(filter.getClass());
        filters.add(filter.getNext().getClass());
        assertTrue(filters.contains(LimitFilter.class));
        assertTrue(filters.contains(UpcaseFilter.class));
    }

    @Test
    public void testCreateFormattedField() {
        final int year = 2006;
        final int month = 3;
        final int day = 17;
        fieldSpec.setInitialValue(DateUtil.createDate(year, month, day));
        final String pattern = "MM/dd/yy";
        fieldSpec.setFormat(new SimpleDateFormat(pattern));

        JFormattedTextField field = (JFormattedTextField) TextFieldFactory.create(fieldSpec);

        assertEquals(1, field.getColumns());
        assertEquals(FIELD_NAME, field.getName());

        DateFormatter formatter = (DateFormatter) field.getFormatter();
        SimpleDateFormat format = (SimpleDateFormat) formatter.getFormat();
        assertEquals(pattern, format.toPattern());
        assertEquals(Date.class, field.getValue().getClass());
        assertEquals("03/17/06", field.getText());

        TestUtil.assertDateEquals(year, month, day, (Date) field.getValue());
    }
}