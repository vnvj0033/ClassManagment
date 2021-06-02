package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studentInfo.Course;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.jupiter.api.Assertions.*;

public class SisTest {
    private Sis sis;
    private JFrame frame;
    private CoursesPanel panel;
    private Robot robot;

    @BeforeEach
    protected void setUp() throws Exception {
        sis = new Sis();
        frame = sis.getFrame();
        panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        robot = new Robot();
    }

    @Test
    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(Sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(Sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(Util.getComponent(frame, CoursesPanel.NAME));
        assertEquals(Sis.COURSES_TITLE, frame.getTitle());

        CoursesPanel panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        assertNotNull(panel);

        verifyFilter(panel);

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
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "MATH");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);

        button.doClick();

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }


    @Test
    public void testKeyListeners() throws Exception {
        sis.show();

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());
        selectField(CoursesPanel.DEPARTMENT_FIELD_NAME);
        type('A');
        Thread.sleep(300);
        selectField(CoursesPanel.NUMBER_FIELD_NAME);
        type('1');
        Thread.sleep(300);
        assertTrue(button.isEnabled());
    }

    @Test
    public void testSetAddButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());

        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "a");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "1");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());

        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, " ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "a");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "  ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());
    }

    private void selectField(String name) throws Exception {
        JTextField field = panel.getField(name);
        Point point = field.getLocationOnScreen();
        robot.mouseMove(point.x, point.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void type(int key) throws Exception {
        robot.keyPress(key);
        robot.keyRelease(key);
    }

    private void verifyFilter(CoursesPanel panel) {
        DocumentFilter filter = getFilter(panel, CoursesPanel.DEPARTMENT_FIELD_NAME);
        assertTrue(filter.getClass() == UpcaseFilter.class);
    }

    private DocumentFilter getFilter(CoursesPanel panel, String fieldName) {
        JTextField field = panel.getField(fieldName);
        AbstractDocument document = (AbstractDocument) field.getDocument();
        return document.getDocumentFilter();
    }


    @AfterEach
    protected void tearDown() {
        sis.close();
    }
}
