package tags.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class MenuPrivacyTag extends MenuTag {
    public int doStartTag() {
        try {
            String localeHref = getLocalHref((HttpServletRequest) pageContext.getRequest(), "menu.privacy");
            String href = getHref("privacy", localeHref);
            JspWriter out = pageContext.getOut();
            out.print(href);
        } catch (IOException exception) {
            System.out.println("Error in MenuPrivacyTag: " + exception);
        }
        return SKIP_BODY;
    }
}