package model.comment;

import util.TestUtility;
import util.article.ArticleUtility;
import util.question.QuestionEntryUtility;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.10.2012
 * Time: 12:57:44
 * To change this template use File | Settings | File Templates.
 */
public enum CommentType {
    ALL,
    QUESTION {
        public String getUrl(int referenceId) {
            return QuestionEntryUtility.getQuestionUrl(referenceId);
        }
    },
    ARTICLE {
        public String getUrl(int referenceId) {
            return ArticleUtility.getArticleUrl(referenceId);
        }
    },
    TEST {
        public String getUrl(int referenceId) {
            return TestUtility.getTestUrl(referenceId);
        }
    };

    //TODO
    public String getUrl(int referenceId) {
        return "";
    }
}
