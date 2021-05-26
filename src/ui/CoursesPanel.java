package ui;

import javax.swing.*;

public class CoursesPanel extends JPanel {
    public static final String NAME = "coursesPanel";
    public static final String COURSES_LABEL_TEXT = "Courses";
    public static final String COURSES_LABEL_NAME = "coursesLabel";
    public static final String COURSES_LIST_NAME = "coursesList";
    public static final String ADD_BUTTON_TEXT = "Add";
    public static final String ADD_BUTTON_NAME = "addButton";
    public static final String DEPARTMENT_FIELD_NAME = "deptField";
    public static final String NUMBER_FIELD_NAME = "numberField";
    public static final String DEPARTMENT_LABEL_NAME = "deptLabel";
    public static final String NUMBER_LABEL_NAME = "numberLabel";
    public static final String DEPARTMENT_LABEL_TEXT = "Department";
    public static final String NUMBER_LABEL_TEXT = "Number";

    public static void main(String[] args) {
        show(new CoursesPanel());
    }

    private static void show(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public CoursesPanel() {
        setName(NAME);
        createLayout();
    }

    private void createLayout() {
        JLabel coursesLabel = createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
        JList coursesList = createList(COURSES_LIST_NAME);
        JButton addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);

        int columns = 20;

        JLabel departmentLabel = createLabel(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
        JTextField departmentField = createField(DEPARTMENT_FIELD_NAME, columns);
        JLabel numberLabel = createLabel(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
        JTextField numberField = createField(NUMBER_FIELD_NAME, columns);

        add(coursesLabel);
        add(coursesList);
        add(addButton);
        add(departmentLabel);
        add(departmentField);
        add(numberLabel);
        add(numberField);
    }

    private JLabel createLabel(String name, String text) {
        JLabel label = new JLabel(text);
        label.setName(name);
        return label;
    }

    private JList createList(String name) {
        JList list = new JList();
        list.setName(name);
        return list;
    }

    private JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    private JTextField createField(String name, int columns) {
        JTextField field = new JTextField(columns);
        field.setName(name);
        return field;
    }

    JLabel getLabel(String name) {
        return (JLabel) Util.getComponent(this, name);
    }

    JList getList(String name) {
        return (JList) Util.getComponent(this, name);
    }

    JButton getButton(String name) {
        return (JButton) Util.getComponent(this, name);
    }

    JTextField getField(String name) {
        return (JTextField) Util.getComponent(this, name);
    }
}