package model.sitemap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tatyana on 04.02.2017.
 */
@XmlRootElement
public class UrlEntity {
    private String loc;
    private String lastmod;
    private String changefreq;
    private double priority;

    @XmlElement(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @XmlElement(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
    public String getLastmod() {
        return lastmod;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = lastmod;
    }

    @XmlElement(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
    public String getChangefreq() {
        return changefreq;
    }

    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }

    @XmlElement(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}
