package tags.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import static util.AllConstants.TASKS_PAGE;

public class MenuTaskTag extends MenuTag {

    public int doStartTag() {
        try {
            String localeHref = getLocalHref((HttpServletRequest) pageContext.getRequest(), "menu.tasks");
            String href = getHref(TASKS_PAGE, localeHref);
            JspWriter out = pageContext.getOut();
            out.print(href);
        } catch (IOException exception) {
            System.out.println("Error in MenuTaskTag: " + exception);
        }
        return SKIP_BODY;
    }
}