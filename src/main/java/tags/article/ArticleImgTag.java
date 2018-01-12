package tags.article;

import model.article.Article;
import util.GeneralUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 06.08.2016.
 */
public class ArticleImgTag extends TagSupport {

    public int doStartTag() {
        try {
            ArticleTag parent =
                    (ArticleTag) findAncestorWithClass(this, ArticleTag.class);
            Article article = parent.getArticle();
            JspWriter out = pageContext.getOut();
            String image = article.getImage();
            String result = "";
            if (!GeneralUtility.isEmpty(image)) {
                String title = article.getTitle();
                result = "<img class='categoryImage'src='" + pageContext.getServletContext().getContextPath() + image + "'" +
                        "alt='" + title + "' title='" + title + "' width='280' height='200'>";
            }
            out.print(result);
        } catch (IOException ioe) {
            System.out.println("Error in ArticleImgTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
