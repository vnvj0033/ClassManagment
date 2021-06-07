package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.FieldCatalog.*;

public class FieldCatalogTest {

    @Test
    public void testAllFields() {
        FieldCatalog catalog = new FieldCatalog();

        assertEquals(3, catalog.size());

        Field field = catalog.get(NUMBER_FIELD_NAME);
        assertEquals(DEFAULT_COLUMNS, field.getColumns());
        assertEquals(NUMBER_LABEL_TEXT, field.getLabelText());
        assertEquals(NUMBER_FIELD_LIMIT, field.getLimit());

        field = catalog.get(DEPARTMENT_FIELD_NAME);
        assertEquals(DEFAULT_COLUMNS, field.getColumns());
        assertEquals(DEPARTMENT_LABEL_TEXT, field.getLabelText());
        assertEquals(DEPARTMENT_FIELD_LIMIT, field.getLimit());
        assertTrue(field.isUpcaseOnly());

        field = catalog.get(EFFECTIVE_DATE_FIELD_NAME);
        assertEquals(DEFAULT_COLUMNS, field.getColumns());
        assertEquals(EFFECTIVE_DATE_LABEL_TEXT, field.getLabelText());
        assertSame(DEFAULT_DATE_FORMAT, field.getFormat());
    }
}