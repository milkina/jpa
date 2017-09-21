package ejb.question;

import main.java.model.Category;
import main.java.model.QuestionEntry;
import main.java.model.article.Article;
import main.java.model.person.Person;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;
import utils.TestValues;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.03.2013
 * Time: 22:57:45
 * To change this template use File | Settings | File Templates.
 */
public class QuestionEntryHandlerIT extends BaseIT {

    @Test
    public void testAddQuestionEntry() {
        Category category = createCategoryWithTest(7, 7);
        Person person = TestUtils.createPerson(5);
        person = personHandler.addPerson(person);
        QuestionEntry questionEntry = TestUtils.createQuestionEntry(3, category, person);

        QuestionEntry receivedQuestionEntry = questionEntryHandler.addQuestionEntry(questionEntry);
        Assert.assertNotNull(receivedQuestionEntry);
        Assert.assertNotNull(receivedQuestionEntry.getAnswer());
        Assert.assertEquals(receivedQuestionEntry.getAnswer().getText(), TestValues.ANSWERS[3]);
        Assert.assertNotNull(receivedQuestionEntry.getQuestion());
        Assert.assertEquals(receivedQuestionEntry.getQuestion().getText(), TestValues.QUESTION_TEXTS[3]);

        Assert.assertNotNull(receivedQuestionEntry.getCreatedDate());
        Assert.assertNotNull(receivedQuestionEntry.getCategory());
        Assert.assertEquals(receivedQuestionEntry.getCategory(), category);
        Assert.assertNotNull(receivedQuestionEntry.getPerson());
        Assert.assertEquals(receivedQuestionEntry.getPerson(), person);
    }

    @Test
    public void testGetAllQuestions() {
        Map<Integer, QuestionEntry> receivedMap = questionEntryHandler.getAllQuestions(categories[1]);
        Assert.assertNotNull(receivedMap);
        Assert.assertSame(receivedMap.size(), 2);
        Set<Integer> keys = receivedMap.keySet();
        for (Integer key : keys) {
            QuestionEntry questionEntry = receivedMap.get(key);
            Assert.assertNotNull(questionEntry);
        }
    }

    @Test
    public void testUpdateQuestionEntry() {
        Category category = createCategoryWithTest(8, 8);

        Person person = TestUtils.createPerson(6);
        person = personHandler.addPerson(person);
        QuestionEntry questionEntry = TestUtils.createQuestionEntry(4, category, person);
        questionEntry = questionEntryHandler.addQuestionEntry(questionEntry);

        main.java.model.Test newTest = TestUtils.createTest(9);
        newTest = testHandler.addTest(newTest);
        Article article = TestUtils.createArticle(10, person);
        articleHandler.addArticle(article);
        Category newCategory = createCategory(9, newTest, article);

        questionEntry.getAnswer().setText(TestValues.ANSWERS[5]);
        questionEntry.getQuestion().setText(TestValues.QUESTION_TEXTS[5]);
        questionEntry.setCategory(newCategory);
        QuestionEntry receivedQuestionEntry = questionEntryHandler.updateQuestionEntry(questionEntry);

        Assert.assertNotNull(receivedQuestionEntry);
        Assert.assertEquals(receivedQuestionEntry.getCategory(), newCategory);
        Assert.assertEquals(receivedQuestionEntry.getAnswer().getText(), TestValues.ANSWERS[5]);
        Assert.assertEquals(receivedQuestionEntry.getQuestion().getText(), TestValues.QUESTION_TEXTS[5]);
    }

    @Test
    public void testDeleteQuestionEntry() {
        Category category = createCategoryWithTest(10, 10);

        Person person = TestUtils.createPerson(7);
        person = personHandler.addPerson(person);
        QuestionEntry questionEntry = TestUtils.createQuestionEntry(6, category, person);
        questionEntry = questionEntryHandler.addQuestionEntry(questionEntry);

        Map<Integer, QuestionEntry> receivedMap = questionEntryHandler.getAllQuestions(category);
        Assert.assertNotNull(receivedMap);
        Assert.assertEquals(receivedMap.size(), 1);

        questionEntryHandler.deleteQuestionEntry(questionEntry.getId());
        receivedMap = questionEntryHandler.getAllQuestions(category);
        Assert.assertNotNull(receivedMap);
        Assert.assertEquals(receivedMap.size(), 0);
    }

    @Test
    public void testMoveQuestions() {
        Category category = createCategoryWithTest(11, 11);
        Category newCategory = createCategoryWithTest(12, 12);
        Person person = TestUtils.createPerson(8);
        person = personHandler.addPerson(person);

        QuestionEntry[] questionEntries = new QuestionEntry[2];
        questionEntries[0] = TestUtils.createQuestionEntry(7, category, person);
        questionEntries[1] = TestUtils.createQuestionEntry(8, category, person);
        questionEntryHandler.addQuestionEntries(questionEntries);
        questionEntryHandler.moveBatch(category, newCategory,1,2);

        Map<Integer, QuestionEntry> questionsMap1 = questionEntryHandler.getAllQuestions(category);
        Assert.assertNotNull(questionsMap1);
        Assert.assertEquals(questionsMap1.size(), 0);
        Map<Integer, QuestionEntry> questionsMap2 = questionEntryHandler.getAllQuestions(newCategory);
        Assert.assertNotNull(questionsMap2);
        Assert.assertEquals(questionsMap2.size(), 2);
    }

