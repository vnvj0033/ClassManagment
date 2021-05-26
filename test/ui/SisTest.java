package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(containsCoursesPanel(frame));
    }

    @Test
    public void testShow() {
        sis.show();
        assertTrue(frame.isVisible());
    }

    @AfterEach
    protected void tearDown() {
        sis.close();
    }

    private boolean containsCoursesPanel(JFrame frame) {
        Container pane = frame.getContentPane();
        for (Component component : pane.getComponents())
            if (component instanceof CoursesPanel)
            return true;
        return false;
    }
}
