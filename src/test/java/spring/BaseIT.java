package spring;

import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;
import model.Language;
import model.QuestionEntry;
import model.Test;
import model.TestQuestionEntry;
import model.article.Article;
import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;
import model.person.PersonInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import spring.services.answer.AnswerService;
import spring.services.article.ArticleService;
import spring.services.category.CategoryService;
import spring.services.comment.CommentService;
import spring.services.course.CourseService;
import spring.services.exam.ExamService;
import spring.services.language.LanguageService;
import spring.services.person.PersonService;
import spring.services.question.QuestionService;
import utils.TestUtils;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static utils.TestUtils.createArticle;
import static utils.TestUtils.createComment;
import static utils.TestUtils.createLanguage;
import static utils.TestUtils.createQuestionEntry;
import static utils.TestUtils.createQuestionExam;
import static utils.TestUtils.createTestExam;
import static utils.TestUtils.createTestQuestionEntry;

@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class BaseIT extends AbstractTestNGSpringContextTests {
    private static boolean isSet;
    @Resource
    protected PersonService personService;
    @Resource
    protected CommentService commentService;
    @Resource
    protected ArticleService articleService;
    @Resource
    protected CourseService courseService;
    @Resource
    protected CategoryService categoryService;
    @Resource
    protected LanguageService languageService;
    @Resource
    protected ExamService examService;
    @Resource
    protected QuestionService questionService;
    @Resource
    protected AnswerService answerService;

    protected static model.Test[] tests;
    protected static Person[] persons;
    protected static PersonInfo personInfo[];
    protected static Category[] categories;
    protected static QuestionEntry[] questionEntries;
    protected static TestQuestionEntry[] testQuestionEntries;
    protected static Comment[] comments;
    protected static Article[] articles;
    protected static AbstractExam[] exams;
    protected static Language[] languages;

    @BeforeClass
    public void setUp() {
        if (!isSet) {
            prepareTests();
            preparePersons();
            prepareCategories();
            prepareQuestionEntries();
            prepareTestQuestionEntries();

            prepareAnsweredQuestions();
            prepareArticles();
            prepareComments();

            prepareExams();
            prepareLanguages();
            isSet = true;
        }
    }

    private void prepareExams() {
        exams = new AbstractExam[2];
        exams[0] = createTestExam(categories[2], persons[0], 50);
        exams[1] = createQuestionExam(categories[1], persons[0], 50);
        examService.createExam(exams[0]);
        examService.createExam(exams[1]);
    }

    private void prepareLanguages() {
        languages = new Language[2];
        languages[0] = createLanguage(0);
        languages[1] = createLanguage(1);
        languageService.createLanguage(languages[0]);
        languageService.createLanguage(languages[1]);
    }

    private void prepareArticles() {
        articles = new Article[3];
        articles[0] = createArticle(0, persons[0]);
        articles[1] = createArticle(1, persons[0]);
        articles[2] = createArticle(2, persons[1]);
        articles[0] = articleService.addArticle(articles[0]);
        articles[1] = articleService.addArticle(articles[1]);
        articles[2] = articleService.addArticle(articles[2]);
    }

    private void prepareComments() {
        comments = new Comment[3];
        comments[0] = createComment(0, persons[0], CommentType.ARTICLE, articles[0].getId());
        comments[1] = createComment(1, persons[1], CommentType.QUESTION, questionEntries[0].getId());
        comments[2] = createComment(2, persons[2], CommentType.ARTICLE, articles[0].getId());

        comments[0] = commentService.save(comments[0]);
        comments[1] = commentService.save(comments[1]);
        comments[2] = commentService.save(comments[2]);
    }

    private void prepareAnsweredQuestions() {
        List<AbstractQuestionEntry> answeredQuestions = new ArrayList<>();
        answeredQuestions.add(questionEntries[0]);
        answeredQuestions.add(questionEntries[1]);
        persons[0].setAnsweredQuestions(answeredQuestions);
        personService.save(persons[0]);
    }

    private void prepareQuestionEntries() {
        int questionLength = 5;
        questionEntries = new QuestionEntry[questionLength];
        questionEntries[0] = createQuestionEntry(0, categories[0], persons[0]);
        questionEntries[1] = createQuestionEntry(1, categories[1], persons[1]);
        questionEntries[2] = createQuestionEntry(2, categories[1], persons[2]);
        questionEntries[3] = createQuestionEntry(3, categories[3], persons[2]);
        questionEntries[4] = createQuestionEntry(4, categories[2], persons[2]);
        questionEntries[0] = (QuestionEntry) questionService.addQuestionEntry(questionEntries[0]);
        questionEntries[1] = (QuestionEntry) questionService.addQuestionEntry(questionEntries[1]);
        questionEntries[2] = (QuestionEntry) questionService.addQuestionEntry(questionEntries[2]);
        questionEntries[3] = (QuestionEntry) questionService.addQuestionEntry(questionEntries[3]);
        questionEntries[4] = (QuestionEntry) questionService.addQuestionEntry(questionEntries[4]);
    }

    private void prepareTestQuestionEntries() {
        int questionLength = 2;
        testQuestionEntries = new TestQuestionEntry[questionLength];
        testQuestionEntries[0] = createTestQuestionEntry(0, categories[1], persons[2]);
        testQuestionEntries[1] = createTestQuestionEntry(1, categories[1], persons[2]);
        testQuestionEntries[0] = (TestQuestionEntry) questionService.addQuestionEntry(testQuestionEntries[0]);
        testQuestionEntries[1] = (TestQuestionEntry) questionService.addQuestionEntry(testQuestionEntries[1]);
    }

    private void preparePersons() {
        int personLength = 3;
        persons = new Person[personLength];
        personInfo = new PersonInfo[personLength];
        for (int i = 0; i < personLength; i++) {
            persons[i] = TestUtils.createPerson(i);
            persons[i] = personService.save(persons[i]);
        }
    }

    private void prepareTests() {
        int testLength = 3;
        tests = new model.Test[testLength];

        for (int i = 0; i < testLength; i++) {
            tests[i] = TestUtils.createTest(i);
        }
        tests = courseService.addCourses(tests);
    }

    private void prepareCategories() {
        int categoryLength = 4;
        categories = new Category[categoryLength];
        Article article0 = createArticle(6, persons[0]);
        Article article1 = createArticle(7, persons[0]);
        Article article2 = createArticle(8, persons[0]);
        articleService.addArticle(article0);
        articleService.addArticle(article1);
        articleService.addArticle(article2);

        categories[0] = createCategory(0, tests[0], article0);
        categories[1] = createCategory(1, tests[0], article1);
        categories[2] = createCategory(2, tests[1], article2);
        categories[3] = createCategory(3, tests[2], article2);
    }

    protected Category createCategory(int i, Test test, Article article) {
        Category category = TestUtils.createCategory(i, article);
        category = categoryService.create(category);
        return categoryService.addCategoryToCourse(test, category);
    }

    protected Category createCategoryWithTest(int testId, int categoryId) {
        model.Test test = TestUtils.createTest(testId);
        test = courseService.create(test);
        Article article = createArticle(categoryId, persons[0]);
        articleService.addArticle(article);
        return createCategory(categoryId, test, article);
    }

    protected Category createCategoryWithArticle(model.Test test, int categoryId) {
        Article article = createArticle(categoryId, persons[0]);
        articleService.addArticle(article);
        return createCategory(categoryId, test, article);
    }
}
