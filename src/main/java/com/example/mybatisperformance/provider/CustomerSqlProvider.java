package com.example.mybatisperformance.provider;

import com.example.mybatisperformance.entity.Customer;
import com.example.mybatisperformance.schema.CustomerSchema;
import com.example.mybatisperformance.type.CustomerType;
import com.example.mybatisperformance.type.PhoneType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class CustomerSqlProvider implements CustomerSchema {

    public String getById(@Param(F.id) Long id) {
        return new SQL()
                .SELECT(String.join(",", columns.keySet()))
                .FROM(TABLE_NAME)
                .WHERE(String.format("%s = %s", CUSTOMER_ID, id))
                .toString();
    }

//    @ResultMap("wbFxRate")
//    @SelectProvider(type = CustomerSqlProvider.class, method = "getByBaseCurrencyAndType")
//    Customer getByBaseCurrencyAndType(@Param(F.baseCurrency) String baseCurrency, @Param(F.type) WbFxRateType type);

    public String getAll() {
        return new SQL()
                .SELECT(String.join(",", columns.keySet()))
                .FROM(TABLE_NAME)
                .toString();
    }

    public String update(Customer customer) {
        return new SQL()
                .UPDATE(TABLE_NAME)
                .SET(String.format("%s = #{%s}", CUSTOMER_NAME, F.name))
                .SET(String.format("%s = #{%s}", CUSTOMER_PHONE, F.phone))
                .SET(String.format("%s = #{%s}", CUSTOMER_BALANCE, F.balance))
                .SET(String.format("%s = %s", CUSTOMER_PHONE_TYPE, String.format("#{%s,typeHandler=com.example.mybatisperformance.typehandler.PhoneTypeTypeHandler}", F.phoneType)))
                .SET(String.format("%s = #{%s}", CUSTOMER_TYPE, F.customerType))
                .SET(String.format("%s = #{%s}", CUSTOMER_IS_ACTIVE, F.isActive))
                .SET(String.format("%s = #{%s}", CUSTOMER_VERSION, F.version))
                .SET(String.format("%s = #{%s}", CUSTOMER_UPDATED_AT, F.updatedAt))
                .WHERE(String.format("%s = %s", CUSTOMER_ID, customer.getId()))
                .AND()
                .WHERE(String.format("%s = %s", CUSTOMER_VERSION, customer.getVersion() - 1))
                .toString();
    }

    public String insert(Customer customer) {
        return new SQL()
                .INSERT_INTO(TABLE_NAME)
                .INTO_COLUMNS(new String[]{String.join(",", List.of(
                        CUSTOMER_ID,
                        CUSTOMER_NAME,
                        CUSTOMER_PHONE,
                        CUSTOMER_BALANCE,
                        CUSTOMER_PHONE_TYPE,
                        CUSTOMER_TYPE,
                        CUSTOMER_IS_ACTIVE,
                        CUSTOMER_VERSION,
                        CUSTOMER_CREATED_AT,
                        CUSTOMER_UPDATED_AT))})
                .INTO_VALUES(new String[]{String.join(",", getFormattedProperties())}).toString();
    }

    public String getNextId() {
        return new SQL()
                .SELECT(String.format("nextval('%s')", SEQ_NAME))
                .toString();
    }

    public String findAllByCustomerType(CustomerType customerType) {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE_NAME)
                .WHERE(String.format("%s = '%s'", CUSTOMER_TYPE, customerType.toString()))
                .toString();
    }

    public String findAllByPhoneType(PhoneType phoneType) {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE_NAME)
                .WHERE(String.format("%s = %s", CUSTOMER_PHONE_TYPE, phoneType.getId()))
                .toString();
    }

    private List<String> getFormattedProperties() {
        return List.of(String.format("#{%s}", F.id),
                String.format("#{%s}", F.name),
                String.format("#{%s}", F.phone),
                String.format("#{%s}", F.balance),
                String.format("#{%s,typeHandler=com.example.mybatisperformance.typehandler.PhoneTypeTypeHandler}", F.phoneType),
                String.format("#{%s}", F.customerType),
                String.format("#{%s}", F.isActive),
                String.format("#{%s}", F.version),
                String.format("#{%s}", F.createdAt),
                String.format("#{%s}", F.updatedAt));
    }
}
