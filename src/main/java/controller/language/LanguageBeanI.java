package controller.language;

import data.language.Language;

import java.util.List;

public interface LanguageBeanI {
    List<Language> findAllLanguages();

    Language createLanguage(Language language);

}
