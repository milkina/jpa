package main.java.data.questionEntry;

import main.java.model.Category;
import main.java.model.QuestionEntry;
import main.java.model.QuestionType;
import main.java.model.Test;
import main.java.model.person.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static main.java.util.AllBeanNameConstants.QUESTION_ENTRY_BEAN_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 10:02:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestionEntryHandler {

    QuestionEntryBeanI questionEntryBean;
    Context ct;

    public QuestionEntryHandler() {
        try {
            ct = new InitialContext();
            questionEntryBean = (QuestionEntryBeanI) ct.lookup(QUESTION_ENTRY_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public QuestionEntryHandler(QuestionEntryBeanI questionEntryBean) {
        this.questionEntryBean = questionEntryBean;
    }

    public Map<Integer, QuestionEntry> getAllQuestions(Category category) {
        List<QuestionEntry> list = questionEntryBean.getAllQuestions(category);
        Map<Integer, QuestionEntry> map = new TreeMap<Integer, QuestionEntry>();
        for (QuestionEntry questionEntry : list) {
            map.put(questionEntry.getId(), questionEntry);
        }
        return map;
    }


    public List<QuestionEntry> getAllQuestionsList(Category category) {
        return questionEntryBean.getAllQuestions(category);
    }

    public List<QuestionEntry> getQuestions(Category category, Person person, String questionType) {
        if (person == null || questionType == null || QuestionType.ALL.toString().equals(questionType)) {
            return questionEntryBean.getAllQuestions(category);
        } else if (QuestionType.ANSWERED.toString().equals(questionType)) {
            return questionEntryBean.getAnsweredQuestions(category, person);
        } else {
            return questionEntryBean.getNotAnsweredQuestions(category, person);
        }
    }

    public List<QuestionEntry> getAnsweredQuestions(Category category, Person person) {
        return questionEntryBean.getAnsweredQuestions(category, person);
    }

    public List<QuestionEntry> getNotAnsweredQuestions(Category category, Person person) {
        return questionEntryBean.getNotAnsweredQuestions(category, person);
    }

    public QuestionEntry updateQuestionEntry(QuestionEntry questionEntry) {
        return questionEntryBean.updateQuestionEntry(questionEntry);
    }

    public void addQuestionEntries(QuestionEntry[] questionEntries) {
        for (QuestionEntry questionEntry : questionEntries) {
            addQuestionEntry(questionEntry);
        }
    }

    public QuestionEntry addQuestionEntry(QuestionEntry questionEntry) {
        questionEntry.setCreatedDate(new Date());
        return questionEntryBean.addQuestionEntry(questionEntry);
    }

    public void deleteQuestionEntry(QuestionEntry questionEntry) {
        questionEntryBean.deleteQuestionEntry(questionEntry);
    }

    public void deleteQuestionEntry(int id) {
        QuestionEntry questionEntry = questionEntryBean.getQuestionEntry(id);
        questionEntryBean.deleteQuestionEntry(questionEntry);
    }

    public QuestionEntry getQuestionEntry(int id) {
        return questionEntryBean.getQuestionEntry(id);
    }

    public void moveBatch(Category oldCategory, Category category, Integer from, Integer to) {
        questionEntryBean.moveBatch(oldCategory, category, from, to);
    }


    public QuestionEntry getPreviousQuestionEntry(int orderColumn) {
        return questionEntryBean.getPreviousQuestionEntry(orderColumn);
    }

    public void moveQuestionEntryUp(int questionId) {
        QuestionEntry questionEntry = getQuestionEntry(questionId);
        QuestionEntry previousQuestionEntry = getPreviousQuestionEntry(questionEntry.getOrderColumn());
        if (previousQuestionEntry != null) {
            swapQuestionEntries(questionEntry, previousQuestionEntry);
        }
    }

    public void swapQuestionEntries(QuestionEntry q1, QuestionEntry q2) {
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
}
