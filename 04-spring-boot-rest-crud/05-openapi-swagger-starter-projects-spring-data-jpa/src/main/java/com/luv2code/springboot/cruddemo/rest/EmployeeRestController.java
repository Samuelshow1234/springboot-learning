package com.luv2code.springboot.cruddemo.rest;

//import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private JsonMapper jsonMapper;

    @Autowired
    public  EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper){
        employeeService = theEmployeeService;
        jsonMapper = theJsonMapper;
    }



    // quick and dirty inject employee dao
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();


    }

    // read a single employee by id /employee/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public  Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);

        }
        return theEmployee;
    }

    // add mapping for POST /employees - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // also just in case they pass an id in JSON set id to 0
        // this is to force a save of new item .... instead of update

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //update employee using PUT
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // delete by employee Id
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        // check if the employee exists

        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee==null){
            throw new RuntimeException("Employee Not Found - " + employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Employee Deleted " + employeeId;


    }

    // add mapping for PATCH

    @PatchMapping("/employee/{employeeId}")
    public  Employee patchEmployee(@PathVariable int employeeId,
                                   @RequestBody Map<String, Object> patchPayLoad){
        Employee tempEmployee = employeeService.findById(employeeId);

        // throw error if not found

        if(tempEmployee==null){
            throw new RuntimeException("Employee ID NOT Found " + employeeId);
        }

        // throw if request contains the id
        if(patchPayLoad.containsKey("id")){
            throw new RuntimeException("Employee ID not allowed in the request body - " + employeeId);
        }
        Employee patchedEmployee = jsonMapper.updateValue(tempEmployee, patchPayLoad);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }



}
