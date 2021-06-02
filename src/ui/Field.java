package ui;

import javax.swing.*;
import java.text.DateFormat;

public class Field {
    static final String DEPARTMENT_LABEL_NAME = "department label name";
    static final String NUMBER_LABEL_NAME = "number label name";
    static final String EFFECTIVE_DATE_LABEL_NAME = "effective label name";

    private String fieldName;
    private JLabel label = new JLabel();
    private int colums;
    private int limit;
    private DateFormat dateFormat;
    private boolean isUpcase = false;
    private Object initialValue;

    public Field(String fieldName) {
        this.fieldName = fieldName;
        if (fieldName.equals(FieldCatalog.DEPARTMENT_FIELD_NAME))
            label.setName(DEPARTMENT_LABEL_NAME);
        if (fieldName.equals(FieldCatalog.NUMBER_FIELD_NAME))
            label.setName(NUMBER_LABEL_NAME);
        if (fieldName.equals(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME))
            label.setName(EFFECTIVE_DATE_LABEL_NAME);
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setColumns(int columns) {
        this.colums = columns;
    }

    public void setUpcaseOnly() {
        isUpcase = true;
    }

    public void setFormat(DateFormat format) {
        this.dateFormat = format;
    }

    public void setInitialValue(Object obj) {
        this.initialValue = obj;
    }

    public String getLabelName() {
        return label.getName();
    }

    public String getLabelText() {
        return label.getText();
    }


    public boolean isUpcaseOnly() {
        return isUpcase;
    }

    public int getColumns() {
        return colums;
    }

    public String getName() {
        return fieldName;
    }

    public int getLimit() {
        return limit;
    }

    public DateFormat getFormat() {
        return dateFormat;
    }

    public Object getInitialValue() {
        return initialValue;
    }

    public JLabel getLabel() {
        return label;
    }

    public String getShortName() {
        String shortName = "";
        if (fieldName.equals(FieldCatalog.DEPARTMENT_FIELD_NAME))
            shortName = "dept";
        if (fieldName.equals(FieldCatalog.NUMBER_FIELD_NAME))
            shortName = "num";
        if (fieldName.equals(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME))
            shortName = "eff";
        return shortName;
    }
}
