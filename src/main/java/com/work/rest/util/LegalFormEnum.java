package com.work.rest.util;

public enum LegalFormEnum {
    UR ("UR"), IP("IP"), FL("FL");

    private final String title;

    LegalFormEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
