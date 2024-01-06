package com.example.mybatisperformance.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

//will be serialised as a string value
@Getter
@AllArgsConstructor
public enum CustomerType {
    MANAGER(0),
    USER(1),
    STAFF(2),
    EXTERNAL(3),
    OWNER(4),
    PLAYER(5);
    private final int id;
}
