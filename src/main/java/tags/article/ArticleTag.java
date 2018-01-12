package tags.article;

import model.article.Article;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class ArticleTag extends BodyTagSupport {
    private Article article;
    private Iterator<Article> iterator;

    public int doStartTag() {
        setIterator();
        if (iterator.hasNext()) {
            setArticle(iterator.next());
            return EVAL_BODY_TAG;
        }
        return SKIP_BODY;
    }

    private void setIterator() {
        ArticleListTag parent =
                (ArticleListTag) findAncestorWithClass(this, ArticleListTag.class);
        List<Article> articleList = parent.getArticleList();
        iterator = articleList.iterator();
    }

    public int doAfterBody() {
        BodyContent body = getBodyContent();
        try {
            JspWriter out = body.getEnclosingWriter();
            out.println(body.getString());
            body.clearBody(); // Clear for next evaluation
        } catch (IOException ioe) {
            System.out.println("Error in ArticleTag: " + ioe);
        }
        if (iterator.hasNext()) {
            setArticle(iterator.next());
            return EVAL_BODY_TAG;
        } else {
            iterator = null;
            article = null;
            return SKIP_BODY;
        }
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}


