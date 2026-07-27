package com.example.pagination.model;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeModel {
  private Integer id;
  private String name;
  private String dept;
  private Date joiningDate;
}
