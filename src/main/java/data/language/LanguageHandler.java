package data.language;

import model.Language;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static util.AllBeanNameConstants.LANGUAGE_BEAN_NAME;

public class LanguageHandler {
    private LanguageBeanI languageBean;
    private Context ct;

    public LanguageHandler() {
        try {
            ct = new InitialContext();
            languageBean = (LanguageBeanI) ct.lookup(LANGUAGE_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public LanguageHandler(LanguageBeanI languageBean) {
        this.languageBean = languageBean;
    }

    public Map<String, Language> findAllLanguages() {
        List<Language> list = languageBean.findAllLanguages();
        Map<String, Language> map = new TreeMap<>();
        for (Language language : list) {
            map.put(language.getCode(), language);
        }
        return map;
    }

    public Language createLanguage(Language language) {
        return languageBean.createLanguage(language);
    }
}
