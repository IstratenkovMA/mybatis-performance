package com.example.mybatisperformance.schema;

import com.example.mybatisperformance.entity.Customer;

import java.util.LinkedHashMap;

public interface CustomerSchema {
    String TABLE_NAME = "customer";
    String SEQ_NAME = "customer_seq";
    String CUSTOMER_ID = "customer_id";
    String CUSTOMER_NAME = "customer_name";
    String CUSTOMER_PHONE = "customer_phone";
    String CUSTOMER_BALANCE = "customer_balance";
    String CUSTOMER_PHONE_TYPE = "customer_phone_type";
    String CUSTOMER_TYPE = "customer_type";
    String CUSTOMER_IS_ACTIVE = "customer_is_active";
    String CUSTOMER_VERSION = "customer_version";
    String CUSTOMER_CREATED_AT = "customer_created_at";
    String CUSTOMER_UPDATED_AT = "customer_updated_at";

    interface F extends Customer.F {}

    LinkedHashMap<String, String> columns = new LinkedHashMap<>() {{
            put(CUSTOMER_ID, F.id);
            put(CUSTOMER_NAME, F.name);
            put(CUSTOMER_PHONE, F.phone);
            put(CUSTOMER_BALANCE, F.balance);
            put(CUSTOMER_PHONE_TYPE, F.phoneType);
            put(CUSTOMER_TYPE, F.customerType);
            put(CUSTOMER_IS_ACTIVE, F.isActive);
            put(CUSTOMER_VERSION, F.version);
            put(CUSTOMER_CREATED_AT, F.createdAt);
            put(CUSTOMER_UPDATED_AT, F.updatedAt);
        }};
}
