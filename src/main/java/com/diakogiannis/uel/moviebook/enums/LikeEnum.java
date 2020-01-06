package com.diakogiannis.uel.moviebook.enums;


import java.util.HashMap;
import java.util.Map;

public enum LikeEnum {
    LIKE("like"),
    HATE("hate"),
    UNDO("undo");

    private String type;

    LikeEnum(String type) {
        this.type = type;
    }

    private static final Map<String, LikeEnum> BY_LABEL = new HashMap<>();

    static {
        for (LikeEnum e : values()) {
            BY_LABEL.put(e.type, e);
        }
    }

    public static LikeEnum valueOfLabel(String type) {
        return BY_LABEL.get(type);
    }

}
