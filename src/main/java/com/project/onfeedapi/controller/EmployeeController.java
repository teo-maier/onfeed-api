package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.dto.PaginatedRequestDTO;
import com.project.onfeedapi.mapper.EmployeeMapper;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.service.EmployeeService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/all")
    public ResponseEntity<AbstractResponseDTO> getAll(@RequestParam(name = "search", required = false) String search) {
        return ResponseHandler.handleResponse("All employees", employeeService.searchEmployee(search));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<AbstractResponseDTO> getEmployee(@PathVariable("employeeId") long id) {
        return ResponseHandler.handleResponse("Employee by id", employeeService.getEmployee(id));
    }

    @GetMapping("/me")
    public ResponseEntity<AbstractResponseDTO> getEmployeeDetails(Principal principal) {
        Employee newEmployee = null;
        newEmployee = employeeService.getEmployeeByEmail(principal.getName());
        EmployeeDTO newEmployeeDTO = EmployeeMapper.convertToDTO(newEmployee);
        return ResponseHandler.handleResponse("Employee's details", newEmployeeDTO);
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

