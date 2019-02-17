package spring.services.course;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repositories.CategoryRepository;
import spring.repositories.CourseRepository;

import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Object[]> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Map<Integer, Test> getAllCourses() {
        Map<Integer, Test> map = new TreeMap<>();
        List<Object[]> list = courseRepository.findAllCourses();
        if (list != null) {
            for (Object[] object : list) {
                Test test = (Test) object[0];
                test.setQuestionsNumber((Long) object[1]);
                test.setTestsNumber((Long) object[2]);
                map.put(test.getId(), test);
            }
        }
        return map;
    }

    @Override
    public Map<String, Test> getAllCoursesWithPath() {
        Map<String, Test> map = new LinkedHashMap<>();
        List<Object[]> list = courseRepository.findAllCourses();
        if (list != null) {
            for (Object[] object : list) {
                Test test = (Test) object[0];
                test.setQuestionsNumber((Long) object[1]);
                test.setTestsNumber((Long) object[2]);
                map.put(test.getPathName(), test);
            }
        }
        return map;
    }

    @Override
    public Test getCourse(int id) {
        return courseRepository.findOne(id);
    }

    @Override
    public Map<String, Integer> getPathName() {
        Map<String, Integer> result = new HashMap<>();
        List<Object[]> list = courseRepository.getPathName();
        for (Object[] r : list) {
            result.put(r[1].toString(), (Integer) r[0]);
        }
        return result;
    }

    @Override
    @Transactional
    public Test create(Test test) {
        test = courseRepository.save(test);
        test.setOrderId(test.getId());
        return test;
    }

    @Override
    @Transactional
    public Test update(Test test) {
        return courseRepository.save(test);
    }

    @Override
    public void moveCourse(Test course, Test stopCourse) {
        if (course.getOrderId() > stopCourse.getOrderId()) {
            moveCourseUp(course.getPathName(), stopCourse.getPathName());
        } else {
            moveCourseDown(course.getPathName(), stopCourse.getPathName());
        }
    }

    @Override
    public Test[] addCourses(Test... courses) {
        for (int i = 0; i < courses.length; i++) {
            Test t = create(courses[i]);
            courses[i] = t;
        }
        return courses;
    }

    @Override
    @Transactional
    public void setUpdatedDate(Test course) {
        course.setUpdatedDate(new Date());
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public Test getCourse(Category category) {
        category = categoryRepository.save(category);
        return category.getTests().get(0);
    }

    @Override
    @Transactional
    public void removeCategoryFromCourse(Test course, Category category) {
        course = courseRepository.save(course);
        category = categoryRepository.save(category);
        course.removeCategory(category);
        category.removeTest(course);
    }

    @Override
    @Transactional
    public boolean deleteCourse(Test course) {
        course = courseRepository.save(course);
        if (course.getCategories() == null || course.getCategories().isEmpty()) {
            courseRepository.delete(course);
            return true;
        }
        return false;
    }

    @Override
    public Test getCourseByQuestion(AbstractQuestionEntry questionEntry) {
        List<Test> courses = courseRepository.getCourseByQuestion(questionEntry);
        return courses.get(0);
    }

    @Override
    public List<Test> getAllTestsWithNotEmptyTests() {
        return courseRepository.findAllWithNotEmptyTests();
    }

    @Override
    public List<Test> getAllCoursesWithNotEmptyQuestions() {
        return courseRepository.findAllWithNotEmptyQuestions();
    }

    @Transactional
    private void moveCourseUp(String coursePath, String stopCoursePath) {
        List<Test> courses = getPreviousCourses(coursePath);
        for (int i = courses.size() - 1; i > 0; i--) {
            if (stopCoursePath.equals(courses.get(i - 1).getPathName())) {
                break;
            }
            swapCourses(courses.get(i), courses.get(i - 1));

            Test tmp = courses.get(i);
            courses.set(i, courses.get(i - 1));
            courses.set(i - 1, tmp);
        }
    }

    @Transactional
    private void moveCourseDown(String coursePath, String stopCoursePath) {
        List<Test> courses = getNextCourses(coursePath);
        for (int i = 0; i < courses.size() - 1; i++) {
            swapCourses(courses.get(i), courses.get(i + 1));

            Test tmp = courses.get(i);
            courses.set(i, courses.get(i + 1));
            courses.set(i + 1, tmp);
            if (stopCoursePath.equals(courses.get(i).getPathName())) {
                break;
            }
        }
    }

    @Transactional
    @Override
    public void swapCourses(Test course1, Test course2) {
        int id1 = course1.getOrderId();
        int id2 = course2.getOrderId();
        course1.setOrderId(id2);
        course2.setOrderId(id1);
        courseRepository.save(course1);
        courseRepository.save(course2);
    }

    private List<Test> getPreviousCourses(String coursePath) {
        return courseRepository.getPreviousCourses(coursePath);
    }

    private List<Test> getNextCourses(String coursePath) {
        return courseRepository.getNextCourses(coursePath);
    }
}
