package ui;

import studentInfo.Course;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sis {
    static final int WIDTH = 300;
    static final int HEIGHT = 200;
    static final String COURSES_TITLE = "Course Listing";

    private JFrame frame = new JFrame(COURSES_TITLE);
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

        Image image = ImageUtil.create("/image/courses.gif");
        frame.setIconImage(image);

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
