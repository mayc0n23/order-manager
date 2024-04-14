package io.github.mayc0n23.ordermanager.model.enums;

public enum Extension {

    TXT("text/plain");

    private final String contentType;

    Extension(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

}