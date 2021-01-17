package com.aceboot.entity.jpa;

import lombok.Data;

import java.io.Serializable;


@Data
public class TestEntity implements Serializable {
    private Integer id;
    private Integer userId;
    private String userName;
    private String email;

}

