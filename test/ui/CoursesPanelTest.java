package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studentInfo.Course;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;
import static ui.CoursesPanel.*;

public class CoursesPanelTest {
    private CoursesPanel panel;
    private boolean wasCliicked;

    @BeforeEach
    protected void setUp() {
        panel = new CoursesPanel();
    }

    @Test
    public void testCreate() {
        assertEmptyList(COURSES_TABLE_NAME);
        assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);


        String[] fields = {FieldCatalog.DEPARTMENT_FIELD_NAME,
                FieldCatalog.NUMBER_FIELD_NAME,
                FieldCatalog.EFFECTIVE_DATE_FIELD_NAME};
        assertFields(fields);

        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertEquals(ADD_BUTTON_MNEMONIC, button.getMnemonic());
    }

    @Test
    public void testAddButtonClick() {
        JButton button = panel.getButton(ADD_BUTTON_NAME);

        wasCliicked = false;
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wasCliicked = true;
            }
        });

        button.doClick();
        assertTrue(wasCliicked);
    }

    @Test
    public void testAddCourse() {
        Course course = new Course("ENGL", "101");
        panel.addCourse(course);
        JList list = panel.getList(COURSES_LIST_NAME);
        ListModel model = list.getModel();
        assertEquals("ENGL-101", model.getElementAt(0).toString());
    }

    @Test
    public void testEnableDisable() {
        panel.setEnabled(ADD_BUTTON_NAME, true);
        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertTrue(button.isEnabled());

        panel.setEnabled(ADD_BUTTON_NAME, false);
        assertFalse(button.isEnabled());
    }

    @Test
    public void testAddListener() throws Exception {
        KeyListener listener = new KeyAdapter() {
        };
        panel.addFieldListener(DEPARTMENT_FIELD_NAME, listener);
        JTextField field = panel.getField(DEPARTMENT_FIELD_NAME);
        KeyListener[] listeners = field.getKeyListeners();
        assertEquals(1, listeners.length);
        assertSame(listener, listeners[0]);
    }

    private void assertFields(String[] fieldNames) {
        StatusBar statusBar = (StatusBar) Util.getComponent(panel, StatusBar.NAME);
        FieldCatalog catalog = new FieldCatalog();
        for (String fieldName : fieldNames) {
            JTextField field = panel.getField(fieldName);
            Field fieldSpec = catalog.get(fieldName);

            assertEquals(fieldSpec.getInfo(), statusBar.getInfo(field));
            assertLabelText(fieldSpec.getLabelName(), fieldSpec.getLabelText());
        }
    }

    private void assertLabelText(String name, String text) {
        JLabel label = panel.getLabel(name);
        assertEquals(text, label.getText());
    }

    private void assertEmptyField(String name) {
        JTextField field = panel.getField(name);
        assertEquals("", field.getText());
    }

    private void assertEmptyList(String name) {
        JList list = panel.getList(name);
        assertEquals(0, list.getModel().getSize());
    }

    private void assertButtonText(String name, String text) {
        JButton button = panel.getButton(name);
        assertEquals(text, button.getText());
    }
}
