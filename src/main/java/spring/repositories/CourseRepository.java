package spring.repositories;

import model.AbstractQuestionEntry;
import model.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Test, Integer> {
    @Query("SELECT t,sum(c.questionsCount),sum(c.testsCount) FROM Test t "
            + "left join t.categories c "
            + "GROUP BY t ORDER BY t.orderId ")
    List<Object[]> findAllCourses();

    @Query("select t.id,t.pathName from Test t")
    List<Object[]> getPathName();

    @Query("SELECT t FROM Test t WHERE t.orderId<= "
            + " (SELECT tt.orderId FROM Test tt WHERE tt.pathName=:param) ORDER BY t.orderId")
    List<Test> getPreviousCourses(@Param("param") String coursePath);

    @Query("SELECT t FROM Test t WHERE t.orderId>= "
            + " (SELECT tt.orderId FROM Test tt WHERE tt.pathName=:param) ORDER BY t.orderId")
    List<Test> getNextCourses(@Param("param") String coursePath);


    @Query("SELECT t FROM Test t INNER JOIN t.categories c INNER JOIN c.questionEntries q" +
            " WHERE q=:param")
    List<Test> getCourseByQuestion(@Param("param") AbstractQuestionEntry questionEntry);

    @Query("SELECT distinct t FROM Test t "
            + "left join t.categories c WHERE "
            + "c.testsCount>0 "
            + "ORDER BY t.orderId")
    List<Test> findAllWithNotEmptyTests();

    @Query("SELECT distinct t FROM Test t "
            + "left join t.categories c WHERE "
            + "c.questionsCount>0 "
            + "ORDER BY t.orderId")
    List<Test> findAllWithNotEmptyQuestions();
}
