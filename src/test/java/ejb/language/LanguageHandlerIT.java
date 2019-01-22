package ejb.language;

import model.Language;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;

import java.util.Map;

public class LanguageHandlerIT extends BaseIT {
    @Test
    public void testFindAllLanguages() {
        Map<String, Language> result = languageHandler.findAllLanguages();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
    }
}
