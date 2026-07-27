package com.example.pagination.employeeserviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.pagination.employeeservice.EmployeeService;
import com.example.pagination.model.EmployeeModel;
import com.example.pagination.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public List<EmployeeModel> fetchAllEmployee(int pageNo,int pageSize) {
    return employeeRepository.fetchAllEmployee(pageNo,pageSize);
  }

  @Override
  public List<EmployeeModel> sortByJoiningDate(int sortFlag) {
     return employeeRepository.sortByJoiningDate(sortFlag);
  }
}
