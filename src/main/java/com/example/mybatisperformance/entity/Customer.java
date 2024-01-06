package com.example.mybatisperformance.entity;

import com.example.mybatisperformance.type.CustomerType;
import com.example.mybatisperformance.type.PhoneType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class Customer implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private BigDecimal balance;
    private PhoneType phoneType;
    private CustomerType customerType;
    private Boolean isActive;
    private int version;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public interface F {
        String id = "id";
        String name = "name";
        String phone = "phone";
        String balance = "balance";
        String phoneType = "phoneType";
        String customerType = "customerType";
        String isActive = "isActive";
        String version = "version";
        String createdAt = "createdAt";
        String updatedAt = "updatedAt";
    }
}
