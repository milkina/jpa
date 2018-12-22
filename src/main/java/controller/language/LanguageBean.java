package controller.language;

import data.language.Language;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LanguageBean implements LanguageBeanI {
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public List<Language> findAllLanguages() {
        Query query = entityManager.createNamedQuery("Language.findAllLanguages");
        return query.getResultList();
    }

    public Language createLanguage(Language language) {
        entityManager.persist(language);
        return language;
    }
}
