package ui;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoursesPanelTest {

    @Test
    public void testCreate() {
        CoursesPanel panel = new CoursesPanel();
        JLabel label = getLabel(panel);
        assertEquals(CoursesPanel.LABEL_TEXT, label.getText());
    }

    private JLabel getLabel(JPanel panel){
        for (Component component: panel.getComponents())
            if (component instanceof JLabel)
                return (JLabel) component;
            return null;
    }
}
