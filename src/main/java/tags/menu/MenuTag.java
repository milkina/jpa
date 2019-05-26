package tags.menu;

import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;

import static util.GeneralUtility.getResourceValue;

public class MenuTag extends TagSupport {

    public String getHref(String currentPage, String path) {
        String href = "#";
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String localAddress = request.getRequestURI();
        if (!localAddress.contains(currentPage)) {
            href = request.getContextPath() + "/" + path;
        }
        return href;
    }

    public String getLocalHref(HttpServletRequest request, String key) {
        Locale locale = RequestContextUtils.getLocale(request);
        return getResourceValue(locale, key, "label");
    }
}
