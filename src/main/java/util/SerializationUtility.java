package main.java.util;

import main.java.data.category.CategoryHandler;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Category;
import main.java.model.QuestionEntry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 9:49:14
 * To change this template use File | Settings | File Templates.
 */
public class SerializationUtility {
    public static  void main(String[] args) {
        SerializationUtility serializationUtility = new SerializationUtility();
      //  serializationUtility.serialize();
    }

 /*   public void serialize() {
        ObjectOutputStream os =null;
        String fileName = "testSer";
        int i = 1;
        CategoryHandler categoryHandler = new CategoryHandler();
        List<Category> list = categoryHandler.getAllCategoriesList();
        QuestionEntryHandler questionEntryHandler=new QuestionEntryHandler();
        try {

            for (Category category : list) {

                Map<Integer,QuestionEntry> questions = questionEntryHandler.getAllQuestions(category);
                os = new ObjectOutputStream(new FileOutputStream(fileName+i++ +"ser"));
                os.writeObject(questions); // 3
                os.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Tatyana\\testingcenter\\src\\main\\webapp\\testSer.ser1");
            ObjectInputStream ois = new ObjectInputStream(fis);
            //List<QuestionEntry> questions = (List<QuestionEntry>) ois.readObject(); // 4
            Object questions =  ois.readObject(); // 4
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
