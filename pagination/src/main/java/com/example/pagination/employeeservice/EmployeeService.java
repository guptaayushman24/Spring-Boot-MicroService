package com.example.pagination.employeeservice;

import java.util.List;

import com.example.pagination.model.EmployeeModel;

public interface EmployeeService {
  public List<EmployeeModel> fetchAllEmployee(int pageNo,int pageSize);
  public List<EmployeeModel> sortByJoiningDate(int sortFlag);
}
