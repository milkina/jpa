package model.sitemap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatyana on 04.02.2017.
 */

@XmlRootElement(name = "urlset",
        namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
public class UrlSet {

    private List<UrlEntity> url;

    @XmlElement(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
    public List<UrlEntity> getUrl() {
        return url;
    }

    public void setUrl(List<UrlEntity> list) {
        this.url = list;
    }

    public void addUrlEntity(UrlEntity entity) {
        if (url == null) {
            url = new ArrayList<>();
        }
        url.add(entity);
    }
}
