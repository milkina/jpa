package utils;

import model.Language;
import model.*;
import model.article.Article;
import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;

import java.util.*;

import static utils.TestValues.*;

/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: Jul 14, 2011
 * Time: 9:50:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestUtils {
    public static Person createPerson(int i) {
        return createPerson(TestValues.LOGINS[i], TestValues.PASSWORDS[i], true, EMAILS[i], null);
    }

    public static Person createPerson(String login, String password, Boolean sysadmin,
                                      String email, List<AbstractQuestionEntry> answeredQuestions) {
        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        person.setSysadmin(sysadmin);
        person.setEmail(email);
        person.setAnsweredQuestions(answeredQuestions);
        return person;
    }

    public static QuestionEntry createQuestionEntry(int i, Category category, Person person) {
        return createQuestionEntry(category, ANSWERS[i], person, QUESTION_TEXTS[i]);
    }

    public static TestQuestionEntry createTestQuestionEntry(int i, Category category, Person person) {
        return createTestQuestionEntry(category, ANSWERS[i], person, QUESTION_TEXTS[i]);
    }

    public static QuestionEntry createQuestionEntry(Category category, String answerText, Person person, String questionText) {
        Question question = TestUtils.createQuestion(questionText);
        Answer answer = TestUtils.createAnswer(answerText, false);
        return createQuestionEntry(category, answer, person, question);
    }

    public static TestQuestionEntry createTestQuestionEntry(Category category, String answerText, Person person, String questionText) {
        Question question = TestUtils.createQuestion(questionText);
        Answer answer1 = TestUtils.createAnswer(answerText + 0, true);
        Answer answer2 = TestUtils.createAnswer(answerText + 1, false);
        Answer answer3 = TestUtils.createAnswer(answerText + 3, true);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        return createTestQuestionEntry(category, answers, person, question);
    }

    public static QuestionEntry createQuestionEntry(Category category, Answer answer, Person person, Question question) {
        QuestionEntry questionEntry = new QuestionEntry();
        questionEntry.setCategory(category);
        questionEntry.setAnswer(answer);
        questionEntry.setPerson(person);
        questionEntry.setQuestion(question);
        questionEntry.setApproved(true);
        answer.setQuestionEntry(questionEntry);
        return questionEntry;
    }

    public static TestQuestionEntry createTestQuestionEntry(Category category, List<Answer> answers, Person person, Question question) {
        TestQuestionEntry questionEntry = new TestQuestionEntry();
        questionEntry.setCategory(category);
        questionEntry.setAnswers(answers);
        questionEntry.setPerson(person);
        questionEntry.setQuestion(question);
        questionEntry.setApproved(true);

        for (Answer answer : answers) {
            answer.setQuestionEntry(questionEntry);
        }
        return questionEntry;
    }

    public static Test createTest(int i) {
        return createTest(TestValues.TEST_NAMES[i], TestValues.TEST_PATHS[i], TEST_QUESTION_NUMBERS[i], TEST_TAGS[i]);
    }

    public static Test createTest(String name, String pathName, long questionsNumber, String tags) {
        Test test = new Test();
        test.setName(name);
        test.setPathName(pathName);
        test.setUpdatedDate(new Date());
        test.setQuestionsNumber(questionsNumber);
        test.setTags(tags);
        return test;
    }

    public static Question createQuestion(String questionText) {
        Question question = new Question();
        question.setText(questionText);
        return question;
    }

    public static Comment createComment(int i, Person person, CommentType type, int referenceId) {
        return createComment(TestValues.COMMENTS[i], person, type, referenceId);
    }
    /*

public static PossibleAnswer createPossibleAnswer(Answer answer, boolean isRight) {
    PossibleAnswer possibleAnswer = new PossibleAnswer();
    possibleAnswer.setAnswer(answer);
    possibleAnswer.setRight(isRight);
    return possibleAnswer;
} */

    public static Answer createAnswer(String answerText, boolean correct) {
        Answer answer = new Answer();
        answer.setText(answerText);
        answer.setCorrect(correct);
        return answer;
    }


    public static Category createCategory(int i, Article article) {
        return createCategory(CATEGORY_NAMES[i], CATEGORY_PATHNAMES[i],
                article);
    }

    public static Category createCategory(String name, String pathName, Article article) {
        Category category = new Category();
        category.setName(name);
        category.setPathName(pathName);
        category.setArticle(article);
        category.setSubCategories(new ArrayList<>());
  //      category.setHidden(false);
        return category;
    }

    public static Comment createComment(String commentText, Person user, CommentType type, int referenceId) {
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setCreatedDate(new Date());
        comment.setCommentType(type);
        comment.setUser(user);
        comment.setReferenceId(referenceId);
        return comment;
    }

    public static TestExam createTestExam(Category category, Person person, int percent) {
        TestExam exam = new TestExam();

        exam.setCategory(category);
        exam.setPercent(percent);
        exam.setPerson(person);
        exam.setDate(new Date());
        return exam;
    }

    public static QuestionExam createQuestionExam(Category category, Person person, int percent) {
        QuestionExam exam = new QuestionExam();
        exam.setCategory(category);
        exam.setPercent(percent);
        exam.setPerson(person);
        exam.setDate(new Date());
        return exam;
    }

    public static Article createArticle(int i, Person author) {
        Article article = new Article();
        article.setUrl(ARTICLE_URLS[i]);
        article.setText(ARTICLE_TEXT[i]);
        article.setImage(ARTICLE_IMAGE[i]);
        article.setDescription(ARTICLE_DESC[i]);
        article.setKeywords(ARTICLE_KEYWORDS[i]);
        article.setCreatedDate(new Date());
        article.setAuthor(author);
        article.setTitle(ARTICLE_TITLE[i]);
        return article;
    }

    public static Language createLanguage(int i) {
        Language language = new Language();
        language.setCode(LanguageCode.valueOf(LANGUAGE_CODE[i]));
        language.setDescription(LANGUAGE_DESCRIPTION[i]);
        return language;
    }

   /*
    public static UserTest createUserTest(int ansQuestions, boolean isPassed, Date date, int totalQuestion, Person person, List<UserAnswer> userAnswers) {
        UserTest userTest = new UserTest();
        userTest.setAnsweredQuestions(ansQuestions);
        userTest.setPassed(isPassed);
        userTest.setPassedDate(date);
        userTest.setTotalQuestion(totalQuestion);
        userTest.setPerson(person);
        userTest.setUserAnswers(userAnswers);
        return userTest;
    }

    public static UserAnswerItem createUserAnswerItem(boolean isRightAnswer,PossibleAnswer possibleAnswer) {
        UserAnswerItem userAnswer = new UserAnswerItem();
        userAnswer.setRightAnswer(isRightAnswer);
        userAnswer.setPossibleAnswer(possibleAnswer);
        return userAnswer;
    }

     public static UserAnswer createUserAnswer(UserTest userTest, List<UserAnswerItem> answers,boolean isRightAnswer) {
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserTest(userTest);
        userAnswer.setAnswers(answers);
        userAnswer.setRight(isRightAnswer);
        return userAnswer;
    }

    public static Exam createExam(Person author, Date createdDate, String examName, int quantity, Test exam) {
        Exam exam = new Exam();
        exam.setAuthor(author);
        exam.setCreatedDate(createdDate);
        exam.setName(examName);
        exam.setQuantity(quantity);
        exam.setTest(exam);
        return exam;
    }

    public static ExamRowEtalon createExamRowEtalon(Category category, int quantity,Map<Integer, Level> levels) {
        ExamRowEtalon examCB1 = new ExamRowEtalon();
        examCB1.setCategory(category);
        examCB1.setQuantity(quantity);
        examCB1.setLevel(levels);
        return examCB1;

    }

    public static Level createLevel(int level, int minLevel, int maxLevel) {
        Level levelBean = new Level();
        levelBean.setLevel(level);
        levelBean.setMinLevelCount(minLevel);
        levelBean.setMaxLevelCount(maxLevel);
        return levelBean;
    }
*/
}
