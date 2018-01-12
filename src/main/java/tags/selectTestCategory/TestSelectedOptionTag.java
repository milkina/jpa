package tags.selectTestCategory;

import model.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 20.05.2016.
 */
public class TestSelectedOptionTag extends TagSupport {

    public int doStartTag() {
        try {
            TestTag parent =
                    (TestTag) findAncestorWithClass(this, TestTag.class);
            Test test = parent.getTest();
            int testId = test.getId();
            String selected = testId == parent.getSelectedTestID()
                    ? "selected" : "";
            JspWriter out = pageContext.getOut();
            out.print(selected);
        } catch (IOException ioe) {
            System.out.println("Error in TestSelectedOptionTag: " + ioe);
        }
        return SKIP_BODY;
    }
}


