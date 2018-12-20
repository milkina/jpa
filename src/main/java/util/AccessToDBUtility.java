package util;

import model.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 21:21:25
 * To change this template use File | Settings | File Templates.
 */
public class AccessToDBUtility {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection connection;

   /* public AccessToDBUtility() {
        this.driver = "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource";
        this.url = "jdbc:mysql://127.12.113.1:3306/quiz";
        this.username = "admin";
        this.password = "csmdZfsVzBr6";
    }*/

    public AccessToDBUtility() {
        this.driver = "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource";
        this.url = "jdbc:mysql://127.11.235.1:3306/jpa";
        this.username = "admin";
        this.password = "G1Jy3bY1Hban";
    }

    public synchronized void createConnection() {
        /*  driver = "sun.jdbc.odbc.JdbcOdbcDriver";
   url = "jdbc:odbc:MSAccess";
   username = ""; // No username/password required
   password = "";   */
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("SQLException in createConnection" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException in createConnection " + e);
        }
    }

    public synchronized void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException in closeConnection" + e);
        }

    }

    public synchronized int addCategory(Category category) throws SQLException {
        String templateInsert = "INSERT INTO Category (name) VALUES(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(templateInsert);
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException in writeCategory" + e);
            throw e;
        }
        return getCategoryMaxId();
    }

    public synchronized int getCategoryMaxId() throws SQLException {
        String template = "SELECT max(ID) as qID from Category";
        int categoryID = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(template);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoryID = resultSet.getInt("qID");
            }
        } catch (SQLException e) {
            System.out.println("SQLException in getCategoryMaxId" + e);
            throw e;
        }
        return categoryID;
    }

    public int addQuestionText(String question) throws SQLException {
        String templateInsert = "INSERT INTO QUESTION_TEXT (question) VALUES(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(templateInsert);
            statement.setString(1, question);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException in addQuestionText" + e);
            throw e;
        }
        return getQuestionMaxId();
    }

    public synchronized int getQuestionMaxId() throws SQLException {
        String template = "SELECT max(ID) as qID from QUESTION_TEXT";
        int questionID = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(template);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionID = resultSet.getInt("qID");
            }
        } catch (SQLException e) {
            System.out.println("SQLException in getQuestionMaxId" + e);
            throw e;
        }
        return questionID;

    }

    public int addAnswerText(String answer) throws SQLException {
        String templateInsert = "INSERT INTO ANSWER_TEXT (answer) VALUES(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(templateInsert);
            statement.setString(1, answer);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException in addAnswerText" + e);
            throw e;

        }
        return getAnswerMaxId();
    }

    public synchronized int getAnswerMaxId() throws SQLException {
        String template = "SELECT max(ID) as qID from ANSWER_TEXT";
        int answerID = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(template);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                answerID = resultSet.getInt("qID");
            }
        } catch (SQLException e) {
            System.out.println("SQLException in getAnswerMaxId" + e);
            throw e;
        }
        return answerID;

    }

    public void addQuestionEntry(int categoryId, int questionId, int answerId)
            throws SQLException {
        String templateInsert =
                "INSERT INTO QUESTIONS (CATEGORY_ID,QUESTION_ID,ANSWER_ID) VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(templateInsert);
            statement.setInt(1, categoryId);
            statement.setInt(2, questionId);
            statement.setInt(3, answerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException in addQuestionEntry" + e);
            throw e;
        }
    }
}
