package tags.selectTestCategory;

import model.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 20.05.2016.
 */
public class TestPathNameTag extends TagSupport {

    public int doStartTag() {
        try {
            TestTag parent =
                    (TestTag) findAncestorWithClass(this, TestTag.class);
            Test test = parent.getTest();
            JspWriter out = pageContext.getOut();
            out.print(test.getPathName());
        } catch (IOException ioe) {
            System.out.println("Error in TestPathNameTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
