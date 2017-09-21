package main.java.util;

import main.java.data.category.CategoryHandler;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Category;
import main.java.model.QuestionEntry;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 21:40:34
 * To change this template use File | Settings | File Templates.
 */
public class SyncUtility {
    AccessToDBUtility a;

   /* public void syncToRH() {
        CategoryHandler categoryHandler = new CategoryHandler();
        List<Category> list = categoryHandler.getAllCategoriesList();
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        a = new AccessToDBUtility();
        a.createConnection();
        for (Category category : list) {
            try{
            int categoryId = a.addCategory(category);
                System.out.println("Category: "+category.getName()+" added");
            Map<Integer,QuestionEntry> questions = questionEntryHandler.getAllQuestions(category);
            syncQuestionEntries(questions, categoryId);
            }catch(SQLException e){
               a.closeConnection();
               break; 
            }
        }
        a.closeConnection();
    }*/

    public void syncQuestionEntries(Map<Integer,QuestionEntry> questions, int categoryId) throws SQLException{
        for (QuestionEntry questionEntry : questions.values()) {
            int questionId = a.addQuestionText(questionEntry.getQuestion().getText());
            int answerId = a.addAnswerText(questionEntry.getAnswer().getText());
            a.addQuestionEntry(categoryId, questionId, answerId);
        }
    }

}
