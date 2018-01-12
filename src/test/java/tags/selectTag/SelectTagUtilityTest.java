package tags.selectTag;

import junit.framework.Assert;
import model.comment.CommentType;
import tags.selectTag.SelectTagUtility;
import org.testng.annotations.Test;

/**
 * Created by Tatyana on 11.05.2016.
 */
public class SelectTagUtilityTest {
    String expectedResult = "<select name='%s'><option value='ALL' >ALL</option><option value='QUESTION' >QUESTION</option>" +
            "<option value='ARTICLE' selected>ARTICLE</option><option value='TEST' >TEST</option></select>";

    @Test
    public void testCreateSelectTag() {
        String name = "selectName";
        Object[] options = CommentType.values();
        Object selected = CommentType.ARTICLE;
        String result = SelectTagUtility.createSelectTag(name, options, selected);
        Assert.assertNotNull(result);
        Assert.assertEquals(String.format(expectedResult,name), result);
    }
}
