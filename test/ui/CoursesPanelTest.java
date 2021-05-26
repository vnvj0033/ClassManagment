package ui;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoursesPanelTest {

    @Test
    public void testCreate() {
        CoursesPanel panel = new CoursesPanel();
        JLabel label = (JLabel) Util.getComponent(panel, CoursesPanel.NAME);
        assertEquals(CoursesPanel.LABEL_TEXT, label.getText());
    }
}
