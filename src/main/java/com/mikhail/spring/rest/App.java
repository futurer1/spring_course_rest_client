package com.mikhail.spring.rest;

import com.mikhail.spring.rest.configuration.MyConfig;
import com.mikhail.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        // добавление нового работника
        Employee emp = new Employee("Kolya", "Gavrilov", "IT",  800);

        // в ответе получили id только что добавленного работника
        int id = communication.saveEmployee(emp);

        // получение списка всех работников
        List<Employee> employees = communication.getEmployees();
        System.out.println(employees);

        // получение одного работника по id
        Employee employee = communication.getEmployee(id);
        System.out.println(employee);

        // изменение работника
        Employee emp1 = new Employee("Kolya", "Gavrilov", "HR",  808);
        emp1.setId(id);
        communication.saveEmployee(emp1);

        // удаление работника
        communication.delEmployee(id);
    }
}
