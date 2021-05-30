package ui;

import studentInfo.Course;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        createKeyListeners();

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
                System.out.println("onClick");
                addCourse();
            }
        });
    }

    private void addCourse() {
        Course course = new Course(panel.getText(CoursesPanel.DEPARTMENT_FIELD_NAME), panel.getText(CoursesPanel.NUMBER_FIELD_NAME));
        panel.addCourse(course);
    }

    void createKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setAddButtonState();
            }
        };
        panel.addFieldListener(CoursesPanel.DEPARTMENT_FIELD_NAME, listener);
        panel.addFieldListener(CoursesPanel.NUMBER_FIELD_NAME, listener);
        setAddButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, !isEmpty(CoursesPanel.DEPARTMENT_FIELD_NAME) && !isEmpty(CoursesPanel.NUMBER_FIELD_NAME));
    }

    private boolean isEmpty(String field) {
        String value = panel.getText(field);
        return value.trim().equals("");
    }
}
