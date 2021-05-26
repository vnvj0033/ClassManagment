package ui;

import javax.swing.*;

public class CoursesPanel extends JPanel {
    public static final String NAME = "coursesPanel";
    public static final String LABEL_TEXT = "Courses";
    public static final String LABEL_NAME = "coursesLabel";
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
        JLabel label = new JLabel(LABEL_TEXT);
        label.setName(LABEL_NAME);

        JList list = new JList();
        list.setName(COURSES_LIST_NAME);

        JButton addButton = new JButton(ADD_BUTTON_TEXT);
        addButton.setName(ADD_BUTTON_NAME);

        int columns = 20;

        JLabel departmentLabel = new JLabel(DEPARTMENT_LABEL_TEXT);
        departmentLabel.setName(DEPARTMENT_LABEL_NAME);

        JTextField departmentField = new JTextField(columns);
        departmentField.setName(DEPARTMENT_FIELD_NAME);

        JLabel numberLabel = new JLabel(NUMBER_LABEL_TEXT);
        numberLabel.setName(NUMBER_LABEL_NAME);

        JTextField numberField = new JTextField(columns);
        numberField.setName(NUMBER_FIELD_NAME);

        add(label);
        add(list);
        add(addButton);
        add(departmentLabel);
        add(departmentField);
        add(numberLabel);
        add(numberField);
    }
}
