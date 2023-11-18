package com.mikhail.spring.rest;

import com.mikhail.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/spring_course_rest/api/employees";

    public List<Employee> getEmployees() {

        // ResponseEntity обертка над HttpResponse
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange( //
                        URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Employee>>() {}
                );

        List<Employee> employees = responseEntity.getBody();
        return employees;
    }

    public Employee getEmployee(int id) {

        Employee employee = restTemplate.getForObject(
                URL + "/" + id,
                Employee.class
        );

        return employee;
    }

    public int saveEmployee(Employee employee) {
        int id = employee.getId();

        if (id == 0) {

            // тело ответа ResponseEntity будет String в виде JSON формата
            ResponseEntity<Employee> responseEntity =
                    restTemplate.postForEntity(
                            URL,
                            employee, // добавляется объект, который будет отправлен
                            Employee.class //
                    );
            System.out.println("Employee was added to DB");
            System.out.println(responseEntity.getBody());

            id = responseEntity.getBody().getId();
        } else {

            // изменение уже имеющегося работника
            restTemplate.put(URL, employee);
            System.out.println("Employee ID = " + id + " was updated");
        }

        return id;
    }

    public void delEmployee(int id) {
        restTemplate.delete(
                URL + "/" + id
        );
        System.out.println("Employee ID = " + id + " was deleted");
    }
}
