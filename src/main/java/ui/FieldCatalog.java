package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FieldCatalog {
    public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
    static final String DEPARTMENT_FIELD_NAME = "deptField";
    static final String DEPARTMENT_LABEL_TEXT = "Department";
    static final int DEPARTMENT_FIELD_LIMIT = 4;
    static final String NUMBER_FIELD_NAME = "numberField";
    static final String NUMBER_LABEL_TEXT = "Number";
    static final int NUMBER_FIELD_LIMIT = 3;
    static final String EFFECTIVE_DATE_FIELD_NAME = "effectiveDateField";
    static final String EFFECTIVE_DATE_LABEL_TEXT = "Effective Date";
    static final int DEFAULT_COLUMNS = 20;
    static final String DEPARTMENT_FIELD_INFO = "Enter a 4-character department designation.";
    static final String NUMBER_FIELD_INFO = "The department number should be 3 digits.";
    static final String EFFECTIVE_DATE_FIELD_INFO = "Effective date should be in mm/dd/yy format.";

    private Map<String, Field> fields;

    public FieldCatalog() {
        loadFields();
    }

    public int size() {
        return fields.size();
    }

    private void loadFields() {
        fields = new HashMap<>();

        Field fieldSpec = new Field(DEPARTMENT_FIELD_NAME);
        fieldSpec.setLabel(DEPARTMENT_LABEL_TEXT);
        fieldSpec.setLimit(DEPARTMENT_FIELD_LIMIT);
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setUpcaseOnly();
        fieldSpec.setInfo(DEPARTMENT_FIELD_INFO);
        put(fieldSpec);

        fieldSpec = new Field(NUMBER_FIELD_NAME);
        fieldSpec.setLabel(NUMBER_LABEL_TEXT);
        fieldSpec.setLimit(NUMBER_FIELD_LIMIT);
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setInfo(NUMBER_FIELD_INFO);
        put(fieldSpec);

        fieldSpec = new Field(EFFECTIVE_DATE_FIELD_NAME);
        fieldSpec.setLabel(EFFECTIVE_DATE_LABEL_TEXT);
        fieldSpec.setFormat(DEFAULT_DATE_FORMAT);
        fieldSpec.setInitialValue(new Date());
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setInfo(EFFECTIVE_DATE_FIELD_INFO);
        put(fieldSpec);
    }

    private void put(Field fieldSpec) {
        fields.put(fieldSpec.getName(), fieldSpec);
    }

    public Field get(String fieldName) {
        return fields.get(fieldName);
    }
}