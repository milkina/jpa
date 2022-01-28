package spring.repositories;

import model.AbstractQuestionEntry;
import model.Answer;
import model.Category;
import model.Question;
import model.QuestionEntry;
import model.TestQuestionEntry;
import model.person.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<AbstractQuestionEntry, Integer> {

    @Query("SELECT DISTINCT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category=:param AND qe.approved=true ORDER BY qe.orderColumn, qe.id")
    List<AbstractQuestionEntry> getAllQuestions(@Param("param") Category category);

    @Query("SELECT DISTINCT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category IN (:param) AND qe.approved=true ORDER BY qe.orderColumn, qe.id")
    List<AbstractQuestionEntry> getAllQuestionsForCategories(@Param("param") List<Category> categories);

    @Query("SELECT DISTINCT qe from TestQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category=:param AND qe.approved=true ORDER BY qe.orderColumn, qe.id")
    List<AbstractQuestionEntry> getAllTestQuestions(@Param("param") Category category);

    @Query("SELECT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category=:category AND qe.id IN "
            + "(select aq.id FROM Person p JOIN p.answeredQuestions aq where p=:person) ORDER BY qe.orderColumn,qe.id")
    List<AbstractQuestionEntry> getAnsweredQuestions(@Param("category") Category category, @Param("person") Person person);

    @Query("SELECT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category IN (:category) AND qe.id IN "
            + "(select aq.id FROM Person p JOIN p.answeredQuestions aq where p=:person) ORDER BY qe.orderColumn,qe.id")
    List<AbstractQuestionEntry> getAnsweredQuestionsForCategories(@Param("category") List<Category> categories, @Param("person") Person person);

    @Query("SELECT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category=:category AND qe.approved=true AND qe.id NOT IN "
            + "(SELECT aq.id FROM Person p JOIN p.answeredQuestions aq WHERE p=:person) ORDER BY qe.orderColumn,qe.id")
    List<AbstractQuestionEntry> getNotAnsweredQuestions(@Param("category") Category category, @Param("person") Person person);

    @Query("SELECT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category=:category AND qe.approved=true AND qe.id NOT IN "
            + "(SELECT aq.id FROM Person p JOIN p.answeredQuestions aq WHERE p=:person) ORDER BY qe.orderColumn,qe.id")
    List<AbstractQuestionEntry> getNotAnsweredQuestionsForCategories(@Param("category") List<Category> category, @Param("person") Person person);

    @Query("SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.category=:param AND qe.approved=true ORDER BY qe.orderColumn, qe.id")
    List<AbstractQuestionEntry> getAllAbstractQuestions(@Param("param") Category category);

    @Query("SELECT qe1 FROM AbstractQuestionEntry qe1 "
            + "WHERE qe1.orderColumn = (SELECT max(qe2.orderColumn) "
            + "FROM AbstractQuestionEntry qe2 WHERE qe2.approved=true AND qe2.orderColumn<:param AND qe2.category.id="
            + "(SELECT qe3.category.id FROM AbstractQuestionEntry qe3 WHERE qe3.orderColumn=:param)"
            + " AND qe2.type=(SELECT qe4.type FROM AbstractQuestionEntry qe4 WHERE qe4.orderColumn=:param))")
    List<QuestionEntry> getPreviousQuestionEntry(@Param("param") int orderColumn);

    @Modifying
    @Transactional
    @Query(value = "UPDATE QUESTIONS SET QTYPE =:type WHERE entry_id =:id", nativeQuery = true)
    void changeQuestionType(@Param("type") String type, @Param("id") int id);

    @Query("SELECT DISTINCT qe from TestQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.approved=true AND (qe.category.pathName=:param OR qe.category IN "
            + "(SELECT c FROM Category c WHERE c.parentCategory.pathName=:param))")
    List<TestQuestionEntry> getQuestionsForExam(@Param("param") String categoryPath);

    @Query("SELECT DISTINCT qe from TestQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.approved=true AND (qe.category.pathName IN (:param) OR qe.category IN "
            + "(SELECT c FROM Category c WHERE c.parentCategory.pathName IN (:param)))")
    List<TestQuestionEntry> getQuestionsForExamForBatch(@Param("param") List<String> categoryPath);

    @Query("SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.approved=false ORDER BY qe.orderColumn, qe.id")
    List<AbstractQuestionEntry> getNotApprovedQuestions();

    @Query("SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
            + "JOIN FETCH qe.category WHERE qe.person.id=:param ORDER BY qe.orderColumn, qe.id")
    List<AbstractQuestionEntry> getPersonQuestions(@Param("param") int person);

    @Query("SELECT a FROM Answer a WHERE a.id=:param")
    Answer getAnswer(@Param("param") int id);

    @Query("SELECT q FROM Question q WHERE q.id=:param")
    Question getQuestion(@Param("param") int id);
}
