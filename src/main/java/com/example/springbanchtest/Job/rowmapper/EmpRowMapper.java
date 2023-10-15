package com.example.springbanchtest.Job.rowmapper;

import com.example.springbanchtest.bean.Emp;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpRowMapper implements RowMapper<Emp> {
    @Override
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
        Emp emp = new Emp();

        emp.setId(rs.getInt("id"));
        emp.setJob(rs.getString("name"));
        emp.setName(rs.getString("job"));
        emp.setSalary(rs.getDouble("salary"));

        return emp;

    }
}
