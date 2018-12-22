package util;

import controller.language.LanguageHandler;
import data.language.Language;

import javax.servlet.ServletContext;

import java.util.Map;

import static util.AllConstantsAttribute.LANGUAGES;

public class LanguageUtility {
    private static LanguageHandler languageHandler = new LanguageHandler();

    public static void loadLanguages(ServletContext servletContext) {
        Map<String, Language> languages = languageHandler.findAllLanguages();
        servletContext.setAttribute(LANGUAGES, languages);
    }

    public static Language findLanguageInContext(ServletContext servletContext, String key) {
        Map<String, Language> map = (Map<String, Language>) servletContext.getAttribute(LANGUAGES);
        return map.get(key);
    }
}
