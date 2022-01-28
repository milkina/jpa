package spring.services.question;

import model.AbstractQuestionEntry;
import model.Answer;
import model.Category;
import model.Question;
import model.QuestionEntry;
import model.QuestionType;
import model.Test;
import model.TestQuestionEntry;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repositories.QuestionRepository;
import spring.services.answer.AnswerService;
import spring.services.category.CategoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AnswerService answerService;

    @Override
    public List<AbstractQuestionEntry> getAllQuestions(Category category) {
        return questionRepository.getAllQuestions(category);
    }

    @Override
    public List<AbstractQuestionEntry> getAllQuestions(List<Category> category) {
        return questionRepository.getAllQuestionsForCategories(category);
    }

    @Override
    public List<AbstractQuestionEntry> getAllTestQuestions(Category category) {
        return questionRepository.getAllTestQuestions(category);
    }

    @Override
    public List<AbstractQuestionEntry> getAnsweredQuestions(Category category, Person person) {
        return questionRepository.getAnsweredQuestions(category, person);
    }

    @Override
    public List<AbstractQuestionEntry> getAnsweredQuestions(List<Category> category, Person person) {
        return questionRepository.getAnsweredQuestionsForCategories(category, person);
    }

    @Override
    public List<AbstractQuestionEntry> getNotAnsweredQuestions(Category category, Person person) {
        return questionRepository.getNotAnsweredQuestions(category, person);
    }

    @Override
    public List<AbstractQuestionEntry> getNotAnsweredQuestions(List<Category> category, Person person) {
        return questionRepository.getNotAnsweredQuestionsForCategories(category, person);
    }

    @Override
    public List<AbstractQuestionEntry> getQuestions(Category category,
                                                    Person person,
                                                    String questionType) {
        if (person == null || questionType == null
                || QuestionType.ALL.toString().equals(questionType)) {
            return getAllQuestions(category);
        } else if (QuestionType.ANSWERED.toString().equals(questionType)) {
            return getAnsweredQuestions(category, person);
        } else {
            return getNotAnsweredQuestions(category, person);
        }
    }

    @Override
    public List<AbstractQuestionEntry> getQuestions(List<Category> categories,
                                                    Person person,
                                                    String questionType) {
        if (person == null || questionType == null
                || QuestionType.ALL.toString().equals(questionType)) {
            return getAllQuestions(categories);
        } else if (QuestionType.ANSWERED.toString().equals(questionType)) {
            return getAnsweredQuestions(categories, person);
        } else {
            return getNotAnsweredQuestions(categories, person);
        }
    }

    @Override
    public List<AbstractQuestionEntry> getQuestionsForQuiz(String[] categoryPaths,
                                                           Person person,
                                                           String questionType,
                                                           int count) {
        List<AbstractQuestionEntry> result = getCategoryPaths(categoryPaths, person, questionType);
        Collections.shuffle(result);
        count = Math.min(result.size(), count);
        return result.subList(0, count);
    }

    private List<AbstractQuestionEntry> getCategoryPaths(String[] categoryPaths, Person person, String questionType) {
        List<Category> categories = new ArrayList<>();
        for (String pathName : categoryPaths) {
            Category category = categoryService.getCategory(pathName);
            categories.add(category);
            if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
                categories.addAll(category.getSubCategories());
            }
        }
        return getQuestions(categories, person, questionType);
    }

    public List<AbstractQuestionEntry> getAllAbstractQuestions(Category category) {
        return questionRepository.getAllAbstractQuestions(category);
    }

    @Override
    public Map<Integer, AbstractQuestionEntry> getAllAbstractQuestionsMap(Category category) {
        List<AbstractQuestionEntry> list = getAllAbstractQuestions(category);
        Map<Integer, AbstractQuestionEntry> map = new TreeMap<>();
        for (AbstractQuestionEntry questionEntry : list) {
            map.put(questionEntry.getId(), questionEntry);
        }
        return map;
    }

    @Override
    @Transactional
    public void moveBatch(Category oldCategory, Category category,
                          Integer from, Integer to) {
        List<AbstractQuestionEntry> list = getAllAbstractQuestions(oldCategory);
        for (int i = from - 1; i < to; i++) {
            AbstractQuestionEntry questionEntry = list.get(i);
            questionEntry.setCategory(category);
        }
    }

    @Transactional
    @Override
    public AbstractQuestionEntry updateQuestionEntry(AbstractQuestionEntry questionEntry) {
        categoryService.update(questionEntry.getCategory());
        return questionRepository.save(questionEntry);
    }

    public void removeAnswer(Answer answer) {
        questionRepository.delete(answer.getId());
    }

    @Transactional
    @Override
    public void removeAnswers(AbstractQuestionEntry questionEntry) {
        List<Answer> answers = questionEntry.getAnswers();
        questionEntry.setAnswers(null);
        updateQuestionEntry(questionEntry);
        answerService.removeAnswers(answers);
    }

    @Transactional
    @Override
    public void swapQuestionEntries(AbstractQuestionEntry q1, AbstractQuestionEntry q2) {
        int id1 = q1.getOrderColumn();
        int id2 = q2.getOrderColumn();
        q1.setOrderColumn(id2);
        q2.setOrderColumn(id1);
        updateQuestionEntry(q1);
        updateQuestionEntry(q2);
    }

    @Override
    public AbstractQuestionEntry getQuestionEntry(int questionId) {
        return questionRepository.findOne(questionId);
    }

    @Override
    public AbstractQuestionEntry getPreviousQuestionEntry(int orderColumn) {
        List<QuestionEntry> list = questionRepository.getPreviousQuestionEntry(orderColumn);
        AbstractQuestionEntry result = null;
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public void moveQuestionEntryUp(int questionId) {
        AbstractQuestionEntry questionEntry = getQuestionEntry(questionId);
        AbstractQuestionEntry previousQuestionEntry =
                getPreviousQuestionEntry(questionEntry.getOrderColumn());
        if (previousQuestionEntry != null) {
            swapQuestionEntries(questionEntry, previousQuestionEntry);
        }
    }

    @Override
    public void deleteQuestionEntry(int id) {
        changeCategoryCount(id, -1);
        questionRepository.delete(id);
    }

    private void changeCategoryCount(int id, int count) {
        AbstractQuestionEntry questionEntry = getQuestionEntry(id);
        Category category = null;
        if (questionEntry.getApproved()) {
            category = questionEntry.getCategory();
            questionEntry.changeCategoryCount(count);
            categoryService.update(category);
        }
    }

    @Override
    @Transactional
    public AbstractQuestionEntry addQuestionEntry(AbstractQuestionEntry questionEntry) {
        questionEntry.setCreatedDate(new Date());
        questionEntry = questionRepository.save(questionEntry);
        questionEntry.setOrderColumn(questionEntry.getId());
        // changeCategoryCount(questionEntry.getId(), 1);
        categoryService.update(questionEntry.getCategory());
        return questionEntry;
    }

    @Override
    public void addQuestionEntries(AbstractQuestionEntry[] questionEntries) {
        for (AbstractQuestionEntry questionEntry : questionEntries) {
            addQuestionEntry(questionEntry);
        }
    }

    @Transactional
    public List<Test> getQuestionEntryTests(int id) {
        AbstractQuestionEntry questionEntry =
                questionRepository.findOne(id);
        return questionEntry.getCategory().getTests();
    }

    @Transactional
    @Override
    public Test getFirstQuestionEntryTest(int id) {
        AbstractQuestionEntry questionEntry =
                questionRepository.findOne(id);
        return questionEntry.getCategory().getTests().get(0);
    }

    @Override
    public void changeQuestionToTestQuestion(int id) {
        changeQuestionType(id, QuestionType.TEST.toString());
    }

    @Override
    public void changeTestQuestionToQuestion(int id) {
        changeQuestionType(id, QuestionType.QUESTION.toString());
    }

    private void changeQuestionType(int id, String type) {
        questionRepository.changeQuestionType(type, id);
    }

    @Override
    public List<AbstractQuestionEntry> getQuestionsForExam(String[] categoryPaths, int count) {
        List<AbstractQuestionEntry> result = new ArrayList<>(questionRepository.getQuestionsForExamForBatch(Arrays.asList(categoryPaths)));
        Collections.shuffle(result);
        count = Math.min(result.size(), count);
        return result.subList(0, count);
    }

    @Override
    public List<AbstractQuestionEntry> getNotApprovedQuestions() {
        return questionRepository.getNotApprovedQuestions();
    }

    @Override
    public List<AbstractQuestionEntry> getPersonQuestions(int person) {
        return questionRepository.getPersonQuestions(person);
    }

    @Override
    public Question getQuestion(int id) {
        return questionRepository.getQuestion(id);
    }
}
