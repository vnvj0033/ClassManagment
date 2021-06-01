package ui;

import javax.swing.*;
import java.text.DateFormat;
import java.util.Date;

public class Field {
    private String fieldName;
    private JLabel label = new JLabel();
    private int colums;
    private int limit;
    private DateFormat dateFormat;
    private Date initialValue;

    public Field(String fieldName) {
        this.fieldName = fieldName;
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

    }

    public void setFormat(DateFormat format) {
        this.dateFormat = format;
    }

    public void setInitialValue(Date date) {
        initialValue = date;
    }

    public void setInitialValue(String value) {
    }

    public String getLabelName() {
        return label.getName();
    }

    public String getLabel() {
        return label.getName();
    }

    public boolean isUpcaseOnly() {
        return false;
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
        return null;
    }
}
