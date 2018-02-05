package com.company;

/**
 * Created by @kash on 1/9/2018.
 */
public class Employee {
    int empNo;
    String name;
    float salary;

    void giveDataToEmployee()   {
        empNo = 1001;
        name = "Rama";
        salary = 7000;
    }

    void displayEmployeeDetaila()   {
        System.out.println("name: " + name);
        System.out.println("Salary: " + salary);
    }
}

class Execute {
    public static void main(String[] args)   {
        Employee e = new Employee();
        e.giveDataToEmployee();
        System.out.println("Printing Employee Details: \n");
        e.displayEmployeeDetaila();
    }
}