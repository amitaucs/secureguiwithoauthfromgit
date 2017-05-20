package com.amisoft;

/**
 * Created by amitdatta on 03/05/17.
 */
public class EmployeeDetails {

    private String id;
    private String name;


    public EmployeeDetails() {
    }

    public EmployeeDetails(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
