package com.kata.cinema.base.models.enums;

import lombok.Getter;

public enum ProfessionName {
    PRODUCER("Продюссер"),
    SCREENWRITER("Сценарист"),
    OPERATOR("Оператор"),
    COMPOSER("Композитор"),
    PAINTER("Художник"),
    MOUNTING("Монтажер"),
    ACTOR("Актёр");

    @Getter
    private final String translation;

    ProfessionName(String translation) {
        this.translation = translation;
    }

    public static ProfessionName ofName(String name){
        for (ProfessionName value : ProfessionName.values()) {
            if (value.getTranslation().equals(name)){
                return value;
            }
        }

        throw new IllegalArgumentException("Couldn't find ProfessionName for name: " + name);
    }
}



