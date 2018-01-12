package util;

import model.QuestionEntry;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 21:40:34
 * To change this template use File | Settings | File Templates.
 */
public class SyncUtility {
    private AccessToDBUtility a;

    public void syncQuestionEntries(Map<Integer,
            QuestionEntry> questions, int categoryId) throws SQLException {
        for (QuestionEntry questionEntry : questions.values()) {
            int questionId = a.addQuestionText(
                    questionEntry.getQuestion().getText());
            int answerId = a.addAnswerText(
                    questionEntry.getAnswer().getText());
            a.addQuestionEntry(categoryId, questionId, answerId);
        }
    }
}
