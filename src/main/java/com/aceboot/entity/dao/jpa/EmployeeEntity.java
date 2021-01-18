package com.aceboot.entity.dao.jpa;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EmployeeEntity implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date birthDate;
    private String title;
    private String dept;


}
