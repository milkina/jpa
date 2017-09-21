package main.java.tags.article;

import main.java.model.article.Article;
import main.java.util.CategoryUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class ArticleUrlTag extends TagSupport {

    public int doStartTag() {
        try {
            ArticleTag parent =
                    (ArticleTag) findAncestorWithClass(this, ArticleTag.class);
            Article article = parent.getArticle();
            JspWriter out = pageContext.getOut();
            String pathName = article.getUrl();
            out.print("/" + pathName);
        } catch (IOException ioe) {
            System.out.println("Error in ArticleUrlTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}