package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.dto.PaginatedRequestDTO;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.repository.EmployeeRepository;
import com.project.onfeedapi.utils.EmployeeType;
import com.project.onfeedapi.utils.PaginationUtils;
import com.project.onfeedapi.utils.StringUtils;
import com.project.onfeedapi.utils.exception.EmployeeException;
import com.project.onfeedapi.utils.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private final BCryptPasswordEncoder encoder;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

//    public List<Employee> searchEmployee(String search) {
//        List<String> inputSearchKeys = Arrays.asList("firstName", "lastName", "fullName", "email");
//        EmployeeSearchCriteria employeeSearchCriteria = new EmployeeSearchCriteria(inputSearchKeys, search);
//        EmployeeSpecification employeeSpecification = new EmployeeSpecification(employeeSearchCriteria);
//        Sort sortByStatusAndName = Sort.by("active").descending().and(Sort.by("lastName").ascending()).and(Sort.by("firstName").ascending());
//        return employeeRepository.findAll(employeeSpecification, sortByStatusAndName);
//    }


    public Page<Employee> getAllEmployeesPaginated(PaginatedRequestDTO request) {
        return employeeRepository.findAll(PaginationUtils.createPageableWithSort(request));
    }

    public Employee getEmployee(long id) throws EmployeeException {
        if (id < 0) {
            throw new EmployeeException("Invalid param", ErrorCode.GENERAL_ERROR);
        }
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeException("Employee does not exist", ErrorCode.NOT_FOUND));
    }

    public List<Employee> getAdminList(List<Employee> employees) throws EmployeeException {
        List<Employee> adminList;
        adminList = employees.stream()
                .filter(e -> e.getType() == EmployeeType.ADMIN)
                .collect(Collectors.toList());
        if (adminList.isEmpty()) {
            throw new EmployeeException("No admin found", ErrorCode.GENERAL_ERROR);
        } else {
            return adminList;
        }
    }

    @Transactional
    public void deleteEmployee(long id) throws EmployeeException {
        existsEmployeeById(id);
        boolean employeeStatus = employeeRepository.getStatusById(id);
        if (employeeStatus) {
            toggleEmployeeStatus(id);
        } else {
            throw new EmployeeException("Employee is already disabled", ErrorCode.GENERAL_ERROR);
        }
    }

    private void existsEmployeeById(long id) throws EmployeeException {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new EmployeeException("Employee does not exist", ErrorCode.NOT_FOUND);
        }
    }

    public Employee createEmployee(Employee employee) throws EmployeeException {
        validateEmployee(employee);
        setGeneratedPassword(employee);
        employee.setActive(true);
        return saveEmployee(employee);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    private void validateEmployee(Employee employee) throws EmployeeException {
        String firstName = employee.getFirstName().trim();
        String lastName = employee.getLastName().trim();
        if (StringUtils.isMatch(firstName, "[a-zA-Z ]+") && StringUtils.isMatch(lastName, "[a-zA-Z ]+")) {
            employee.setFirstName(StringUtils.capitalizeFirstLetter(firstName));
            employee.setLastName(StringUtils.capitalizeFirstLetter(lastName));
        } else {
            throw new EmployeeException("Name must contain only letters", ErrorCode.GENERAL_ERROR);
        }
    }

    private void setGeneratedPassword(Employee employee) {
        String password = employee.getEmail();
        String encodedPassword = encoder.encode(password);
        employee.setPassword(encodedPassword);
    }

    @Transactional
    public boolean toggleEmployeeStatus(long id) throws EmployeeException {
        existsEmployeeById(id);
        boolean employeeStatus = employeeRepository.getStatusById(id);
        boolean invertedEmployeeStatus = !employeeStatus;
        employeeRepository.toggleEmployeeStatus(id, invertedEmployeeStatus);
        return invertedEmployeeStatus;
    }

    public void editEmployee(Employee employee, EmployeeDTO editedEmployee) throws EmployeeException {
        validateEmployee(employee);
        editInformation(editedEmployee, employee);
        saveEmployee(employee);
    }


    private void editInformation(EmployeeDTO editedEmployee, Employee employee) {
        employee.setFirstName(editedEmployee.getFirstName());
        employee.setLastName(editedEmployee.getLastName());
        employee.setEmail(editedEmployee.getEmail());
        employee.setType(editedEmployee.getType());
    }


    public void editCurrentEmployee(Employee employee, EmployeeDTO editedEmployee) throws EmployeeException {
        validateEmployee(employee);
        employee.setFirstName(editedEmployee.getFirstName());
        employee.setLastName(editedEmployee.getLastName());
    }

    private void verifyCurrentPassword(String encodedPassword, String rawPassword) throws EmployeeException {
        boolean matchedPassword = encoder.matches(rawPassword, encodedPassword);
        if (!matchedPassword) {
            throw new EmployeeException("Bad credentials", ErrorCode.GENERAL_ERROR);
        }
    }

//    public void changePassword(Employee employee, PasswordDTO passwordDTO) throws EmployeeException {
//        verifyCurrentPassword(employee.getPassword(), passwordDTO.getPassword());
//        String encodedPassword = encoder.encode(passwordDTO.getNewPassword());
//        employee.setPassword(encodedPassword);
//        saveEmployee(employee);
//    }

    public Employee getEmployeeByEmail(String email) throws EmployeeException {
        if (email == null) {
            throw new EmployeeException("Invalid param", ErrorCode.GENERAL_ERROR);
        }
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new EmployeeException("Employee does not exist!", ErrorCode.NOT_FOUND);
        }
        return employee;
    }

    public Employee getLoggedInEmployee(Principal principal) {
        if (principal == null) {
            return null;
        }
        return employeeRepository.findByEmail(principal.getName());
    }

}
