package com.example.pagination.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.pagination.model.EmployeeModel;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

  private final JdbcTemplate jdbcTemplate;

  private static final RowMapper<EmployeeModel> EMPLOYEE_ROW_MAPPER = (rs, rowNum) -> {
    EmployeeModel employee = new EmployeeModel();
    employee.setId(rs.getInt("id"));
    employee.setName(rs.getString("name"));
    employee.setDept(rs.getString("dept"));
    employee.setJoiningDate(rs.getDate("joining_date"));
    return employee;
  };

  public List<EmployeeModel> fetchAllEmployee(int pageNo, int pageSize) {
    int offset = (pageNo-1) * pageSize;
    String sql = "SELECT id, name, dept, joining_date FROM employee_detail LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, EMPLOYEE_ROW_MAPPER, pageSize, offset);
  }

  public List<EmployeeModel> sortByJoiningDate (int sortFlag){
    String isAsendingOrDescending = "";
    if (sortFlag==-0){
      isAsendingOrDescending = "asc";
    }
    else if (sortFlag==1){
      isAsendingOrDescending = "desc";
    }
    String sql = "SELECT id, name, dept, joining_date FROM employee_detail order by joining_date"+" "+isAsendingOrDescending;
     return jdbcTemplate.query(sql, EMPLOYEE_ROW_MAPPER);
  }
}
