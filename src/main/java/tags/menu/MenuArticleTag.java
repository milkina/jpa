package tags.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class MenuArticleTag extends MenuTag {
    public int doStartTag() {
        try {
            String localeHref = getLocalHref((HttpServletRequest) pageContext.getRequest(), "menu.articles");
            String href = getHref("show-all-articles", localeHref);
            JspWriter out = pageContext.getOut();
            out.print(href);
        } catch (IOException exception) {
            System.out.println("Error in MenuArticleTag: " + exception);
        }
        return SKIP_BODY;
    }
}
