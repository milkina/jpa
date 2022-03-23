package tags.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import static util.AllConstants.QUESTIONS_PAGE;


public class MenuQuestionsTag extends MenuTag {

    public int doStartTag() {
        try {
            String localeHref = getLocalHref((HttpServletRequest) pageContext.getRequest(), "menu.questions");
            String href = getHref(QUESTIONS_PAGE, localeHref);
            JspWriter out = pageContext.getOut();
            out.print(href);
        } catch (IOException exception) {
            System.out.println("Error in MenuQuestionsTag: " + exception);
        }
        return SKIP_BODY;
    }
}