package ui;

import studentInfo.Course;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sis {
    static final int WIDTH = 300;
    static final int HEIGHT = 200;

    private JFrame frame = new JFrame();
    private CoursesPanel panel;

    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        initialize();
    }

    public void show() {
        frame.setVisible(true);
    }

    JFrame getFrame() {
        return frame;
    }

    void close() {
        frame.dispose();
    }

    private void initialize() {
        createCoursesPanel();

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
    }

    void createCoursesPanel() {
        panel = new CoursesPanel();
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
    }

    private void addCourse() {
        Course course = new Course(panel.getText(CoursesPanel.DEPARTMENT_FIELD_NAME), panel.getText(CoursesPanel.NUMBER_FIELD_NAME));
        panel.addCourse(course);
    }
}
