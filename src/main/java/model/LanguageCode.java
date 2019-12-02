package model;

public enum LanguageCode {
    en(""), ru("ru/");
    private String path;

    LanguageCode(String path) {
        this.path = path;
    }

    public String getPath() {
        return path.toLowerCase();
    }
}
