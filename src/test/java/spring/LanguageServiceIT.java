package spring;

import model.Language;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class LanguageServiceIT extends BaseIT {
    @Test
    public void testFindAllLanguages() {
        Map<String, Language> result = languageService.findAllLanguages();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
    }
}
