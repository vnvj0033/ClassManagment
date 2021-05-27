package ui;

import studentInfo.Course;

public class CourseDisplayAdapter extends Course {
    CourseDisplayAdapter(Course course) {
        super(course.getDepartment(), course.getNumber());
    }

    @Override
    public String toString() {
        return String.format("%s-%s", getDepartment(), getNumber());
    }
}
