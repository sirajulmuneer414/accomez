package com.sirajul.Accomez.Entities;

import com.sirajul.Accomez.CustomAnnotation.RolesAllowed;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = false)
    private String employeeusername;

    @Column(nullable = false,unique = true,length = 15)
    private String employeecode;

    @Column(nullable = false)
    private boolean enabled;


    public Employee(String name, String employeecode, String employeeusername, boolean enabled) {
        try {
            this.name = name;
            this.employeecode = employeecode;
            this.employeeusername = employeeusername;
            this.enabled = enabled;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Employee(String name, String employeecode, String employeeusername) {
        try {
            this.name = name;
            this.employeecode = employeecode;
            this.employeeusername = employeeusername;
            this.enabled = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeusername() {
        return employeeusername;
    }

    public void setEmployeeusername(String employeeusername) {

            this.employeeusername = employeeusername;
    }


    public String getEmployeecode() {

        return employeecode;
    }

    @RolesAllowed("ADMIN")
    public void setEmployeecode(String employeecode) {
        if(employeecode == null)
        this.employeecode = employeecode;
    }

    @RolesAllowed("ADMIN")
    public void setEmployeecode(String employeecode,String oldemployeecode) {
        if(employeecode.equals(employeecode))
            this.employeecode = oldemployeecode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
