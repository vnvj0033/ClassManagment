package ui;

import studentInfo.Course;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import static java.awt.GridBagConstraints.*;

public class CoursesPanel extends JPanel {
    public static final String NAME = "coursesPanel";
    public static final String COURSES_LABEL_TEXT = "Courses";
    public static final String COURSES_LIST_NAME = "coursesList";
    public static final String ADD_BUTTON_TEXT = "Add";
    public static final String ADD_BUTTON_NAME = "addButton";
    public static final String DEPARTMENT_FIELD_NAME = "deptField";
    public static final String NUMBER_FIELD_NAME = "numberField";
    public static final String COURSES_TABLE_NAME = "coursesTable";

    static final char ADD_BUTTON_MNEMONIC = 'A';

    private JButton addButton;
    private CoursesTableModel coursesTableModel = new CoursesTableModel();
    private Status status;

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
        JTable coursesTable = createCoursesTable();
        JScrollPane coursesScroll = new JScrollPane(coursesTable);
        coursesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());

        final int pad = 6;
        Border emptyBorder = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder, COURSES_LABEL_TEXT);
        setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));

        add(coursesScroll, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JTable createCoursesTable() {
        JTable table = new JTable(coursesTableModel);
        table.setName(COURSES_TABLE_NAME);
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    JPanel createBottomPanel() {
        StatusBar statusBar = new StatusBar();
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        status = new Status(statusBar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(statusBar, BorderLayout.SOUTH);
        panel.add(createInputPanel(), BorderLayout.CENTER);
        return panel;

    }

    private JPanel createInputPanel() {
        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        addButton.setMnemonic(ADD_BUTTON_MNEMONIC);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addButton);
        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        panel.add(createFieldsPanel());

        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        return panel;
    }

    JPanel createFieldsPanel() {
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel(layout);
        int i = 0;
        FieldCatalog catalog = new FieldCatalog();

        for (String fieldName : getFieldName()) {
            Field fieldSpec = catalog.get(fieldName);
            JTextField textField = TextFieldFactory.create(fieldSpec);
            status.addText(textField, fieldSpec.getInfo());
            addField(panel, layout, i++, createLabel(fieldSpec), textField);
        }

        return panel;
    }

    private JLabel createLabel(Field fieldSpec) {
        return fieldSpec.getLabel();
    }

    private String[] getFieldName() {
        return new String[]{
                FieldCatalog.DEPARTMENT_FIELD_NAME,
                FieldCatalog.NUMBER_FIELD_NAME,
                FieldCatalog.EFFECTIVE_DATE_FIELD_NAME
        };
    }

    private void addField(JPanel panel, GridBagLayout layout, int row, JLabel label, JTextField textField) {

        Insets insets = new Insets(3, 3, 3, 3);
        layout.setConstraints(label, new GridBagConstraints(
                0, row,
                1, 1,
                40, 1,
                LINE_END, NONE,
                insets,
                0, 0));
        layout.setConstraints(textField, new GridBagConstraints(
                1, row,
                2, 1,
                60, 1,
                CENTER,
                HORIZONTAL,
                insets,
                0, 0));

        panel.add(label);
        panel.add(textField);
    }

    private JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    void addCourse(Course course) {
        coursesTableModel.add(course);
    }

    Course getCourse(int index) {
        return coursesTableModel.get(index);
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    void setText(String textFieldName, String text) {
        getField(textFieldName).setText(text);
    }

    void setEnabled(String name, boolean state) {
        getButton(name).setEnabled(state);
    }

    void addFieldListener(String name, KeyListener listener) {
        getField(name).addKeyListener(listener);
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

    String getText(String textFieldName) {
        return getField(textFieldName).getText();
    }
}
