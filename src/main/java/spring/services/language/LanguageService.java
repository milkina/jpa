package spring.services.language;

import model.Language;

import java.util.Map;

public interface LanguageService {

    Map<String, Language> findAllLanguages();

    Language createLanguage(Language language);
}
