package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.CoursesPanel.*;

public class CoursesPanelTest {
    private CoursesPanel panel;
    private boolean wasCliicked;

    @BeforeEach
    protected void setUp(){
        panel = new CoursesPanel();
    }

    @Test
    public void testCreate() {
        assertLabelText(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
        assertEmptyList(COURSES_LIST_NAME);
        assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        assertLabelText(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
        assertEmptyField(NUMBER_FIELD_NAME);
        assertLabelText(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
        assertEmptyField(NUMBER_FIELD_NAME);
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
