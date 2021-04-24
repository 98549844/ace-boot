package com.entity.dao.hibernate;

import lombok.Data;

import java.io.Serializable;


@SuppressWarnings("JpaDataSourceORMInspection")
@Data
public class ProductEntity implements Serializable {

    private int id;
    private String item;
    private String itemSn;
    private Double price;
    private String category;
    Integer qty;






}
