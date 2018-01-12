package tags.selectTestCategory;

import model.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 20.05.2016.
 */
public class TestNameTag extends TagSupport {

    public int doStartTag() {
        try {
            TestTag parent =
                    (TestTag) findAncestorWithClass(this, TestTag.class);
            Test test = parent.getTest();
            JspWriter out = pageContext.getOut();
            out.print(test.getName());
        } catch (IOException ioe) {
            System.out.println("Error in TestNameTag: " + ioe);
        }
        return SKIP_BODY;
    }
}

