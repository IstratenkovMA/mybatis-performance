package com.example.mybatisperformance.typehandler;

import com.example.mybatisperformance.type.PhoneType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneTypeTypeHandler extends BaseTypeHandler<PhoneType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PhoneType parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getId());
    }

    @Override
    public PhoneType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.wasNull() ? null : PhoneType.getById(rs.getInt(columnName));
    }

    @Override
    public PhoneType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.wasNull() ? null : PhoneType.getById(rs.getInt(columnIndex));
    }

    @Override
    public PhoneType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.wasNull() ? null : PhoneType.getById(cs.getInt(columnIndex));
    }
}