    @Test
    public void testGetQuestionEntry() {
        QuestionEntry receivedQuestionEntry = questionEntryHandler.getQuestionEntry(questionEntries[0].getId());
        Assert.assertNotNull(receivedQuestionEntry);
        Assert.assertEquals(receivedQuestionEntry, questionEntries[0]);
    }

    @Test
    public void testGetAnsweredQuestions() {
        List<QuestionEntry> questionEntryList = questionEntryHandler.getAnsweredQuestions(categories[1], persons[0]);
        Assert.assertNotNull(questionEntryList);
        Assert.assertEquals(questionEntryList.size(), 1);
        Assert.assertEquals(questionEntryList.get(0), questionEntries[1]);
    }

    @Test
    public void testGetNotAnsweredQuestions() {
        List<QuestionEntry> questionEntryList = questionEntryHandler.getNotAnsweredQuestions(categories[1], persons[0]);
        Assert.assertNotNull(questionEntryList);
        Assert.assertEquals(questionEntryList.size(), 1);
        Assert.assertEquals(questionEntryList.get(0), questionEntries[2]);
    }

    @Test
    public void testGetPreviousQuestionEntry() {
        main.java.model.Test test = TestUtils.createTest(14);
        test = testHandler.addTest(test);
        Category category1 = createCategoryWithArticle(test, 14);
        Category category2 = createCategoryWithArticle(test, 15);

        QuestionEntry questionEntry1 = TestUtils.createQuestionEntry(9, category1, persons[0]);
        QuestionEntry questionEntry2 = TestUtils.createQuestionEntry(10, category2, persons[0]);
        QuestionEntry questionEntry3 = TestUtils.createQuestionEntry(11, category1, persons[0]);
        questionEntry1 = questionEntryHandler.addQuestionEntry(questionEntry1);
        questionEntry2 = questionEntryHandler.addQuestionEntry(questionEntry2);
        questionEntry3 = questionEntryHandler.addQuestionEntry(questionEntry3);

        QuestionEntry result = questionEntryHandler.getPreviousQuestionEntry(questionEntry3.getOrderColumn());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), questionEntry1.getId());
    }

    @Test
    public void testSwapQuestionEntries() {
        Category category = createCategoryWithTest(15, 16);
        QuestionEntry questionEntry1 = TestUtils.createQuestionEntry(12, category, persons[0]);
        QuestionEntry questionEntry2 = TestUtils.createQuestionEntry(13, category, persons[0]);
        questionEntry1 = questionEntryHandler.addQuestionEntry(questionEntry1);
        questionEntry2 = questionEntryHandler.addQuestionEntry(questionEntry2);
        int orderColumn1 = questionEntry1.getOrderColumn();
        int orderColumn2 = questionEntry2.getOrderColumn();
        questionEntryHandler.swapQuestionEntries(questionEntry1, questionEntry2);

        QuestionEntry updatedQuestionEntry1 = questionEntryHandler.getQuestionEntry(questionEntry1.getId());
        QuestionEntry updatedQuestionEntry2 = questionEntryHandler.getQuestionEntry(questionEntry2.getId());
        Assert.assertNotNull(updatedQuestionEntry1);
        Assert.assertNotNull(updatedQuestionEntry2);
        Assert.assertEquals(updatedQuestionEntry1.getOrderColumn(), orderColumn2);
        Assert.assertEquals(updatedQuestionEntry2.getOrderColumn(), orderColumn1);
    }

    @Test
    public void testMoveQuestionEntryUp() {
        Category category = createCategoryWithTest(16, 17);
        QuestionEntry questionEntry1 = TestUtils.createQuestionEntry(14, category, persons[0]);
        QuestionEntry questionEntry2 = TestUtils.createQuestionEntry(15, category, persons[0]);
        questionEntry1 = questionEntryHandler.addQuestionEntry(questionEntry1);
        questionEntry2 = questionEntryHandler.addQuestionEntry(questionEntry2);
        int orderColumn1 = questionEntry1.getOrderColumn();
        int orderColumn2 = questionEntry2.getOrderColumn();

        questionEntryHandler.moveQuestionEntryUp(questionEntry2.getId());

        QuestionEntry updatedQuestionEntry1 = questionEntryHandler.getQuestionEntry(questionEntry1.getId());
        QuestionEntry updatedQuestionEntry2 = questionEntryHandler.getQuestionEntry(questionEntry2.getId());

        Assert.assertNotNull(updatedQuestionEntry1);
        Assert.assertNotNull(updatedQuestionEntry2);
        Assert.assertEquals(updatedQuestionEntry1.getOrderColumn(), orderColumn2);
        Assert.assertEquals(updatedQuestionEntry2.getOrderColumn(), orderColumn1);
    }

    @Test
    public void testMoveQuestionEntryUpForFirstEntry() {
        questionEntryHandler.moveQuestionEntryUp(questionEntries[0].getId());
        int orderColumn = questionEntries[0].getOrderColumn();
        QuestionEntry result = questionEntryHandler.getQuestionEntry(questionEntries[0].getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getOrderColumn(), orderColumn);
    }

}

