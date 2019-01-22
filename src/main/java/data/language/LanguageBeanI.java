package data.language;

import model.Language;

import java.util.List;

public interface LanguageBeanI {
    List<Language> findAllLanguages();

    Language createLanguage(Language language);

}
