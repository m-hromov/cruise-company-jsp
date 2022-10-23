package com.cruisecompany.util.files;

public enum FileType {
    SHIP_PHOTO("ship"),
    PASSENGER_DOCUMENT("user_document");
    public final String str;
    FileType(String str){
        this.str = str;
    }
}
