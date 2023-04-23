package com.project.onfeedapi.mapper;


import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.model.Employee;

public class EmployeeMapper {
    public static EmployeeDTO convertToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .active(employee.isActive())
                .email(employee.getEmail())
                .type(employee.getType())
                .build();
    }

    public static Employee convertToModel(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeDTO.getId())
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .type(employeeDTO.getType())
                .build();
    }

}
