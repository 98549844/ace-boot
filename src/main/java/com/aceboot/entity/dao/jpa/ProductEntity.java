package com.aceboot.entity.dao.jpa;

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



    public ProductEntity() {
    }

    public ProductEntity(String item, Double price, String category) {
        this.item = item;
        this.price = price;
        this.category = category;
    }


}
