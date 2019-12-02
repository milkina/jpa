package spring.services.language;

import model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repositories.LanguageRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
     public Map<String, Language> findAllLanguages() {
        List<Language> list = languageRepository.findAll();
        Map<String, Language> map = new TreeMap<>();
        for (Language language : list) {
            map.put(language.getCode().toString(), language);
        }
        return map;
    }

    @Override
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }
}
