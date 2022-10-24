package com.kata.cinema.base.models.enums;

import lombok.Getter;

public enum PROFESSION {
    PRODUCER("Продюссер"),
    SCREENWRITER("Сценарист"),
    OPERATOR("Оператор"),
    COMPOSER("Композитор"),
    PAINTER("Художник"),
    MOUNTING("Монтажер"),
    ACTOR("Актёр");

    @Getter
    private final String translation;

    PROFESSION(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return " + translation + ";
    }
}



