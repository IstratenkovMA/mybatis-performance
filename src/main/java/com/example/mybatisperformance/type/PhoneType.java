package com.example.mybatisperformance.type;

import lombok.Getter;

import java.util.Arrays;

//will be serialised as an Id of entity value
@Getter
public enum PhoneType {
    MOBILE(0),
    STATIONARY(1),
    WORK(2),
    FRIEND(3);
    private final Integer id;

    PhoneType(int id) {
        this.id = id;
    }

    public static PhoneType getById(int id) {
        for (PhoneType type : values())
            if (type.id == id)
                return type;
        throw new IllegalArgumentException("cannot find type with id " + id);
    }
}
