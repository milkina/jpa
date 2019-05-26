package tags.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import static util.AllConstants.TESTS_PAGE;

public class MenuTestsTag extends MenuTag {

    public int doStartTag() {
        try {
            String localeHref = getLocalHref((HttpServletRequest) pageContext.getRequest(), "menu.tests");
            String href = getHref(TESTS_PAGE, localeHref);
            JspWriter out = pageContext.getOut();
            out.print(href);
        } catch (IOException exception) {
            System.out.println("Error in MenuTestsTag: " + exception);
        }
        return SKIP_BODY;
    }
}