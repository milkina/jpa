package tags.article;

import model.article.Article;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 08.07.2016.
 */
public class ArticleDescriptionTag extends TagSupport {

    public int doStartTag() {
        try {
            ArticleTag parent =
                    (ArticleTag) findAncestorWithClass(this, ArticleTag.class);
            Article article = parent.getArticle();
            JspWriter out = pageContext.getOut();
            out.print(article.getDescription());
        } catch (IOException ioe) {
            System.out.println("Error in ArticleDescriptionTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
