package com.example.mybatisperformance.mapper;

import com.example.mybatisperformance.entity.Customer;
import com.example.mybatisperformance.provider.CustomerSqlProvider;
import com.example.mybatisperformance.schema.CustomerSchema;
import com.example.mybatisperformance.type.CustomerType;
import com.example.mybatisperformance.type.PhoneType;
import com.example.mybatisperformance.typehandler.PhoneTypeTypeHandler;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface CustomerMapper extends CustomerSchema {

    String RESULT_MAP_NAME = "customer";

    @SelectProvider(type = CustomerSqlProvider.class, method = "getById")
    @Results(id = RESULT_MAP_NAME, value = {
            @Result(column = CUSTOMER_ID, property = F.id, id = true),
            @Result(column = CUSTOMER_VERSION, property = F.version),
            @Result(column = CUSTOMER_NAME, property = F.name),
            @Result(column = CUSTOMER_TYPE, property = F.customerType),
            @Result(column = CUSTOMER_PHONE, property = F.phone),
            @Result(column = CUSTOMER_CREATED_AT, property = F.createdAt),
            @Result(column = CUSTOMER_UPDATED_AT, property = F.updatedAt),
            @Result(column = CUSTOMER_PHONE_TYPE, property = F.phoneType, typeHandler = PhoneTypeTypeHandler.class),
            @Result(column = CUSTOMER_BALANCE, property = F.balance),
            @Result(column = CUSTOMER_IS_ACTIVE, property = F.isActive)})
    Customer getById(@Param(F.id) Long id);

//    @ResultMap("wbFxRate")
//    @SelectProvider(type = CustomerSqlProvider.class, method = "getByBaseCurrencyAndType")
//    Customer getByBaseCurrencyAndType(@Param(F.baseCurrency) String baseCurrency, @Param(F.type) WbFxRateType type);

    @ResultMap(RESULT_MAP_NAME)
    @SelectProvider(type = CustomerSqlProvider.class, method = "getAll")
    List<Customer> getAll();

    @UpdateProvider(type = CustomerSqlProvider.class, method = "update")
    int update(Customer customer);

    @InsertProvider(type = CustomerSqlProvider.class, method = "insert")
    int insert(Customer customer);

    @SelectProvider(type = CustomerSqlProvider.class, method = "getNextId")
    Long getNextId();

    @ResultMap(RESULT_MAP_NAME)
    @SelectProvider(type = CustomerSqlProvider.class, method = "findAllByCustomerType")
    List<Customer> findAllByCustomerType(CustomerType customerType);

    @ResultMap(RESULT_MAP_NAME)
    @SelectProvider(type = CustomerSqlProvider.class, method = "findAllByPhoneType")
    List<Customer> findAllByPhoneType(PhoneType phoneType);
}
