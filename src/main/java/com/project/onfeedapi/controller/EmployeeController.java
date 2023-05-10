package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.dto.PaginatedRequestDTO;
import com.project.onfeedapi.mapper.EmployeeMapper;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.service.EmployeeService;
import com.project.onfeedapi.utils.exception.CustomExceptionHandler;
import com.project.onfeedapi.utils.exception.EmployeeException;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all-paginated")
    public ResponseEntity<AbstractResponseDTO> getAll(PaginatedRequestDTO pagination) {
        Page<EmployeeDTO> employeeDTOList = employeeService.getAllEmployeesPaginated(pagination).map(EmployeeMapper::convertToDTO);
        return ResponseHandler.handleResponse("All employees paginated", employeeDTOList);
    }

//    @PreAuthorize("@authorizationService.employeeTypeIn({" +
//            "T(com.ontegra.ontrack.utils.EmployeeType).MANAGER," +
//            "T(com.ontegra.ontrack.utils.EmployeeType).ADMIN" +
//            "})"
//    )
//    @GetMapping("/all")
//    public ResponseEntity<AbstractResponseDTO> getAll(@RequestParam(name = "search", required = false) String search) {
//        List<Employee> employees = employeeService.searchEmployee(search);
//        List<EmployeeDTO> employeeDTOList = employees.stream()
//                .map(EmployeeMapper::convertToDTO)
//                .collect(Collectors.toList());
//        return ResponseHandler.handleResponse("All employees", employeeDTOList);
//    }
//
//    @PreAuthorize("@authorizationService.employeeTypeIn({" +
//            "T(com.ontegra.ontrack.utils.EmployeeType).MANAGER," +
//            "T(com.ontegra.ontrack.utils.EmployeeType).ADMIN" +
//            "})"
//    )
//    @GetMapping("/all/active")
//    public ResponseEntity<AbstractResponseDTO> getAllActiveEmployees(@RequestParam(name = "search", required = false) String search) {
//        List<Employee> employees = employeeService.searchEmployee(search);
//        List<EmployeeDTO> employeeDTOList = employees.stream()
//                .map(EmployeeMapper::convertToDTO)
//                .filter(EmployeeDTO::isActive)
//                .collect(Collectors.toList());
//        return ResponseHandler.handleResponse("All employees with status active", employeeDTOList);
//    }

    @PutMapping("/delete/{employeeId}")
    public ResponseEntity<AbstractResponseDTO> deleteEmployee(@PathVariable("employeeId") long id) {
        Employee employee = employeeService.getEmployee(id);
        EmployeeDTO employeeDTO = EmployeeMapper.convertToDTO(employee);
        employeeService.deleteEmployee(employeeDTO.getId());
        return ResponseHandler.handleResponse("User disable", employeeDTO);
    }


    @PostMapping
    public ResponseEntity<AbstractResponseDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = EmployeeMapper.convertToModel(employeeDTO);
        newEmployee = employeeService.createEmployee(newEmployee);
        EmployeeDTO newEmployeeDTO = EmployeeMapper.convertToDTO(newEmployee);
        return ResponseHandler.handleResponse("User created successfully", newEmployeeDTO);
    }

    @PreAuthorize("@authorizationService.hasAccessToEmployee(#employeeId)")
    @GetMapping("/{employeeId}")
    public ResponseEntity<AbstractResponseDTO> getEmployee(@PathVariable("employeeId") long id) {
        Employee employee = null;
        employee = employeeService.getEmployee(id);
        EmployeeDTO employeeDTO = EmployeeMapper.convertToDTO(employee);
        return ResponseHandler.handleResponse("Employee by id", employeeDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<AbstractResponseDTO> getEmployeeDetails(Principal principal) {
        Employee newEmployee = null;
        newEmployee = employeeService.getEmployeeByEmail(principal.getName());
        EmployeeDTO newEmployeeDTO = EmployeeMapper.convertToDTO(newEmployee);
        return ResponseHandler.handleResponse("Employee's details", newEmployeeDTO);
    }

    @PreAuthorize("@authorizationService.employeeTypeIn({ " +
            "T(com.ontegra.ontrack.utils.EmployeeType).ADMIN" +
            "})"
    )
    @PutMapping("/toggle-status/{employeeId}")
    public ResponseEntity<AbstractResponseDTO> toggleEmployeeStatus(@PathVariable("employeeId") long id) {
        boolean changedEmployeeStatus = employeeService.toggleEmployeeStatus(id);
        return ResponseHandler.handleResponse("Status changed", changedEmployeeStatus);
    }

    @PutMapping(value = "/me", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AbstractResponseDTO> editEmployee(@RequestPart("employeeDetailsDTO") EmployeeDTO employeeDTO,
                                                            Principal principal) {
        Employee employee = null;
        employee = employeeService.getEmployeeByEmail(principal.getName());
        employeeService.editCurrentEmployee(employee, employeeDTO);
        EmployeeDTO editedEmployee = EmployeeMapper.convertToDTO(employee);
        return ResponseHandler.handleResponse("Current employee edited with avatar", editedEmployee);
    }


    @PreAuthorize("@authorizationService.employeeTypeIn({ " +
            "T(com.ontegra.ontrack.utils.EmployeeType).ADMIN" +
            "})"
    )
    @PutMapping("/{employeeId}")
    public ResponseEntity<AbstractResponseDTO> editEmployeeById(@PathVariable("employeeId") long id,
                                                                @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = null;
        employee = employeeService.getEmployee(id);
        employeeService.editEmployee(employee, employeeDTO);
        EmployeeDTO editedEmployee = EmployeeMapper.convertToDTO(employee);
        return ResponseHandler.handleResponse("Employee edited", editedEmployee);
    }

//    @PutMapping("/password")
//    public ResponseEntity<AbstractResponseDTO> changePassword(@RequestBody PasswordDTO passwordDTO, Principal principal) {
//        Employee employee = null;
//        try {
//            employee = employeeService.getEmployeeByEmail(principal.getName());
//            employeeService.changePassword(employee, passwordDTO);
//            return ResponseHandler.handleResponse("Password changed", null);
//        } catch (EmployeeException e) {
//            return ExceptionHandler.handleException(e);
//        }
}

