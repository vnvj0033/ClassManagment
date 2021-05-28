package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UtilTest {
    private JPanel panel;

    @BeforeEach
    protected void setUp() {
        panel = new JPanel();
    }

    @Test
    public void testNotFound() {
        assertNull(Util.getComponent(panel, "abc"));
    }

    @Test
    public void testDirectlyEmbeddedComponent() {
        final String name = "a";
        Component component = new JLabel("x");
        component.setName(name);
        panel.add(component);
        assertEquals(component, Util.getComponent(panel, name));
    }

    @Test
    public void testSubcomponent() {
        final String name = "a";
        Component component = new JLabel("x");
        component.setName(name);

        JPanel subpanel = new JPanel();
        subpanel.add(component);
        panel.add(subpanel);

        assertEquals(component, Util.getComponent(panel, name));
    }
}