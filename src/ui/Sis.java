package ui;

import studentInfo.Course;
import util.DateUtil;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        createInputFilters();

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
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try{
            Course course = new Course(panel.getText(CoursesPanel.DEPARTMENT_FIELD_NAME), panel.getText(CoursesPanel.NUMBER_FIELD_NAME));
            try { Thread.sleep(300); }catch (InterruptedException e){ }

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");

            try {
                Date date = format.parse(panel.getText(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME));
                course.setEffectiveDate(date);
                panel.addCourse(course);
            }catch (Exception e){ }
        }finally {
            frame.setCursor(Cursor.getDefaultCursor());
        }
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

    private void createInputFilters() {
        JTextField field = panel.getField(CoursesPanel.DEPARTMENT_FIELD_NAME);
        AbstractDocument document = (AbstractDocument) field.getDocument();
        document.setDocumentFilter(new UpcaseFilter());
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, !isEmpty(CoursesPanel.DEPARTMENT_FIELD_NAME) && !isEmpty(CoursesPanel.NUMBER_FIELD_NAME));
    }

    private boolean isEmpty(String field) {
        String value = panel.getText(field);
        return value.trim().equals("");
    }
}
