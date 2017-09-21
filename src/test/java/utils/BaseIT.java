package utils;

import main.java.data.article.ArticleBeanI;
import main.java.data.article.ArticleHandler;
import main.java.data.category.CategoryBeanI;
import main.java.data.category.CategoryHandler;
import main.java.data.comment.CommentBeanI;
import main.java.data.comment.CommentHandler;
import main.java.data.person.PersonBeanI;
import main.java.data.person.PersonHandler;
import main.java.data.questionEntry.QuestionEntryBeanI;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.data.test.TestBeanI;
import main.java.data.test.TestHandler;
import main.java.model.Category;
import main.java.model.QuestionEntry;
import main.java.model.Test;
import main.java.model.article.Article;
import main.java.model.comment.Comment;
import main.java.model.comment.CommentType;
import main.java.model.person.Person;
import main.java.model.person.PersonInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.TestUtils.*;

/**
 * Created by Tatyana on 02.04.2016.
 */
public class BaseIT {
    protected static TestBeanI testBean;
    protected static CategoryBeanI categoryBean;
    protected static QuestionEntryBeanI questionEntryBean;
    protected static PersonBeanI personBean;
    protected static CommentBeanI commentBean;
    protected static ArticleBeanI articleBean;

    protected static TestHandler testHandler;
    protected static CategoryHandler categoryHandler;
    protected static QuestionEntryHandler questionEntryHandler;
    protected static PersonHandler personHandler;
    protected static CommentHandler commentHandler;
    protected static ArticleHandler articleHandler;

    protected static main.java.model.Test[] tests;
    protected static Person[] persons;
    protected static PersonInfo personInfo[];
    protected static Category[] categories;
    protected static QuestionEntry[] questionEntries;
    protected static Comment[] comments;
    protected static Article[] articles;

    static {
        prepareBeans();
        prepareHandlers();
        prepareTests();
        preparePersons();
        prepareCategories();
        prepareQuestionEntries();

        prepareAnsweredQuestions();
        prepareComments();

        prepareArticles();
    }

    private static void prepareArticles() {
        articles = new Article[3];
        articles[0] = createArticle(0, persons[0]);
        articles[1] = createArticle(1, persons[0]);
        articles[2] = createArticle(2, persons[0]);
        articles = articleHandler.addArticles(articles);

    }

    private static void prepareComments() {
        comments = new Comment[3];
        comments[0] = createComment(0, persons[0], CommentType.ARTICLE);
        comments[1] = createComment(1, persons[1], CommentType.QUESTION);
        comments[2] = createComment(2, persons[2], CommentType.ARTICLE);

        comments[0] = commentHandler.addComment(comments[0]);
        comments[1] = commentHandler.addComment(comments[1]);
        comments[2] = commentHandler.addComment(comments[2]);
    }

    private static void prepareAnsweredQuestions() {
        List<QuestionEntry> answeredQuestions = new ArrayList<QuestionEntry>();
        answeredQuestions.add(questionEntries[0]);
        answeredQuestions.add(questionEntries[1]);
        persons[0].setAnsweredQuestions(answeredQuestions);
        personHandler.updatePerson(persons[0]);
    }

    private static void prepareQuestionEntries() {
        int questionLength = 5;
        questionEntries = new QuestionEntry[questionLength];
        questionEntries[0] = createQuestionEntry(0, categories[0], persons[0]);
        questionEntries[1] = createQuestionEntry(1, categories[1], persons[1]);
        questionEntries[2] = createQuestionEntry(2, categories[1], persons[2]);
        questionEntries[3] = createQuestionEntry(3, categories[3], persons[2]);
        questionEntries[4] = createQuestionEntry(4, categories[2], persons[2]);
        questionEntryHandler.addQuestionEntries(questionEntries);
    }

    private static void preparePersons() {
        int personLength = 3;
        persons = new Person[personLength];
        personInfo = new PersonInfo[personLength];
        for (int i = 0; i < personLength; i++) {
            persons[i] = TestUtils.createPerson(i);
            persons[i] = personHandler.addPerson(persons[i]);
        }
    }

    private static void prepareTests() {
        int testLength = 3;
        tests = new main.java.model.Test[testLength];

        for (int i = 0; i < testLength; i++) {
            tests[i] = TestUtils.createTest(i);
        }
        tests = testHandler.addTests(tests);
    }

    private static void prepareHandlers() {
        testHandler = new TestHandler(testBean);
        categoryHandler = new CategoryHandler(categoryBean, testBean);
        questionEntryHandler = new QuestionEntryHandler(questionEntryBean);
        personHandler = new PersonHandler(personBean);
        commentHandler = new CommentHandler(commentBean);
        articleHandler = new ArticleHandler(articleBean);
    }

    private static void prepareBeans() {
        testBean = InitialContextHandler.getTestBean();
        categoryBean = InitialContextHandler.getCategoryBean();
        questionEntryBean = InitialContextHandler.getQuestionEntryBean();
        personBean = InitialContextHandler.getPersonBean();
        commentBean = InitialContextHandler.getCommentBean();
        articleBean = InitialContextHandler.getArticleBean();
    }

    private static void prepareCategories() {
        int categoryLength = 4;
        categories = new Category[categoryLength];
        Article article0 = createArticle(6, persons[0]);
        Article article1 = createArticle(7, persons[0]);
        Article article2 = createArticle(8, persons[0]);
        articleHandler.addArticle(article0);
        articleHandler.addArticle(article1);
        articleHandler.addArticle(article2);

        categories[0] = createCategory(0, tests[0], article0);
        categories[1] = createCategory(1, tests[0], article1);
        categories[2] = createCategory(2, tests[1], article2);
        categories[3] = createCategory(3, tests[2], article2);
    }

    protected static Category createCategory(int i, Test test, Article article) {
        Category category = TestUtils.createCategory(i, article);
        category = categoryHandler.createCategory(category);
        return categoryHandler.addCategoryToTest(test, category);
    }

    public static Category addTest(Category category, Test test) {
        category.addTest(test);
        categoryHandler.updateCategory(category);
        return category;
    }

    protected Category createCategoryWithTest(int testId, int categoryId) {
        main.java.model.Test test = TestUtils.createTest(testId);
        test = testHandler.addTest(test);
        Article article = createArticle(categoryId, persons[0]);
        articleHandler.addArticle(article);
        return createCategory(categoryId, test, article);
    }

    protected Category createCategoryWithArticle(main.java.model.Test test, int categoryId) {
        Article article = createArticle(categoryId, persons[0]);
        articleHandler.addArticle(article);
        return createCategory(categoryId, test, article);
    }
}
