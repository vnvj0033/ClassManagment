package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.SwingUtil;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusBarTest {
    private JTextField field1;
    private JTextField field2;
    private StatusBar statusBar;
    private JFrame frame;

    @BeforeEach
    protected void setUp() {
        field1 = new JTextField(10);
        field2 = new JTextField(10);
        statusBar = new StatusBar();

        JPanel panel = SwingUtil.createPanel(field1, field2, statusBar);
        frame = SwingUtil.createFrame(panel);
    }

    @Test
    public void testInfo() {
        statusBar.setInfo(field1, "a");
        assertEquals("a", statusBar.getInfo(field1));
    }

    @Test
    public void testMouseover() throws Exception {
        final String text1 = "text1";
        final String text2 = "text2";

        statusBar.setInfo(field1, text1);
        statusBar.setInfo(field2, text2);

        Robot robot = new Robot();

        Point field1Location = field1.getLocationOnScreen();

        robot.mouseMove(field1Location.x - 1, field1Location.y - 1);
        Thread.sleep(100);
        assertEquals("", statusBar.getText().trim());

        robot.mouseMove(field1Location.x + 1, field1Location.y + 1);
        Thread.sleep(100);
        assertEquals(text1, statusBar.getText());

        Point field2Location = field2.getLocationOnScreen();
        robot.mouseMove(field2Location.x + 1, field2Location.y + 1);
        Thread.sleep(100);
        assertEquals(text2, statusBar.getText());
    }

    @AfterEach
    protected void tearDown() {
        frame.dispose();
    }
}
