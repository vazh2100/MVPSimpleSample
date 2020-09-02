package com.vazheninapps.testemployees.screens.employee;

import com.vazheninapps.testemployees.pojo.Employee;

import java.util.List;

public interface EmployeeListView {
    void showData(List<Employee> employees);
    void showError(String string);
}
