package tags.menu;

import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MenuLanguageEn extends TagSupport {

    public int doStartTag() {
        try {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            String localAddress = new UrlPathHelper().getOriginatingRequestUri(request);
            localAddress = localAddress.replaceAll("/ru", "");

            JspWriter out = pageContext.getOut();
            out.print(localAddress);
        } catch (IOException exception) {
            System.out.println("Error in MenuTestsTag: " + exception);
        }
        return SKIP_BODY;
    }
}
