package main.java.tags.cacheTag;

import main.java.model.person.Person;
import main.java.util.GeneralUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TagSupport;

import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;

/**
 * Created by Tatyana on 14.05.2016.
 */
public class CacheTag extends TagSupport {

    public int doStartTag() {
        Person person = (Person) pageContext.getSession().getAttribute(PERSON_ATTRIBUTE);
        GeneralUtility generalUtility = new GeneralUtility();
        generalUtility.setIfModifiedSinceHeader((HttpServletRequest) pageContext.getRequest(),
                (HttpServletResponse) pageContext.getResponse(),
                person);
        return (SKIP_BODY);
    }
}