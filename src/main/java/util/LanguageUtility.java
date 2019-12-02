package util;

import model.Language;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

import static util.AllConstantsAttribute.LANGUAGES;

public class LanguageUtility extends SpringUtility {

    public static void loadLanguages(ServletContext servletContext) {
        Map<String, Language> languages = getLanguageService(servletContext).findAllLanguages();
        servletContext.setAttribute(LANGUAGES, languages);
    }

    public static Language findLanguageInContext(ServletContext servletContext, String key) {
        Map<String, Language> map = (Map<String, Language>) servletContext.getAttribute(LANGUAGES);
        return map.get(key);
    }

    public static Map<String, String> getLanguagesMap(ServletContext servletContext) {
        Map<String, Language> map = (Map<String, Language>) servletContext.getAttribute(LANGUAGES);
        Map<String, String> result = new HashMap<>();
        for (Language language : map.values()) {
            result.put(language.getCode().toString(), language.getDescription());
        }
        return result;
    }
}
