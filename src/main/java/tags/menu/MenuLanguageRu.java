package tags.menu;

import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MenuLanguageRu extends TagSupport {

    public int doStartTag() {
        try {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

            String localAddress = new UrlPathHelper().getOriginatingRequestUri(request);
            String contextPath = request.getServletContext().getContextPath();

            localAddress = localAddress.replaceAll(contextPath, "");
            localAddress = localAddress.replaceAll("/ru", "");

            JspWriter out = pageContext.getOut();
            out.print(contextPath + "/ru" + localAddress);
        } catch (IOException exception) {
            System.out.println("Error in MenuTestsTag: " + exception);
        }
        return SKIP_BODY;
    }
}

