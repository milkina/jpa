package spring.services.course;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<Object[]> findAllCourses();

    Map<Integer, Test> getAllCourses();

    Map<String, Test> getAllCoursesWithPath();

    Test getCourse(int id);

    Map<String, Integer> getPathName();

    Test update(Test test);

    Test create(Test test);

    Test getCourse(Category category);

    void moveCourse(Test course, Test stopCourse);

    Test[] addCourses(Test... courses);

    void setUpdatedDate(Test course);

    void removeCategoryFromCourse(Test course, Category category);

    boolean deleteCourse(Test course);

    Test getCourseByQuestion(AbstractQuestionEntry questionEntry);

    List<Test> getAllTestsWithNotEmptyTests();

    List<Test> getAllCoursesWithNotEmptyQuestions();

    void swapCourses(Test course1, Test course2) ;
}
