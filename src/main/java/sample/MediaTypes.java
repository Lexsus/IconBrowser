package sample;

import okhttp3.MediaType;

public enum MediaTypes {
    JSON("application/json; charset=utf-8"),
    JPG("image/jpeg");

    private MediaType type;

    MediaTypes(String type) {
        this.type = MediaType.parse(type);
    }

    public MediaType getType() {
        return type;
    }
}

