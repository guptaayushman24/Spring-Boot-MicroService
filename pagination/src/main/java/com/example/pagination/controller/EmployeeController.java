package com.example.pagination.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.pagination.employeeservice.EmployeeService;
import com.example.pagination.model.EmployeeModel;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/employees")
  public List<EmployeeModel> getAllEmployees(@RequestParam int pageNo,@RequestParam int pageSize) {
    return employeeService.fetchAllEmployee(pageNo,pageSize);
  }

  @GetMapping("/joining")
  public List<EmployeeModel> getMethodName(@RequestParam int sortFlag) { // 0 -> asc and 1 -> desc
     return employeeService.sortByJoiningDate(sortFlag);
  }
  
}
