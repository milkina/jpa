package data.questionEntry;

import data.category.CategoryHandler;
import model.AbstractQuestionEntry;
import model.Answer;
import model.Category;
import model.Question;
import model.QuestionEntry;
import model.QuestionType;
import model.Test;
import model.TestQuestionEntry;
import model.person.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static util.AllBeanNameConstants.QUESTION_ENTRY_BEAN_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 10:02:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestionEntryHandler {
    private QuestionEntryBeanI questionEntryBean;
    private Context ct;

    public QuestionEntryHandler() {
        try {
            ct = new InitialContext();
            questionEntryBean = (QuestionEntryBeanI) ct.lookup(QUESTION_ENTRY_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public QuestionEntryHandler(QuestionEntryBeanI questionEntryBean) {
        this.questionEntryBean = questionEntryBean;
    }

    public Map<Integer, AbstractQuestionEntry> getAllAbstractQuestionsMap(Category category) {
        List<AbstractQuestionEntry> list = questionEntryBean.getAllAbstractQuestions(category);
        Map<Integer, AbstractQuestionEntry> map = new TreeMap<>();
        for (AbstractQuestionEntry questionEntry : list) {
            map.put(questionEntry.getId(), questionEntry);
        }
        return map;
    }


    public List<AbstractQuestionEntry> getAllQuestions(Category category) {
        return questionEntryBean.getAllQuestions(category);
    }

    public List<AbstractQuestionEntry> getQuestionsForQuiz(String[] categoryPaths,
                                                   Person person,
                                                   String questionType,
                                                   int count) {
        CategoryHandler categoryHandler = new CategoryHandler();
        List<AbstractQuestionEntry> result = new ArrayList<>();
        for (String pathName : categoryPaths) {
            Category category = categoryHandler.getCategory(pathName);
            result.addAll(getQuestions(category, person, questionType));
        }
        Collections.shuffle(result);
        count = result.size() < count ? result.size() : count;
        return result.subList(0, count);
    }

    public List<AbstractQuestionEntry> getQuestions(Category category,
                                                    Person person,
                                                    String questionType) {
        if (person == null || questionType == null
                || QuestionType.ALL.toString().equals(questionType)) {
            return questionEntryBean.getAllQuestions(category);
        } else if (QuestionType.ANSWERED.toString().equals(questionType)) {
            return questionEntryBean.getAnsweredQuestions(category, person);
        } else {
            return questionEntryBean.getNotAnsweredQuestions(category, person);
        }
    }

    public List<AbstractQuestionEntry> getAllAbstractQuestions(Category category) {
        return questionEntryBean.getAllAbstractQuestions(category);
    }

    public List<AbstractQuestionEntry> getAllTestQuestions(Category category) {
        return questionEntryBean.getAllTestQuestions(category);
    }

    public List<AbstractQuestionEntry> getAnsweredQuestions(Category category,
                                                            Person person) {
        return questionEntryBean.getAnsweredQuestions(category, person);
    }

    public List<AbstractQuestionEntry> getNotAnsweredQuestions(Category category,
                                                               Person person) {
        return questionEntryBean.getNotAnsweredQuestions(category, person);
    }

    public AbstractQuestionEntry updateQuestionEntry(AbstractQuestionEntry questionEntry) {
        return questionEntryBean.updateQuestionEntry(questionEntry);
    }

    public void addQuestionEntries(AbstractQuestionEntry[] questionEntries) {
        for (AbstractQuestionEntry questionEntry : questionEntries) {
            addQuestionEntry(questionEntry);
        }
    }

    public AbstractQuestionEntry addQuestionEntry(AbstractQuestionEntry questionEntry) {
        questionEntry.setCreatedDate(new Date());
        return questionEntryBean.addQuestionEntry(questionEntry);
    }

    public void deleteQuestionEntry(AbstractQuestionEntry questionEntry) {
        questionEntryBean.deleteQuestionEntry(questionEntry);
    }

    public void deleteQuestionEntry(int id) {
        AbstractQuestionEntry questionEntry = questionEntryBean.getQuestionEntry(id);
        questionEntryBean.deleteQuestionEntry(questionEntry);
    }

    public AbstractQuestionEntry getQuestionEntry(int id) {
        return questionEntryBean.getQuestionEntry(id);
    }

    public void moveBatch(Category oldCategory, Category category,
                          Integer from, Integer to) {
        questionEntryBean.moveBatch(oldCategory, category, from, to);
    }


    public AbstractQuestionEntry getPreviousQuestionEntry(int orderColumn) {
        return questionEntryBean.getPreviousQuestionEntry(orderColumn);
    }

    public void moveQuestionEntryUp(int questionId) {
        AbstractQuestionEntry questionEntry = getQuestionEntry(questionId);
        AbstractQuestionEntry previousQuestionEntry =
                getPreviousQuestionEntry(questionEntry.getOrderColumn());
        if (previousQuestionEntry != null) {
            swapQuestionEntries(questionEntry, previousQuestionEntry);
        }
    }

    public void swapQuestionEntries(AbstractQuestionEntry q1, AbstractQuestionEntry q2) {
        int id1 = q1.getOrderColumn();
        int id2 = q2.getOrderColumn();
        q1.setOrderColumn(id2);
        q2.setOrderColumn(id1);
        updateQuestionEntry(q1);
        updateQuestionEntry(q2);
    }

    public List<Test> getQuestionEntryTests(int id) {
        return questionEntryBean.getQuestionEntryTests(id);
    }

    public Test getFirstQuestionEntryTest(int id) {
        return questionEntryBean.getFirstQuestionEntryTest(id);
    }

    public void removeAnswers(AbstractQuestionEntry questionEntry) {
        List<Answer> answers = questionEntry.getAnswers();
        questionEntry.setAnswers(null);
        questionEntryBean.updateQuestionEntry(questionEntry);
        for (Answer answer : answers) {
            removeAnswer(answer);
        }
    }

    public void removeAnswer(Answer answer) {
        questionEntryBean.removeAnswer(answer);
    }

    public Answer getAnswer(int id) {
        return questionEntryBean.getAnswer(id);
    }

    public Question getQuestion(int id) {
        return questionEntryBean.getQuestion(id);
    }

    public void changeQuestionToTestQuestion(int id) {
        questionEntryBean.changeQuestionType(id, QuestionType.TEST.toString());
    }

    public void changeTestQuestionToQuestion(int id) {
        questionEntryBean.changeQuestionType(id, QuestionType.QUESTION.toString());
    }


    public List<AbstractQuestionEntry> getQuestionsForExam(String[] categoryPaths, int count) {
        List<AbstractQuestionEntry> result = new ArrayList<>();
        for (String pathName : categoryPaths) {
            result.addAll(questionEntryBean.getQuestionsForExam(pathName));
        }
        Collections.shuffle(result);
        count = result.size() < count ? result.size() : count;
        return result.subList(0, count);
    }

    public List<TestQuestionEntry> getQuestionsForExam(Category category) {
        return questionEntryBean.getQuestionsForExam(category.getPathName());
    }

    public List<AbstractQuestionEntry> getNotApprovedQuestions() {
        return questionEntryBean.getNotApprovedQuestions();
    }

    public List<AbstractQuestionEntry> getPersonQuestions(int person) {
        return questionEntryBean.getPersonQuestions(person);
    }
}
