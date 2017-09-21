package main.java.tags.article;

import main.java.model.article.Article;
import main.java.util.CategoryUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 08.07.2016.
 */
public class ArticleTitleTag extends TagSupport {

    public int doStartTag() {
        try {
            ArticleTag parent =
                    (ArticleTag) findAncestorWithClass(this, ArticleTag.class);
            Article article = parent.getArticle();
            JspWriter out = pageContext.getOut();
            String title = article.getTitle();
            out.print(title);
        } catch (IOException ioe) {
            System.out.println("Error in ArticleTitleTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}
