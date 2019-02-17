package spring;

import model.*;
import model.article.Article;
import model.person.Person;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;
import utils.TestValues;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.03.2013
 * Time: 22:57:45
 * To change this template use File | Settings | File Templates.
 */
@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class QuestionServiceIT extends BaseIT {

    @Test
    public void testAddQuestionEntry() {
        Category category = createCategoryWithTest(7, 7);
        Person person = TestUtils.createPerson(5);
        person = personService.addPerson(person);
        QuestionEntry questionEntry = TestUtils.createQuestionEntry(3, category, person);

        QuestionEntry receivedQuestionEntry = (QuestionEntry) questionService.addQuestionEntry(questionEntry);
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
    public void testGetAllAbstractQuestionsMap() {
        Map<Integer, AbstractQuestionEntry> receivedMap = questionService.getAllAbstractQuestionsMap(categories[1]);
        Assert.assertNotNull(receivedMap);
        Assert.assertSame(receivedMap.size(), 4);
        Set<Integer> keys = receivedMap.keySet();
        for (Integer key : keys) {
            AbstractQuestionEntry questionEntry = receivedMap.get(key);
            Assert.assertNotNull(questionEntry);
        }
    }

    @Test
    public void testGetAllQuestions() {
        List<AbstractQuestionEntry> result = questionService.getAllQuestions(categories[1]);
        Assert.assertNotNull(result);
        Assert.assertSame(result.size(), 2);
        for (AbstractQuestionEntry questionEntry : result) {
            Assert.assertNotNull(questionEntry);
        }
    }

    @Test
    public void testGetAllTestQuestions() {
        List<AbstractQuestionEntry> result = questionService.getAllTestQuestions(categories[1]);
        Assert.assertNotNull(result);
        Assert.assertSame(result.size(), 2);
        for (AbstractQuestionEntry questionEntry : result) {
            Assert.assertNotNull(questionEntry);
        }
    }

    @Test
    public void testUpdateQuestionEntry() {
        Category category = createCategoryWithTest(8, 8);

        Person person = TestUtils.createPerson(6);
        person = personService.addPerson(person);
        QuestionEntry questionEntry = TestUtils.createQuestionEntry(4, category, person);
        questionEntry = (QuestionEntry) questionService.addQuestionEntry(questionEntry);

        model.Test newTest = TestUtils.createTest(9);
        newTest = courseService.create(newTest);
        Article article = TestUtils.createArticle(10, person);
        articleService.addArticle(article);
        Category newCategory = createCategory(9, newTest, article);

        questionEntry.getAnswer().setText(TestValues.ANSWERS[5]);
        questionEntry.getQuestion().setText(TestValues.QUESTION_TEXTS[5]);
        questionEntry.setCategory(newCategory);
        AbstractQuestionEntry receivedQuestionEntry = questionService.updateQuestionEntry(questionEntry);

        Assert.assertNotNull(receivedQuestionEntry);
        Assert.assertEquals(receivedQuestionEntry.getCategory(), newCategory);
        //TODO
        //Assert.assertEquals(receivedQuestionEntry.getAnswer().getText(), TestValues.ANSWERS[5]);
        Assert.assertEquals(receivedQuestionEntry.getQuestion().getText(), TestValues.QUESTION_TEXTS[5]);
    }

    @Test
    public void testDeleteQuestionEntry() {
        Category category = createCategoryWithTest(10, 10);

        Person person = TestUtils.createPerson(7);
        person = personService.addPerson(person);
        QuestionEntry questionEntry = TestUtils.createQuestionEntry(6, category, person);
        questionEntry = (QuestionEntry) questionService.addQuestionEntry(questionEntry);

        Map<Integer, AbstractQuestionEntry> receivedMap = questionService.getAllAbstractQuestionsMap(category);
        Assert.assertNotNull(receivedMap);
        Assert.assertEquals(receivedMap.size(), 1);
        Optional<AbstractQuestionEntry> optional = receivedMap.values().stream().findFirst();
        List<Answer> answers = optional.get().getAnswers();
        Question question = optional.get().getQuestion();

        verifyAnswerAndQuestionExists(answers, question);

        questionService.deleteQuestionEntry(questionEntry.getId());
        receivedMap = questionService.getAllAbstractQuestionsMap(category);
        Assert.assertNotNull(receivedMap);
        Assert.assertEquals(receivedMap.size(), 0);

        verifyAnswerAndQuestionNotExists(answers, question);
    }

    private void verifyAnswerAndQuestionExists(List<Answer> answers, Question question) {
        for (Answer answer : answers) {
            Answer resultAnswer = answerService.getAnswer(answer.getId());
            Assert.assertNotNull(resultAnswer);

            Question questionResult = questionService.getQuestion(question.getId());
            Assert.assertNotNull(questionResult);
        }
    }

    private void verifyAnswerAndQuestionNotExists(List<Answer> answers, Question question) {
        for (Answer answer : answers) {
            Answer resultAnswer = answerService.getAnswer(answer.getId());
            Assert.assertNull(resultAnswer);
        }
        Question questionResult = questionService.getQuestion(question.getId());
        Assert.assertNull(questionResult);
    }

    @Test
    public void testMoveQuestions() {
        Category category = createCategoryWithTest(11, 11);
        Category newCategory = createCategoryWithTest(12, 12);
        Person person = TestUtils.createPerson(8);
        person = personService.addPerson(person);

        QuestionEntry[] questionEntries = new QuestionEntry[2];
        questionEntries[0] = TestUtils.createQuestionEntry(7, category, person);
        questionEntries[1] = TestUtils.createQuestionEntry(8, category, person);
        questionService.addQuestionEntries(questionEntries);
        questionService.moveBatch(category, newCategory, 1, 2);

        Map<Integer, AbstractQuestionEntry> questionsMap1 = questionService.getAllAbstractQuestionsMap(category);
        Assert.assertNotNull(questionsMap1);
        Assert.assertEquals(questionsMap1.size(), 0);
        Map<Integer, AbstractQuestionEntry> questionsMap2 = questionService.getAllAbstractQuestionsMap(newCategory);
        Assert.assertNotNull(questionsMap2);
        Assert.assertEquals(questionsMap2.size(), 2);
    }

    @Test
    public void testGetQuestionEntry() {
        AbstractQuestionEntry receivedQuestionEntry = questionService.getQuestionEntry(questionEntries[0].getId());
        Assert.assertNotNull(receivedQuestionEntry);
        Assert.assertEquals(receivedQuestionEntry, questionEntries[0]);
    }

    @Test
    public void testGetAnsweredQuestions() {
        List<AbstractQuestionEntry> questionEntryList = questionService.getAnsweredQuestions(categories[1], persons[0]);
        Assert.assertNotNull(questionEntryList);
        Assert.assertEquals(questionEntryList.size(), 1);
        Assert.assertEquals(questionEntryList.get(0), questionEntries[1]);
    }

    @Test
    public void testGetNotAnsweredQuestions() {
        List<AbstractQuestionEntry> questionEntryList = questionService.getNotAnsweredQuestions(categories[1], persons[0]);
        Assert.assertNotNull(questionEntryList);
        Assert.assertEquals(questionEntryList.size(), 1);
        Assert.assertEquals(questionEntryList.get(0), questionEntries[2]);
    }

    @Test
    public void testGetPreviousQuestionEntry() {
        model.Test test = TestUtils.createTest(14);
        test = courseService.create(test);
        Category category1 = createCategoryWithArticle(test, 14);
        Category category2 = createCategoryWithArticle(test, 15);

        QuestionEntry questionEntry1 = TestUtils.createQuestionEntry(9, category1, persons[0]);
        QuestionEntry questionEntry2 = TestUtils.createQuestionEntry(10, category2, persons[0]);
        QuestionEntry questionEntry3 = TestUtils.createQuestionEntry(11, category1, persons[0]);
        questionEntry1 = (QuestionEntry) questionService.addQuestionEntry(questionEntry1);
        questionEntry2 = (QuestionEntry) questionService.addQuestionEntry(questionEntry2);
        questionEntry3 = (QuestionEntry) questionService.addQuestionEntry(questionEntry3);

        AbstractQuestionEntry result = questionService.getPreviousQuestionEntry(questionEntry3.getOrderColumn());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), questionEntry1.getId());
    }

    @Test
    public void testSwapQuestionEntries() {
        Category category = createCategoryWithTest(15, 16);
        QuestionEntry questionEntry1 = TestUtils.createQuestionEntry(12, category, persons[0]);
        QuestionEntry questionEntry2 = TestUtils.createQuestionEntry(13, category, persons[0]);
        questionEntry1 = (QuestionEntry) questionService.addQuestionEntry(questionEntry1);
        questionEntry2 = (QuestionEntry) questionService.addQuestionEntry(questionEntry2);
        int orderColumn1 = questionEntry1.getOrderColumn();
        int orderColumn2 = questionEntry2.getOrderColumn();
        questionService.swapQuestionEntries(questionEntry1, questionEntry2);

        AbstractQuestionEntry updatedQuestionEntry1 = questionService.getQuestionEntry(questionEntry1.getId());
        AbstractQuestionEntry updatedQuestionEntry2 = questionService.getQuestionEntry(questionEntry2.getId());
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
        questionEntry1 = (QuestionEntry) questionService.addQuestionEntry(questionEntry1);
        questionEntry2 = (QuestionEntry) questionService.addQuestionEntry(questionEntry2);
        int orderColumn1 = questionEntry1.getOrderColumn();
        int orderColumn2 = questionEntry2.getOrderColumn();

        questionService.moveQuestionEntryUp(questionEntry2.getId());

        AbstractQuestionEntry updatedQuestionEntry1 = questionService.getQuestionEntry(questionEntry1.getId());
        AbstractQuestionEntry updatedQuestionEntry2 = questionService.getQuestionEntry(questionEntry2.getId());

        Assert.assertNotNull(updatedQuestionEntry1);
        Assert.assertNotNull(updatedQuestionEntry2);
        Assert.assertEquals(updatedQuestionEntry1.getOrderColumn(), orderColumn2);
        Assert.assertEquals(updatedQuestionEntry2.getOrderColumn(), orderColumn1);
    }

    @Test
    public void testMoveQuestionEntryUpForFirstEntry() {
        questionService.moveQuestionEntryUp(questionEntries[0].getId());
        int orderColumn = questionEntries[0].getOrderColumn();
        AbstractQuestionEntry result = questionService.getQuestionEntry(questionEntries[0].getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getOrderColumn(), orderColumn);
    }

    @Test
    public void testGetNotApprovedQuestions() {
        testQuestionEntries[0].setApproved(false);
        questionEntries[0].setApproved(false);
        questionService.updateQuestionEntry(testQuestionEntries[0]);
        questionService.updateQuestionEntry(questionEntries[0]);
        List<AbstractQuestionEntry> result = questionService.getNotApprovedQuestions();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);

        testQuestionEntries[0].setApproved(true);
        questionEntries[0].setApproved(true);
        questionService.updateQuestionEntry(testQuestionEntries[0]);
        questionService.updateQuestionEntry(questionEntries[0]);
        result = questionService.getNotApprovedQuestions();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void testGetPersonQuestions() {
        List<AbstractQuestionEntry> result = questionService.getPersonQuestions(persons[2].getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 5);
    }
}

