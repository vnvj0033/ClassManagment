package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studentInfo.Course;
import util.ImageUtil;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SisTest {
    private Sis sis;
    private JFrame frame;

    @BeforeEach
    protected void setUp() {
        sis = new Sis();
        frame = sis.getFrame();
    }

    @Test
    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(Sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(Sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNull(Util.getComponent(frame, CoursesPanel.NAME));
        assertEquals(Sis.COURSES_TITLE, frame.getTitle());

        Image image = frame.getIconImage();
        assertEquals(image, ImageUtil.create("/images/courses.gif"));
    }

    @Test
    public void testShow() {
        sis.show();
        assertTrue(frame.isVisible());
    }

    @Test
    public void testAddCourse() {
        CoursesPanel panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "MATH");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        button.doClick();

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    @AfterEach
    protected void tearDown() {
        sis.close();
    }
}
